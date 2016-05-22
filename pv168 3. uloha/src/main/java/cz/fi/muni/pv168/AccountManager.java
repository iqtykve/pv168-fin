/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package cz.fi.muni.pv168;
import java.util.List;

/**
 *
 * @author Ondra
 */


public interface AccountManager {
    void createAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(Account account);
    Account findAccount(int id);
    List<Account> findAllAccount();
}
