/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ulti;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author Darkness_King
 */
public class CookiHelper {

    public static void sendCooki(HttpServletResponse response, String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 3600);
        response.addCookie(cookie);
    }

    public static void removeCookie(HttpServletResponse response, String key) {
        Cookie cooky = new Cookie(key, "");
        cooky.setMaxAge(-1);
        response.addCookie(cooky);
    }
}
