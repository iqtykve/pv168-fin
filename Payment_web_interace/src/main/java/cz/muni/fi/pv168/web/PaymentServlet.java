package cz.muni.fi.pv168.web;

import cz.fi.muni.pv168.Account;
import cz.fi.muni.pv168.AccountManager;
import cz.fi.muni.pv168.Payment;

import cz.fi.muni.pv168.PaymentManager;
import cz.fi.muni.pv168.ServiceFailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;


@WebServlet(PaymentServlet.URL_MAPPING + "/*")
public class PaymentServlet extends HttpServlet {

    private static final String LIST_JSP = "/list.jsp";
    public static final String URL_MAPPING = "/payment";

    private final static Logger log = LoggerFactory.getLogger(PaymentServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("GET ...");
        showAccountPaymentList(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //support non-ASCII characters in form
        request.setCharacterEncoding("utf-8");
        //action specified by pathInfo
        String action = request.getPathInfo();
        log.debug("POST ... {}",action);
        switch (action) {
            case "/sendPayment":
                try {
                    Long id = Long.valueOf(request.getParameter("id"));
                    
                    Payment payment = getPaymentManager().findPayment(id);
                    payment.setSended(true);
                    getPaymentManager().updatePayment(payment);
                    
                    response.sendRedirect(request.getContextPath()+URL_MAPPING);
                    return;
                } catch (ServiceFailureException e) {
                    log.error("Cannot send payment", e);
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }
            case "/addPayment":
                String message = request.getParameter("message");
                if(isNumeric(request.getParameter("sender")) && request.getParameter("sender").length()!=0 && isNumeric(request.getParameter("receiver")) && request.getParameter("receiver").length()!=0 &&  request.getParameter("amount").length()!=0 && isNumeric(request.getParameter("amount"))){
                   
                   int sender = Integer.parseInt(request.getParameter("sender"));
                   int receiver = Integer.parseInt(request.getParameter("receiver"));
                   if(sender == receiver){
                       request.setAttribute("chybaPayment", "Peníze nelze poslat sám sobě, jak by to vypadalo");
                        log.debug("form data invalid");
                        showAccountPaymentList(request, response);
                        return;
                   }
                   BigDecimal amount = new BigDecimal(request.getParameter("amount"));
                   
                  try{
                    Account accSender = getAccountManager().findAccount(sender);
                    Account accReceiver = getAccountManager().findAccount(receiver);
                    
                    Payment payment = new Payment(amount, LocalDate.now(), message, false, accReceiver, accSender);
                    getPaymentManager().createPayment(payment);
                    showAccountPaymentList(request, response);
                    return;
                   }catch(Exception e){
                       request.setAttribute("chybaPayment", "Chybne ID!");
                        log.debug("form data invalid");
                        showAccountPaymentList(request, response);
                   };
                }else{
                    request.setAttribute("chybaPayment", "Je nutné vyplnit všechny hodnoty !");
                    log.debug("form data invalid");
                    showAccountPaymentList(request, response);
                    return;
                }
                
            case "/addUser":
                //getting POST parameters from form
                String birthName = request.getParameter("birthName");
                String givenName = request.getParameter("givenName");
                String accountNumber = request.getParameter("accountNumber");
                BigDecimal sumAmount = new BigDecimal(-10);
                if(request.getParameter("sumAmount").length()!=0 && isNumeric(request.getParameter("sumAmount"))){
                    sumAmount = new BigDecimal(request.getParameter("sumAmount"));
                }
                //form data validity check
                if (sumAmount == null || sumAmount.intValue()<0 || birthName == null || birthName.length() == 0 || givenName == null || givenName.length() == 0 || accountNumber == null || accountNumber.length() == 0) {
                    request.setAttribute("chybaUzivatel", "Je nutné vyplnit všechny hodnoty !");
                    log.debug("form data invalid");
                    showAccountPaymentList(request, response);
                    return;
                }
                //form data processing - storing to database
                try {
                    //Account acc = new Account("Franta","Vizl",new BigDecimal("100"),"800/0100");
                    Account acc = new Account(birthName,givenName,sumAmount,accountNumber);
                    getAccountManager().createAccount(acc);
                    //redirect-after-POST protects from multiple submission
                    log.debug("redirecting after POST");
                    response.sendRedirect(request.getContextPath()+URL_MAPPING);
                    return;
                } catch (ServiceFailureException e) {
                    log.error("Cannot add user", e);
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }
            case "/deleteUser":
                try {
                    int id = Long.valueOf(request.getParameter("id")).intValue();
                    
                    Account acc = getAccountManager().findAccount(id);
                    acc.setWasDeleted(true);
                    getAccountManager().updateAccount(acc);
                    log.debug("redirecting after POST");
                    response.sendRedirect(request.getContextPath()+URL_MAPPING);
                    return;
                } catch (ServiceFailureException e) {
                    log.error("Cannot delete user", e);
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }
            case "/update":
                //TODO
                return;
            default:
                log.error("Unknown action " + action);
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown action " + action);
        }
    }

    private AccountManager getAccountManager() {
        return (AccountManager) getServletContext().getAttribute("accountManager");
    }
    
    private PaymentManager getPaymentManager() {
        return (PaymentManager) getServletContext().getAttribute("paymentManager");
    }

    private void showAccountPaymentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            log.debug("showing table of account");
            request.setAttribute("account", getAccountManager().findAllAccount());
            request.setAttribute("payment", getPaymentManager().allPayments());
            request.getRequestDispatcher(LIST_JSP).forward(request, response);
        } catch (ServiceFailureException e) {
            log.error("Cannot show account", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
