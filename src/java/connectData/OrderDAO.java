/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectData;

import entity.Order;
import entity.OrderLine;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darkness_King
 */
public class OrderDAO extends DBContext {

    public void xinsert(Order order,String employeeID) {
        try {
            connection.setAutoCommit(false);
       
            String sql_insert_order = "insert into OrderOut(OrderDate,CustomerID,Total,Type,EmployeeID)\n"
                    + "values(GETDATE(), ? ,?,?,?)";
            PreparedStatement ps_insert_order
                    = connection.prepareStatement(sql_insert_order);
            ps_insert_order.setInt(1, order.getCustomer().getId());
            ps_insert_order.setFloat(2, order.getTotal());
            ps_insert_order.setInt(3, 1);
            ps_insert_order.setString(4,employeeID);
            ps_insert_order.executeUpdate();
            //select orderid (IDENTITY)
            String sql_select_orderid = "SELECT @@IDENTITY as oid";
            PreparedStatement ps_select_orderid
                    = connection.prepareStatement(sql_select_orderid);
            ResultSet rs = ps_select_orderid.executeQuery();
            if (rs.next()) {
                order.setId(rs.getInt("oid"));
            }
            //insert order lines
            String sql_insert_line = "INSERT INTO [OrderOutDetail]\n"
                    + "           ([OderID]\n"
                    + "           ,[ProductID]\n"
                    + "           ,[Quantity]\n"
                    + "           ,[Total]\n"
                    + "        \n"
                    + "           ,[ProductPrice])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?)";
            for (OrderLine line : order.getLines()) {
                PreparedStatement ps_insert_line
                        = connection.prepareStatement(sql_insert_line);
                ps_insert_line.setInt(1, order.getId());
                ps_insert_line.setNString(2, line.getProduct().getId());
                ps_insert_line.setFloat(3, line.getQuantity());
                ps_insert_line.setFloat(4, line.getTotal());
                ps_insert_line.setFloat(5, line.getPrice());
                ps_insert_line.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void insertTemp(Order order,String emID) {
        try {
            connection.setAutoCommit(false);
            String sql_insert_order = "insert into OrderOut(OrderDate,CustomerID,Total,Type,Note,EmployeeID)\n"
                    + "values(?, ? ,?,?,?,?)";
            PreparedStatement ps_insert_order
                    = connection.prepareStatement(sql_insert_order);
            ps_insert_order.setDate(1, order.getDate());
            ps_insert_order.setInt(2, order.getCustomer().getId());
            ps_insert_order.setFloat(3, order.getTotal());
            ps_insert_order.setInt(4, 0);
            ps_insert_order.setNString(5, order.getNote());
            ps_insert_order.setNString(6, emID);
            ps_insert_order.executeUpdate();
            //select orderid (IDENTITY)
            String sql_select_orderid = "SELECT @@IDENTITY as oid";
            PreparedStatement ps_select_orderid
                    = connection.prepareStatement(sql_select_orderid);
            ResultSet rs = ps_select_orderid.executeQuery();
            if (rs.next()) {
                order.setId(rs.getInt("oid"));
            }
            //insert order lines
            String sql_insert_line = "INSERT INTO [OrderOutDetail]\n"
                    + "           ([OderID]\n"
                    + "           ,[ProductID]\n"
                    + "           ,[Quantity]\n"
                    + "           ,[Total]\n"
                    + "        \n"
                    + "           ,[ProductPrice])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?)";
            for (OrderLine line : order.getLines()) {
                PreparedStatement ps_insert_line
                        = connection.prepareStatement(sql_insert_line);
                ps_insert_line.setInt(1, order.getId());
                ps_insert_line.setNString(2, line.getProduct().getId());
                ps_insert_line.setFloat(3, line.getQuantity());
                ps_insert_line.setFloat(4, line.getTotal());
                ps_insert_line.setFloat(5, line.getPrice());
                ps_insert_line.executeUpdate();
            }
          
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void UpdateOrder(int id) {
        try {
            connection.setAutoCommit(false);
            String sql_insert_order = " UPDATE OrderOut SET Type=1\n"
                    + "WHERE OrderID=?";
            PreparedStatement ps_insert_order
                    = connection.prepareStatement(sql_insert_order);
            ps_insert_order.setInt(1, id);

            ps_insert_order.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void DeleteOrder(int id) {
        try {
            connection.setAutoCommit(false);
            String sql_delete_orderdetail = "DELETE FROM OrderOutDetail WHERE OrderOutDetail.OderID=?";
            PreparedStatement ps_delete_orderdetail
                    = connection.prepareStatement(sql_delete_orderdetail);
            ps_delete_orderdetail.setInt(1, id);
            ps_delete_orderdetail.executeUpdate();
            
             String sql_delete_order = "DELETE FROM OrderOut WHERE OrderOut.OrderID=?";
            PreparedStatement ps_delete_order
                    = connection.prepareStatement(sql_delete_order);
            ps_delete_order.setInt(1, id);
            ps_delete_order.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
}
