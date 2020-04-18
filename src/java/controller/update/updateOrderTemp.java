/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.update;

import connectData.OrderDAO;
import connectData.ProductDAO;
import connectData.getData;
import entity.NewClass;
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
public class updateOrderTemp extends HttpServlet {

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
        getData a = new getData();
        
        HttpSession session = request.getSession();
        String orderID = request.getParameter("orderID");
        String type = request.getParameter("mess");
        OrderDAO orderDAO = new OrderDAO();
        ProductDAO d=new ProductDAO();
        if (type.compareTo("1") == 0) {
            orderDAO.UpdateOrder(Integer.parseInt(orderID));
           ArrayList<NewClass> n = a.getProductTempById(Integer.parseInt(orderID));
           for(int i=0;i<n.size();i++){
           d.UpdateProductTemp(n.get(i));
           }
           
        } else if (type.compareTo("0") == 0) {
            orderDAO.DeleteOrder(Integer.parseInt(orderID));
        }
        int size = a.count(0);
        session.setAttribute("tempsize", size);
        response.sendRedirect("getOrderTemp");
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
        processRequest(request, response);
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
