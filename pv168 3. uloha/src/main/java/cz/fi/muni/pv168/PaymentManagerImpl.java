package cz.fi.muni.pv168;

import java.util.ArrayList;
import java.util.List;
import java.lang.UnsupportedOperationException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.sql.DataSource;

/**
 * Created by lubomir.viluda on 16.3.2016.
 */
public class PaymentManagerImpl implements cz.fi.muni.pv168.PaymentManager {
    private final DataSource dataSource;
    private List<Payment> payments = null;

    public PaymentManagerImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }
       
    public Payment createPayment(Payment payment) {

        //System.out.print("receiver> "+payment.getReceiver().getId());
        //System.out.print("sender> "+payment.getSender().getId());
        if (payment == null) {
            throw new IllegalArgumentException("create null payment");
        }

        if (payment.getId() != null) {
            throw new IllegalArgumentException("payment id is already set");
        }

        if (payment.getSender() ==  null || payment.getReceiver() == null){
            throw new IllegalArgumentException("empty accounts");
        }

        if((new BigDecimal(0).compareTo(payment.getAmount())) == 1 ) {
            throw new IllegalArgumentException("initial sum account must be positive");
        }
        if(payment.getReceiver().getWasDeleted()==true){
            throw new IllegalArgumentException("wasDeleted");
        }
        if(payment.getSender().getWasDeleted()==true){
            throw new IllegalArgumentException("wasDeleted");
        }
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "INSERT INTO payment (amount,date,message,sended,reciever,sender) VALUES (?,?,?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS)) {

            st.setBigDecimal(1, payment.getAmount());
            st.setDate(2,toSqlDate(payment.getDate()));
            st.setString(3, payment.getMessage());
            st.setBoolean(4, payment.getSended());
            st.setLong(5, payment.getReceiver().getId());
            st.setLong(6, payment.getSender().getId());
            int addedRows = st.executeUpdate();
            if (addedRows != 1) {
                throw new ServiceFailureException("Internal Error: More rows ("
                        + addedRows + ") inserted when trying to insert payment " + payment);
            }

            ResultSet keyRS = st.getGeneratedKeys();
            payment.setId(getKey(keyRS, payment));

        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when inserting account " + payment, ex);
        }
        return null;
    }
    private Long getKey(ResultSet keyRS, Payment payment) throws ServiceFailureException, SQLException {
        if (keyRS.next()) {
            if (keyRS.getMetaData().getColumnCount() != 1) {
                throw new ServiceFailureException("Internal Error: Generated key"
                        + "retriving failed when trying to insert payment " + payment
                        + " - wrong key fields count: " + keyRS.getMetaData().getColumnCount());
            }
            Long result = keyRS.getLong(1);
            if (keyRS.next()) {
                throw new ServiceFailureException("Internal Error: Generated key"
                        + "retriving failed when trying to insert payment " + payment
                        + " - more keys found");
            }
            return result;
        } else {
            throw new ServiceFailureException("Internal Error: Generated key"
                    + "retriving failed when trying to insert payment " + payment
                    + " - no key found");
        }
    }

    public void updatePayment(Payment payment) {
        
  
        if(payment == null) {
            throw new IllegalArgumentException("Null payment");
        }
        
        if(findPayment(payment.getId()).getSended()) {
            throw new IllegalArgumentException("Sended payment could't be upgraded");
        }
        
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "UPDATE payment SET amount = ? ,date= ? ,message = ? " +
                                " ,sended = ?, reciever = ?, sender = ? WHERE id = ? AND sended = ?")) {
            st.setBigDecimal(1, payment.getAmount());
            st.setDate(2,toSqlDate(payment.getDate()));
            st.setString(3, payment.getMessage());
            st.setBoolean(4, payment.getSended());
            st.setObject(5, payment.getReceiver().getId());
            st.setObject(6, payment.getSender().getId());
            st.setLong(7, payment.getId());
            st.setBoolean(8, false);
            st.executeUpdate();
        } catch (SQLException ex) {
            throw new ServiceFailureException( "Error while find account", ex);
        }
        
        if(payment.getSended()){ //odeslani platby
            //System.out.println("posilam"+payment.getAmount()+"reciever: "+payment.getReceiver().getId()+"sender: "+payment.getSender().getId());
            try (  
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "UPDATE accounts SET sumAmount = sumAmount + ? WHERE id = ?")) {
                st.setBigDecimal(1, payment.getAmount());
                st.setLong(2,payment.getReceiver().getId());
                st.executeUpdate();
                
            } catch (SQLException ex) {
                throw new ServiceFailureException( "Error while send money - reciever ", ex);
            }
            
             try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "UPDATE accounts SET sumAmount = sumAmount - ? WHERE id = ?")) {
                st.setBigDecimal(1, payment.getAmount());
                st.setLong(2,payment.getSender().getId());
                st.executeUpdate();
                
            } catch (SQLException ex) {
                throw new ServiceFailureException( "Error while send money - sender", ex);
            }
            
        }
    }

    public Payment findPayment(Long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT id,amount,date,message,sended,reciever,sender FROM payment WHERE id="+id
                )) {
            
            ResultSet rs = st.executeQuery();
            List<Payment> result = new ArrayList<>();

            while (rs.next()) {
                result.add(resultSetToPayment(rs));
            }
            return result.get(0);

        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error, impossible ID", ex);
        }
    }

    public List<Payment> allPayments() {
         try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT payment.id,amount,date,message,sended,reciever,sender FROM payment"
                )) {

            ResultSet rs = st.executeQuery();

            List<Payment> result = new ArrayList<>();

            while (rs.next()) {
                result.add(resultSetToPayment(rs));
            }
            return result;

        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error while find payment", ex);
        }
    }
    
    private Payment resultSetToPayment(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setId(rs.getLong("id"));
        payment.setAmount(rs.getBigDecimal("amount"));
        Date date = (rs.getDate("date"));
        Instant instant = Instant.ofEpochMilli(date.getTime());
        payment.setDate(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate());
        payment.setMessage(rs.getString("message"));
        payment.setSended(rs.getBoolean("sended"));
        
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT id,birthName,givenName,accountNumber,sumAmount,wasDeleted FROM accounts WHERE id = "+rs.getLong("sender")
                )) {

            ResultSet accountRs = st.executeQuery();

            List<Account> result = new ArrayList<>();

            while (accountRs.next()) {
                result.add(resultSetToAccount(accountRs));
            }
            payment.setSender(result.get(0));

        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error while find account", ex);
        }
        
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT id,birthName,givenName,accountNumber,sumAmount,wasDeleted FROM accounts WHERE id = "+rs.getLong("reciever")
                )) {

            ResultSet accountRs = st.executeQuery();

            List<Account> result = new ArrayList<>();

            while (accountRs.next()) {
                result.add(resultSetToAccount(accountRs));
            }
            payment.setReceiver(result.get(0));

        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error while find account", ex);
        }
               
        
        return payment;
    }
    
    private Account resultSetToAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setId(rs.getLong("id"));
        account.setBirthName(rs.getString("birthName"));
        account.setGivenName(rs.getString("givenName"));
        account.setAccountNumber(rs.getString("accountNumber"));
        account.setSumAmount(rs.getBigDecimal("sumAmount"));
        account.setWasDeleted(rs.getBoolean("wasDeleted"));
        return account;
    }
    public List<Payment> findPaymentsByAccount(Account account) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT id,amount,date,message,sender,reciever,sended, FROM payment" +
                                " WHERE receiver =  " + account.getId() +
                                " OR sender = " + account.getId())) {

            ResultSet rs = st.executeQuery();

            List<Payment> result = new ArrayList<>();
            while (rs.next()) {
                result.add(resultSetToPayment(rs));
            }
            return result;

        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when retrieving all accounts", ex);
        }
    }

    public List<Payment> findPaymentsBySenderAccount(Account account) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT id,amount,date,message,sender,reciever,sended, FROM payment" +
                                " WHERE sender = " + account.getId())) {

            ResultSet rs = st.executeQuery();

            List<Payment> result = new ArrayList<>();
            while (rs.next()) {
                result.add(resultSetToPayment(rs));
            }
            return result;

        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when retrieving all accounts", ex);
        }
    }

    public List<Payment> findPaymentsByReceiverAccount(Account account) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT id,amount,date,message,sender,reciever,sended, FROM payment" +
                                " WHERE  reciever = " + account.getId())) {

            ResultSet rs = st.executeQuery();

            List<Payment> result = new ArrayList<>();
            while (rs.next()) {
                result.add(resultSetToPayment(rs));
            }
            return result;

        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when retrieving all accounts", ex);
        }
    }

    private static Date toSqlDate(LocalDate localDate) {
        return localDate == null ? null : Date.valueOf(localDate);
    }

    private static LocalDate toLocalDate(Date date) {
        return date == null ? null : date.toLocalDate();
    }
}
