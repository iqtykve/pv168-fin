package cz.fi.muni.pv168;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.derby.jdbc.EmbeddedDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.List;

public class Main {

    final static Logger log = LoggerFactory.getLogger(Main.class);

    public static DataSource createMemoryDatabase() {
        log.info("Vytvoreni databaze");
        BasicDataSource bds = new BasicDataSource();
        //set JDBC driver and URL
        bds.setDriverClassName(EmbeddedDriver.class.getName());
        bds.setUrl("jdbc:derby:memory:payment;create=true");
        //populate db with tables and data
        new ResourceDatabasePopulator(
                new ClassPathResource("schema-javadb.sql"),
                new ClassPathResource("test-data.sql"))
                .execute(bds);
        return bds;
    }

    public static void main(String[] args) throws AccountException {

        log.info("zaciname");

        DataSource dataSource = createMemoryDatabase();
        AccountManager accManager = new AccountManagerImpl(dataSource);
        PaymentManager paymentManager = new PaymentManagerImpl(dataSource);
        
        List<Payment> allPayment = paymentManager.allPayments();
        List<Account> allAcc = accManager.findAllAccount();
        System.out.println("allAccount = " + allAcc);
        System.out.println("allPayment = " + allPayment);

    }

}
