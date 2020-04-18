/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.insert;

import connectData.CustomerDAO;
import connectData.OrderDAO;
import connectData.ProductDAO;
import connectData.getData;
import entity.Account;
import entity.Customer;
import entity.Order;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Darkness_King
 */
public class insertController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String cid = request.getParameter("xcusId");
        getData get = new getData();
        ProductDAO d = new ProductDAO();
        Customer customer = null;
        customer = get.getOneCustomer(Integer.parseInt(cid));
        Order order = (Order) request.getSession().getAttribute("cart");
        if (cid.compareTo("-1") !=0) {
            customer = get.getOneCustomer(Integer.parseInt(cid));
    
        } else {
           
            String customerName = request.getParameter("customerName");
            String customerAddress = request.getParameter("customerAddress");
            String customerPhone = request.getParameter("customerPhone");
            Customer cus = new Customer();
            cus.setName(customerName);
            cus.setAddress(customerAddress);
            cus.setPhone(customerPhone);
            CustomerDAO customerDAO = new CustomerDAO();
            customerDAO.insert(cus);
            customer = get.getOneCustomer(customerDAO.getLastID());
        } 
        d.UpdateProduct(order);
        order.setCustomer(customer);
        OrderDAO db = new OrderDAO();
        Account a=(Account)request.getSession().getAttribute("userAcount");
        db.xinsert(order,a.getUsername());
        request.getSession().setAttribute("cart", null);
        response.sendRedirect("getProduct");
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
