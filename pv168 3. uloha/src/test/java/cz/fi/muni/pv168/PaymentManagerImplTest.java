package cz.fi.muni.pv168;

import cz.fi.muni.pv168.Account;
import cz.fi.muni.pv168.AccountManager;
import cz.fi.muni.pv168.AccountManagerImpl;
import cz.fi.muni.pv168.Payment;
import cz.fi.muni.pv168.PaymentManager;
import cz.fi.muni.pv168.PaymentManagerImpl;
import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import static org.junit.Assert.*;

import org.apache.derby.jdbc.*;


/**
 * Created by lubomir.viluda on 16.3.2016.
 */
public class PaymentManagerImplTest {
    
    private DataSource dataSource;
    private PaymentManager manager;
    private AccountManager managerAcc;
    
    @org.junit.Before
    public void setUp() throws SQLException {
        dataSource = prepareDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement("CREATE TABLE payment " +
                    "(ID BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "amount NUMERIC(8,3), " +
                    "date DATE, " +
                    "message VARCHAR(120), " +
                    "sender BIGINT, " +
                    "reciever BIGINT, " +
                    "sended BOOLEAN)").executeUpdate();
        }
        manager = new PaymentManagerImpl(dataSource);//(dataSource);
        

        dataSource = prepareDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement("CREATE TABLE accounts " +
                    "(ID BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY, " +
                    "birthName VARCHAR(20), " +
                    "givenName VARCHAR(30), " +
                    "accountNumber VARCHAR(20), " +
                    "sumAmount NUMERIC(8,3), " +
                    "wasDeleted BOOLEAN)").executeUpdate();
        }
        managerAcc = new AccountManagerImpl(dataSource);//(dataSource);
    }
    
    private static DataSource prepareDataSource() throws SQLException {
        EmbeddedDataSource ds = new EmbeddedDataSource();
        //we will use in memory database
        ds.setDatabaseName("memory:payment");
        ds.setCreateDatabase("create");
        return ds;
    }
    
    @org.junit.After
    public void tearDown() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement("DROP TABLE payment").executeUpdate();
        }
        try (Connection connection = dataSource.getConnection()) {
            connection.prepareStatement("DROP TABLE accounts").executeUpdate();
        }
    }
    
    @org.junit.Test
    public void testCreatePayment() throws Exception {
        PaymentManagerImpl paymentManager = new PaymentManagerImpl(dataSource);

        assertTrue(paymentManager.allPayments().isEmpty());
        assertTrue(paymentManager.allPayments().size() == 0);
        
        Account account1 = newAccount("Franta","Vizl", new BigDecimal("0"), "800/0100",false);
        Account account2 = newAccount("Franta","Vizl", new BigDecimal("0"), "800/0100",false);
        managerAcc.createAccount(account1);
        managerAcc.createAccount(account2);
        
        Payment payment1 = newPayment(new BigDecimal("500"),LocalDate.now(),"platba zkouska1",false,account1,account2);
        Payment payment2 = newPayment(new BigDecimal("100"),LocalDate.now(),"platba zkouska2",false,account2,account1);
        
        paymentManager.createPayment(payment1);
        paymentManager.createPayment(payment2);
        
        assertTrue(paymentManager.allPayments().size() == 2);
    }

    @org.junit.Test
    public void testCreatePaymentNull() throws Exception {
        PaymentManagerImpl paymentManager = new PaymentManagerImpl(dataSource);

        try {
            paymentManager.createPayment(null);
            fail();
        } catch (IllegalArgumentException ex) {
            // correct exception
        } catch (Exception ex) {
            fail();
        }
    }

    @org.junit.Test
    public void testUpdatePayment() throws Exception {
        PaymentManager paymentManager = new PaymentManagerImpl(dataSource);

        Account a1 = newAccount("Jan","Vizl", new BigDecimal("100.000"), "800/0100",false);
        Account a2 = newAccount("Franta","Fizl", new BigDecimal("100.000"), "800/0100",false);
        
        managerAcc.createAccount(a1);
        managerAcc.createAccount(a2);

        List<Account> result =  managerAcc.findAllAccount();  
        
        Payment payment = newPayment(new BigDecimal("10"),LocalDate.now(),"platba zkouska1",false,a1,a2);
        Payment payment1 = newPayment(new BigDecimal("101"),LocalDate.now(),"platba zkouska2",false,a2,a1);
        paymentManager.createPayment(payment);
        paymentManager.createPayment(payment1);

        payment.setSended(true);
        paymentManager.updatePayment(payment);
        
        List<Account> result1 =  managerAcc.findAllAccount();
        
        assertEquals(new BigDecimal("110.000"),result1.get(0).getSumAmount());
        assertEquals(new BigDecimal("90.000"),result1.get(1).getSumAmount());
        
        Payment p0 = paymentManager.allPayments().get(0);
        assertTrue(p0.getSended());
        assertTrue("platba zkouska1".equals(p0.getMessage()));
        assertEquals(new BigDecimal("10.000"), p0.getAmount());
        assertTrue(LocalDate.now().equals(p0.getDate()));
        assertTrue(1L == p0.getId());
        
        //System.out.print(p0.getReceiver() + " == "+a1);
        
        assertTrue(a1.equals(p0.getReceiver()));
        assertTrue(a2.equals(p0.getSender()));

        LocalDate time = LocalDate.now();
        
        Payment p1 = new Payment(new BigDecimal("20.0"), time, "New one", true,  a1, a2);
        manager.createPayment(p1);
        //paymentManager.updatePayment(p1);
        p0 = manager.findPayment(3L);

        assertFalse(null == p0.getMessage());
        assertEquals("New one", p0.getMessage());

        assertNotEquals(new BigDecimal("5.0"), p0.getAmount());
        assertEquals(new BigDecimal("20.000"), p0.getAmount());

        assertNotEquals(null, p0.getDate());
        assertEquals(time, p0.getDate());
        
        
        assertTrue(p0.isSended());

        assertTrue(3L == p0.getId());
       
    }

    @org.junit.Test
    public void testEmptyUpgrade()
    {
        PaymentManager paymentManager = new PaymentManagerImpl(dataSource);
        try {
            paymentManager.updatePayment(null);
            fail();
        } catch (IllegalArgumentException ex) {
            // should be fired
        } catch (Exception ex) {
            fail();
        }
    }
    @org.junit.Test
    public void testFindPaymentsByAccount(){
        
        PaymentManager paymentManager = new PaymentManagerImpl(dataSource);
        Account account1 = newAccount("Franta","Vizl", new BigDecimal("0"), "800/0100",false);
        Account account2 = newAccount("Franta","Vizl", new BigDecimal("0"), "800/0100",false);
        managerAcc.createAccount(account1);
        managerAcc.createAccount(account2);
        
        Payment p0 = new Payment(new BigDecimal("5.000"), LocalDate.now(), "platba 1", false, account1, account2);
        Payment p1 = new Payment(new BigDecimal("5.000"), LocalDate.now(), "platba 2", false, account2, account1);
        Payment p2 = new Payment(new BigDecimal("5.000"), LocalDate.now(), "platba 3", false, account1, account2);
        Payment p3 = new Payment(new BigDecimal("5.000"), LocalDate.now(), "platba 4", false, account2, account1);
        Payment p4 = new Payment(new BigDecimal("5.000"), LocalDate.now(), "platba 5", false, account1, account2);
       
        try {
            paymentManager.createPayment(p0);
            paymentManager.createPayment(p1);
            paymentManager.createPayment(p2);
            paymentManager.createPayment(p3);
            paymentManager.createPayment(p4);
        } catch (IllegalArgumentException ex){
            // good
        } catch (Exception ex) {
            fail();
        }
        
        
    }
    @org.junit.Test
    public void testFindPayment() throws Exception {
        PaymentManager paymentManager = new PaymentManagerImpl(dataSource);

        Account account1 = newAccount("Franta","Vizl", new BigDecimal("0"), "800/0100",false);
        Account account2 = newAccount("Franta","Vizl", new BigDecimal("0"), "800/0100",false);
        managerAcc.createAccount(account1);
        managerAcc.createAccount(account2);
        
        Payment p0 = new Payment(new BigDecimal("5.000"), LocalDate.now(), "platba 1", false, account1, account2);
        Payment p1 = new Payment(new BigDecimal("5.000"), LocalDate.now(), "platba 2", false, account2, account1);
        Payment p2 = new Payment(new BigDecimal("5.000"), LocalDate.now(), "platba 3", false, account1, account2);
        Payment p3 = new Payment(new BigDecimal("5.000"), LocalDate.now(), "platba 4", false, account2, account1);
        Payment p4 = new Payment(new BigDecimal("5.000"), LocalDate.now(), "platba 5", false, account1, account2);

        try {
            paymentManager.createPayment(p0);
            paymentManager.createPayment(p1);
            paymentManager.createPayment(p2);
            paymentManager.createPayment(p3);
            paymentManager.createPayment(p4);
        } catch (IllegalArgumentException ex){
            // good
        } catch (Exception ex) {
            fail();
        }

        assertTrue(p0.equals(paymentManager.findPayment(1L)));
        assertTrue(p1.equals(paymentManager.findPayment(2L)));
        assertTrue(p2.equals(paymentManager.findPayment(3L)));
        assertTrue(p3.equals(paymentManager.findPayment(4L)));
        assertTrue(p4.equals(paymentManager.findPayment(5L)));
    }

    @org.junit.Test
    public void testFindPaymentOutOfBound() throws Exception {
        PaymentManager paymentManager = new PaymentManagerImpl(dataSource);
        try {
            paymentManager.findPayment(8L);
            fail();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    @org.junit.Test
    public void testAllPayments() throws Exception {
        PaymentManager paymentManager = new PaymentManagerImpl(dataSource);
        ArrayList<Payment> payments = new ArrayList<Payment>();

        Account a1 = newAccount("Franta","Vizl", new BigDecimal("0"), "800/0100",false);
        Account a2 = newAccount("Franta","Vizl", new BigDecimal("0"), "800/0100",false);
        managerAcc.createAccount(a1);
        managerAcc.createAccount(a2);
        Payment p0 = new Payment(new BigDecimal("5.000"), LocalDate.now(), "platba 1", false, a1, a2);
        Payment p1 = new Payment(new BigDecimal("5.000"), LocalDate.now(), "platba 2", false, a1, a2);
        Payment p2 = new Payment(new BigDecimal("5.000"), LocalDate.now(), "platba 3", false, a1, a2);
        Payment p3 = new Payment(new BigDecimal("5.000"), LocalDate.now(), "platba 4", false, a1, a2);
        Payment p4 = new Payment(new BigDecimal("5.000"), LocalDate.now(), "platba 5", false, a1, a2);

        paymentManager.createPayment(p0);
        paymentManager.createPayment(p1);
        paymentManager.createPayment(p2);
        paymentManager.createPayment(p3);
        paymentManager.createPayment(p4);

        payments.add(p0);
        payments.add(p1);
        payments.add(p2);
        payments.add(p3);
        payments.add(p4);

        assert(payments.equals(paymentManager.allPayments()));
    }
    private static Account newAccount(String birthName, String givenName, BigDecimal sumAmount, String accountNumber, boolean wasDeleted) {
        Account account = new Account();
        account.setBirthName(birthName);
        account.setGivenName(givenName);
        account.setSumAmount(sumAmount);
        account.setAccountNumber(accountNumber);
        account.setWasDeleted(wasDeleted);
        return account;
    }
    private static Payment newPayment(BigDecimal amount,LocalDate date,String message ,boolean sended,Account reciever,Account sender){
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setDate(date);
        payment.setMessage(message);
        payment.setReceiver(reciever);
        payment.setSender(sender);
        payment.setSended(sended);
        
        return payment;
    }
    
}