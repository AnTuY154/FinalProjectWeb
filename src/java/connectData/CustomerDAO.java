/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectData;

import entity.Account;
import entity.Customer;
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
public class CustomerDAO extends DBContext {

    public void insert(Customer customer) {
       
        try {
            connection.setAutoCommit(false);
            String sql_insert_customer = "INSERT INTO [Customer]\n"
                    + "           ([CustomerName]\n"
                    + "           ,[Phone]\n"
                    + "           ,[Address])\n"
                    + "     VALUES\n"
                    + "           (?,?,?)";
         
            PreparedStatement ps_insert_order
                    = connection.prepareStatement(sql_insert_customer);
             
            ps_insert_order.setNString(1, customer.getName());
            ps_insert_order.setString(2, customer.getPhone());
            ps_insert_order.setNString(3, customer.getAddress());
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

    public int getLastID() {
        try {
            connection.setAutoCommit(false);
            String sql = "SELECT MAX(CustomerID) as lastID\n"
                    + "  FROM Customer";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                return rs.getInt("lastID");
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
