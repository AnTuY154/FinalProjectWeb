/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectData;

import entity.Account;
import entity.NewClass;
import entity.Order;
import entity.OrderLine;
import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darkness_King
 */
public class ProductDAO extends DBContext {

    public void UpdateProduct(Order o) {
        try {
            connection.setAutoCommit(false);
            for (OrderLine or : o.getLines()) {
                float a = getQuantityOneProduct(or.getProduct().getId()) - or.getQuantity();
                String sql = "UPDATE Product\n"
                        + "SET Quantity = ?\n"
                        + "WHERE ProductID= ?";
                PreparedStatement ps_update
                        = connection.prepareStatement(sql);
                ps_update.setFloat(1, a);
                ps_update.setString(2, or.getProduct().getId());
                ps_update.executeUpdate();
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

    public void UpdateFullProduct(Product p) {
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE Product\n"
                    + "                        SET ProductName=?,CostPrice=?,SellingPrice=?,Quantity=?,Note=?\n"
                    + "                        WHERE ProductID= ?";
            PreparedStatement ps_update
                    = connection.prepareStatement(sql);
            ps_update.setString(1, p.getName());
            ps_update.setFloat(2, p.getCostPrice());
            ps_update.setFloat(3, p.getPrice());
            ps_update.setFloat(4, p.getQuantity());
            ps_update.setString(5, p.getNote());
            ps_update.setString(6, p.getId());
            ps_update.executeUpdate();

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

    public void UpdateProductTemp(NewClass n) {
        try {
            connection.setAutoCommit(false);

            float a = getQuantityOneProduct(n.getProductID()) - n.getQuantity();
            String sql = "UPDATE Product\n"
                    + "SET Quantity = ?\n"
                    + "WHERE ProductID= ?";
            PreparedStatement ps_update
                    = connection.prepareStatement(sql);
            ps_update.setFloat(1, a);
            ps_update.setString(2, n.getProductID());
            ps_update.executeUpdate();

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

    public float getQuantityOneProduct(String id) {
        try {
            String sql = "select p.Quantity from Product p where p.ProductID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getFloat("Quantity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void xinsert(Product p) {
        try {
            connection.setAutoCommit(false);

            String sql_insert_order = "	 insert into Product\n"
                    + "						 values(?,?,?,?,?,?)";
            PreparedStatement ps_insert_order
                    = connection.prepareStatement(sql_insert_order);
            ps_insert_order.setString(1, p.getId());
            ps_insert_order.setString(2, p.getName());
            ps_insert_order.setFloat(3, p.getCostPrice());
            ps_insert_order.setFloat(4, p.getPrice());
            ps_insert_order.setFloat(5, p.getQuantity());
            ps_insert_order.setString(6, p.getNote());
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
}
