/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import Model.Account;
import java.util.List;

/**
 *
 * @author VLT
 */
public interface AccountService {
    List<Account> getListAccounts();  
    
    void addAccount(Account account);
    
    void editAccout(String oldUsername, Account account );
    
    boolean findAccountByUsername(String username);
}
