/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filter;

import connectData.getData;
import entity.Account;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ulti.CookiHelper;
import ulti.SessionHelper;

/**
 *
 * @author Darkness_King
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String reqURI = req.getRequestURI();
        if (!reqURI.endsWith("/Login") && isAuthenticated(req)==false) {
          
            resp.sendRedirect("Login");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isAuthenticated(HttpServletRequest request) {
        SessionHelper session = new SessionHelper();
        Account a = session.getAccountFromSession(request.getSession());
        if (a != null) {
            return true;
        } else {
            String userName = null;
            String password = null;
            CookiHelper cooki = new CookiHelper();
            Cookie[] cookie = request.getCookies();
            if (cookie == null) {
                return false;
            }
            for (Cookie cookie1 : cookie) {
                if (cookie1.getName().equals("userName")) {
                    userName = cookie1.getValue();
                }
                if (cookie1.getName().equals("password")) {
                    password = cookie1.getValue();
                }
                if (userName != null && password != null) {
                    break;
                }
            }
            if (userName != null && password != null) {
                getData d = new getData();
                a = d.getAccount(userName, password);
                if (a != null) {
                    SessionHelper s = new SessionHelper();
                    s.addAccounttoSession(request.getSession(), a);
                    return true;
                } else {
                    return false;
                }

            }

            return false;
        }
    }
}
