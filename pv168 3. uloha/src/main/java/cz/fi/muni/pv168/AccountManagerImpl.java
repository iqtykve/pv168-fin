package cz.fi.muni.pv168;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Ondra
 */
public class AccountManagerImpl implements AccountManager {

    private final DataSource dataSource;

    public AccountManagerImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }


    private void validate(Account account) throws IllegalArgumentException {
        if(account == null) {
            throw new IllegalArgumentException("account should not be null");
        }

        if(account.getBirthName().isEmpty() || account.getGivenName().isEmpty()) {
            throw new IllegalArgumentException("Part of name is empty");
        }

        if(!isAlpha(account.getBirthName()) || !isAlpha(account.getGivenName())) {
            throw new IllegalArgumentException("Name should contains only chars");
        }
    }

    @Override
    public void createAccount(Account account)throws ServiceFailureException {

        validate(account);
        if (account.getId() != null) {
            throw new IllegalArgumentException("account id is already set");
        }

        if((new BigDecimal(0).compareTo(account.getSumAmount())) == 1 ) {
            throw new IllegalArgumentException("initial sum account must be positive");
        }

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "INSERT INTO accounts (birthName,givenName,accountNumber,sumAmount,wasDeleted) VALUES (?,?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, account.getBirthName());
            st.setString(2, account.getGivenName());
            st.setString(3, account.getAccountNumber());
            st.setBigDecimal(4, account.getSumAmount());
            st.setBoolean(5, account.getWasDeleted());
            int addedRows = st.executeUpdate();
            if (addedRows != 1) {
                throw new ServiceFailureException("Internal Error: More rows ("
                        + addedRows + ") inserted when trying to insert account " + account);
            }

            ResultSet keyRS = st.getGeneratedKeys();
            account.setId(getKey(keyRS, account));

        } catch (SQLException ex) {
            throw new ServiceFailureException("Error when inserting account " + account, ex);
        }
    }


    private Long getKey(ResultSet keyRS, Account account) throws ServiceFailureException, SQLException {
        if (keyRS.next()) {
            if (keyRS.getMetaData().getColumnCount() != 1) {
                throw new ServiceFailureException("Internal Error: Generated key"
                        + "retriving failed when trying to insert account " + account
                        + " - wrong key fields count: " + keyRS.getMetaData().getColumnCount());
            }
            Long result = keyRS.getLong(1);
            if (keyRS.next()) {
                throw new ServiceFailureException("Internal Error: Generated key"
                        + "retriving failed when trying to insert account " + account
                        + " - more keys found");
            }
            return result;
        } else {
            throw new ServiceFailureException("Internal Error: Generated key"
                    + "retriving failed when trying to insert account " + account
                    + " - no key found");
        }
    }

    @Override
    public void updateAccount(Account account){
        validate(account);

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "UPDATE accounts" +
                                " SET" +
                                " birthName = ? " +
                                " ,givenName = ? " +
                                " ,accountNumber = ? "  +
                                " ,sumAmount = ? "  +
                                " ,wasDeleted = ? " +
                                " WHERE id = ?")) {
            st.setString(1, account.getBirthName());
            st.setString(2, account.getGivenName());
            st.setString(3, account.getAccountNumber());
            st.setBigDecimal(4, account.getSumAmount());
            st.setBoolean(5, account.getWasDeleted());
            st.setLong(6, account.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error while find account", ex);
        }
    }
    @Override
    public void deleteAccount(Account account){
        account.setWasDeleted(true);
        this.updateAccount(account);
    }

    @Override
    public Account findAccount(int id){
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT id,birthName,givenName,accountNumber,sumAmount,wasDeleted FROM accounts WHERE id ="
                + id)) {

            ResultSet rs = st.executeQuery();

            List<Account> result = new ArrayList<>();

            while (rs.next()) {
                result.add(resultSetToAccount(rs));
            }
            return result.get(0);

        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error while find account by id", ex);
        }
    }
    
    public List<Account> findAccountByName(String name){
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT * FROM accounts WHERE birthName = ? OR givenName = ?")) {
            st.setString(1, name);
            st.setString(2, name);
            ResultSet rs = st.executeQuery();

            List<Account> result = new ArrayList<>();
            while (rs.next()) {
                result.add(resultSetToAccount(rs));
            }
            return result;

        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when retrieving all accounts", ex);
        }
    }
    
    public List<Account> findAccountByNameActive(String name){
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT * FROM accounts WHERE (birthName = ? OR givenName = ?) AND wasDeleted = false")) {
            st.setString(1, name);
            st.setString(2, name);
            ResultSet rs = st.executeQuery();

            List<Account> result = new ArrayList<>();
            while (rs.next()) {
                result.add(resultSetToAccount(rs));
            }
            return result;

        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when retrieving all accounts", ex);
        }
    }

    @Override
    public List<Account> findAllAccount() throws ServiceFailureException{
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement st = connection.prepareStatement(
                        "SELECT * FROM accounts")) {

            ResultSet rs = st.executeQuery();

            List<Account> result = new ArrayList<>();
            while (rs.next()) {
                result.add(resultSetToAccount(rs));
            }
            return result;

        } catch (SQLException ex) {
            throw new ServiceFailureException(
                    "Error when retrieving all accounts", ex);
        }
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

    private boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

}
