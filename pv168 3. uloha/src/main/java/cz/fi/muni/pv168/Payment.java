package cz.fi.muni.pv168;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Created by lubomir.viluda on 16.3.2016.
 */
public class Payment {

    private BigDecimal amount;
    private LocalDate date;
    private String message;
    private boolean sended;
    private Long id;
    private Account receiver;
    private Account sender;


    public Payment() {
        this.amount = null;
        this.date = null;
        this.message = null;
        this.sended = false;
        this.id = null;
        this.receiver = null;
        this.sender = null;
    }

    public Payment(BigDecimal amount, LocalDate date, String message, boolean sended, Account receiver, Account sender) {
        this.amount = amount;
        this.date = date;
        this.message = message;
        this.sended = sended;
        this.receiver = receiver;
        this.sender = sender;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setSended(boolean sended){
        this.sended = sended;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSended() {
        return sended;
    }

    public boolean getSended() {
        return sended;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.amount);
        hash = 47 * hash + Objects.hashCode(this.date);
        hash = 47 * hash + Objects.hashCode(this.message);
        hash = 47 * hash + (this.sended ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.receiver);
        hash = 47 * hash + Objects.hashCode(this.sender);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;

        Payment payment = (Payment) o;
        if(getId().equals(payment.getId())){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Payment{id=" + id + ", amount=" + amount + ", date=" + date + ", message=" + message + ", sended='" + sended + ", sender=" + sender + ", reciever: " + receiver + "}";
    }
}