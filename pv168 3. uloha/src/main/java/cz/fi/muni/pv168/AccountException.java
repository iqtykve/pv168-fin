/**
 *
 * @author Ondra
 */
package cz.fi.muni.pv168;

public class AccountException extends Exception{
    public AccountException() {
        super();
    }

    public AccountException(String message) {
        super(message);
    }

    public AccountException(String message, Throwable cause) {
        super(message, cause);
    }
}