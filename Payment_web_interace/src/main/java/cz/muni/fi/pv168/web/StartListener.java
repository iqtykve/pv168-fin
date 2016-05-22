package cz.muni.fi.pv168.web;

import cz.fi.muni.pv168.AccountManagerImpl;
import cz.fi.muni.pv168.PaymentManagerImpl;
import cz.fi.muni.pv168.Main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class StartListener implements ServletContextListener {

    private final static Logger log = LoggerFactory.getLogger(StartListener.class);

    @Override
    public void contextInitialized(ServletContextEvent ev) {
        log.info("webová aplikace inicializována");
        ServletContext servletContext = ev.getServletContext();
        DataSource dataSource = Main.createMemoryDatabase();
        servletContext.setAttribute("paymentManager", new PaymentManagerImpl(dataSource));
        servletContext.setAttribute("accountManager", new AccountManagerImpl(dataSource));
        log.info("vytvořeny manažery a uloženy do atributů servletContextu");
    }

    @Override
    public void contextDestroyed(ServletContextEvent ev) {
        log.info("aplikace končí");
    }
}
