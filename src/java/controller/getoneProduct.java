/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connectData.getData;
import entity.Customer;
import entity.Order;
import entity.OrderLine;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Darkness_King
 */
public class getoneProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet getoneProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet getoneProduct at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("cart");
        String proId = request.getParameter("prodelete");
        String subtmit = request.getParameter("xsubmit");
        if (proId.compareTo("0") != 0 || subtmit.compareTo("1") == 0) {
            if (order != null) {
                if (proId.compareTo("0") != 0) {
                    for (int i = 0; i < order.getLines().size(); i++) {
                        if (order.getLines().get(i).getProduct().getId().compareTo(proId) == 0) {
                            order.getLines().remove(i);
                            break;
                        }
                    }
                }
                for (int i = 0; i < order.getLines().size(); i++) {
                    String l = request.getParameter(order.getLines().get(i).getProduct().getId());
                    String price = request.getParameter(order.getLines().get(i).getProduct().getId() + "currentPrice");
                    if (l != null && l != "" && price != null && price != "") {
                        order.getLines().get(i).setQuantity(Float.parseFloat(l));
                        order.getLines().get(i).setPrice(Float.parseFloat(price));
                    }
                }
                if (order.getLines().size() == 0) {
                    session.setAttribute("cart", null);
                } else {
                    session.setAttribute("cart", order);
                }
                if (subtmit.compareTo("1") == 0) {
                    session.setAttribute("cart", order);
                    response.sendRedirect("checkOut");
                } else {
                    response.sendRedirect("getProduct");
                }
            }
        } else {
            String name = request.getParameter("product");
            getData product = new getData();
            Product p = product.getOneProduct(name);
           
            boolean isExi = false;
            if (order == null) {
                order = new Order();
                OrderLine o = new OrderLine();
                o.setProduct(p);
                o.setLine(1);
                o.setPrice(p.getPrice());
               
                order.getLines().add(o);
            } else {
                for (int i = 0; i < order.getLines().size(); i++) {
                    String l = request.getParameter(order.getLines().get(i).getProduct().getId());
                    String price = request.getParameter(order.getLines().get(i).getProduct().getId() + "currentPrice");   
                   
                    if (l.matches("[0-9]+.{0,}[0-9]{0,}")==true) {
                        order.getLines().get(i).setQuantity(Float.parseFloat(l));
                        order.getLines().get(i).setPrice(Float.parseFloat(price));
                    } else {
                        order.getLines().get(i).setQuantity(0);
                    }
                }
                for (int i = 0; i < order.getLines().size(); i++) {
                    if (order.getLines().get(i).getProduct().getId().compareTo(p.getId()) == 0) {
                        isExi = true;
                        break;
                    }
                }
                if (isExi == false) {
                    OrderLine o = new OrderLine();
                    o.setProduct(p);
                    o.setPrice(p.getPrice());
                    o.setQuantity(0);
                    o.setLine(order.getLines().get(order.getLines().size() - 1).getLine() + 1);
                    order.getLines().add(o);
                }
            }

            session.setAttribute("cart", order);
            response.sendRedirect("product.jsp");
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
