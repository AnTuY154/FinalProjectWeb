/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connectData.getData;
import entity.orderTemp;
import entity.xThongKe;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Darkness_King
 */
public class ThongKe extends HttpServlet {

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
            out.println("<title>Servlet ThongKe</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ThongKe at " + request.getContextPath() + "</h1>");
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
        getData g = new getData();
        if (request.getSession().getAttribute("type") == null) {
            request.getSession().setAttribute("type", -1);
        } else {
            String type = request.getParameter("typeTK");
            if (type != null) {
                int xtype = Integer.parseInt(type);
                request.getSession().setAttribute("type", xtype);
                
            }
            String date = request.getParameter("date");

            if (date != null) {

                if (date.trim().compareToIgnoreCase("null") != 0) {
                    if ((Integer) request.getSession().getAttribute("type") == 0) {

                        int count = g.countBydate(date);
                        int pagesize = 10;
                        String raw_pageindex = request.getParameter("page");
                        if (raw_pageindex == null) {
                            raw_pageindex = "1";
                        }
                        int pageindex = Integer.parseInt(raw_pageindex);
                        int pagecount = (count % pagesize == 0) ? count / pagesize : count / pagesize + 1;
                        ArrayList<xThongKe> Tk = g.getAllOrderByDate(pageindex, pagesize, date);
                        float lai = g.layLaiNgay(date);
                        request.setAttribute("pagecount", pagecount);
                        request.setAttribute("pageindex", pageindex);
                        request.getSession().setAttribute("xdate", date);
                        request.getSession().setAttribute("ztype", type);
                        request.getSession().setAttribute("lai", lai);
                        request.setAttribute("ThongKe", Tk);
                    } else if ((Integer) request.getSession().getAttribute("type") == 1) {
                        int count = g.countByMonth(Integer.parseInt(date));
                        int pagesize = 10;
                        String raw_pageindex = request.getParameter("page");
                        if (raw_pageindex == null) {
                            raw_pageindex = "1";
                        }
                        int pageindex = Integer.parseInt(raw_pageindex);
                        int pagecount = (count % pagesize == 0) ? count / pagesize : count / pagesize + 1;
                        ArrayList<xThongKe> Tk = g.getAllOrderByMonth(pageindex, pagesize, date);
                        float lai = g.layLai(date);
                        request.setAttribute("pagecount", pagecount);
                        request.setAttribute("pageindex", pageindex);
                        request.getSession().setAttribute("xdate", date);
                        request.getSession().setAttribute("ztype", type);
                        request.getSession().setAttribute("lai", lai);
                        request.setAttribute("ThongKe", Tk);
                    } else if ((Integer) request.getSession().getAttribute("type") == 2) {
                        String todate = request.getParameter("xdate");
                        int count = g.countFromTo(date, todate);
                        int pagesize = 10;
                        String raw_pageindex = request.getParameter("page");
                        if (raw_pageindex == null) {
                            raw_pageindex = "1";
                        }
                        int pageindex = Integer.parseInt(raw_pageindex);
                        int pagecount = (count % pagesize == 0) ? count / pagesize : count / pagesize + 1;
                        ArrayList<xThongKe> Tk = g.getAllOrderFromTo(pageindex, pagesize, date, todate);
                        float lai = g.layLaiTuan( date, todate);
                        request.setAttribute("pagecount", pagecount);
                        request.setAttribute("pageindex", pageindex);
                        request.getSession().setAttribute("xdate", date);
                        request.getSession().setAttribute("todate", todate);
                        request.getSession().setAttribute("ztype", type);
                        request.getSession().setAttribute("lai", lai);
                        request.setAttribute("ThongKe", Tk);
                    }
                }
            }
        }
        request.getRequestDispatcher("ThongKe.jsp").forward(request, response);
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
