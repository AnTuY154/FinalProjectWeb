/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ulti;

import entity.Account;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Darkness_King
 */
public class SessionHelper {
    public static void addAccounttoSession(HttpSession session,Account acount){
        session.setAttribute("userAcount", acount);
    }
    public static Account getAccountFromSession(HttpSession session){
        return (Account) session.getAttribute("userAcount");
    }
}
