/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectData;

import entity.Account;
import entity.Customer;
import entity.NewClass;
import entity.Order;
import entity.OrderLine;
//import entity.OrderTemp;
import entity.Product;
import entity.xThongKe;
import entity.orderTemp;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Darkness_King
 */
public class getData extends DBContext {

    public Account getAccount(String userName, String password) {
        //  Account a = new Account();
        Account a = null;
        try {
            String sql = "select * from Account where Account.userName='" + userName + "' AND Account.Password='" + password + "'";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                a = new Account();
                a.setUsername(rs.getString("userName"));
                a.setPassword(rs.getString("Password"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public ArrayList<Product> getAllProdcut() {
        ArrayList<Product> xproduct = new ArrayList<>();
        try {
            String sql = "  select * from Product p  where p.Quantity > 0 order by p.ProductName";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product a = new Product();
                a.setId(rs.getNString("ProductID"));
                a.setName(rs.getNString("ProductName"));
                a.setPrice(rs.getFloat("SellingPrice"));
                a.setNote(rs.getNString("Note"));
                xproduct.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return xproduct;

    }

    public ArrayList<NewClass> getProductTempById(int id) {
        ArrayList<NewClass> pro = new ArrayList<>();
        try {
            String sql = "  select * from OrderOutDetail o where o.OderID= ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                NewClass n = new NewClass();
                n.setOderID(rs.getInt("OderID"));
                n.setQuantity(rs.getFloat("Quantity"));
                n.setProductID(rs.getString("ProductID"));
                pro.add(n);

            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pro;
    }

    public Product getOneProduct(String Id) {
        Product p;
        try {
            String sql = "select * from Product p where p.ProductID= ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product a = new Product();
                a.setId(rs.getNString("ProductID"));
                a.setName(rs.getNString("ProductName"));
                a.setPrice(rs.getFloat("SellingPrice"));
                a.setNote(rs.getNString("Note"));
                a.setCostPrice(rs.getFloat("CostPrice"));
                a.setQuantity(rs.getFloat("Quantity"));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Customer getOneCustomer(int Id) {

        try {
            String sql = "select * from Customer c where c.CustomerID=" + Id + "";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("CustomerID"));
                c.setName(rs.getNString("CustomerName"));
                c.setAddress(rs.getNString("Address"));
                c.setPhone(rs.getString("Phone"));
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Customer> getAllCustomer() {
        ArrayList<Customer> xcustomer = new ArrayList<>();
        try {
            String sql = "select * from Customer c order by c.CustomerName";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("CustomerID"));
                c.setName(rs.getNString("CustomerName"));
                c.setAddress(rs.getNString("Address"));
                c.setPhone(rs.getString("Phone"));
                xcustomer.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return xcustomer;
    }

    public int countNumberOfOrderDetail(int a) {
        String sql = "SELECT COUNT(*) as rownum FROM\n"
                + "(SELECT        OrderOut.OrderID\n"
                + "FROM            OrderOut INNER JOIN\n"
                + "                         OrderOutDetail ON OrderOut.OrderID = OrderOutDetail.OderID\n"
                + "						 where type=" + a + ") abc";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("rownum");
            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public int count(int a) {
        String sql = "SELECT COUNT(*) as rownum FROM OrderOut where type =" + a + "";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("rownum");
            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public int countBydate(String date) {
        String sql = "select count(*) as rownum from(\n"
                + "SELECT        OrderOut.OrderDate,,OrderOut.Type\n"
                + "FROM            OrderOut INNER JOIN\n"
                + "                         OrderOutDetail ON OrderOut.OrderID = OrderOutDetail.OderID) abc\n"
                + "						 where OrderDate='2019-11-11' and type=1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("rownum");
            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int countByMonth(int month) {
        String sql = " select count(*) as rownum from(\n"
                + "                SELECT        OrderOut.OrderDate,,OrderOut.Type\n"
                + "                FROM            OrderOut INNER JOIN\n"
                + "                                        OrderOutDetail ON OrderOut.OrderID = OrderOutDetail.OderID) abc\n"
                + "                					 where MONTH(ORderDate)= ? and type=1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, month);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("rownum");
            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int countFromTo(String from, String to) {
        String sql = "  select count(*) as rownum from(\n"
                + "          SELECT        OrderOut.OrderDate,OrderOut.Type\n"
                + "                            FROM            OrderOut INNER JOIN\n"
                + "                                                       OrderOutDetail ON OrderOut.OrderID = OrderOutDetail.OderID) abc\n"
                + "                          					 where OrderDate >=? AND OrderDate <= ? and type=1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, from);
            ps.setString(2, to);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("rownum");
            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<orderTemp> getTempOrder(int pageindex, int pagesize) {
        ArrayList<orderTemp> orderTemp = new ArrayList<>();
        try {
            String sql = " select * from \n"
                    + "( SELECT *,ROW_NUMBER() OVER (ORDER BY CustomerID ASC) as row_num FROM\n"
                    + " \n"
                    + " ( SELECT        Customer.*, OrderOut.OrderID,  OrderOut.Total, OrderOut.OrderDate, OrderOut.Type, OrderOut.Note, Product.ProductName, OrderOutDetail.ProductPrice, OrderOutDetail.Quantity\n"
                    + "FROM            Customer INNER JOIN\n"
                    + "                         OrderOut ON Customer.CustomerID = OrderOut.CustomerID INNER JOIN\n"
                    + "                         OrderOutDetail ON OrderOut.OrderID = OrderOutDetail.OderID INNER JOIN\n"
                    + "                         Product ON OrderOutDetail.ProductID = Product.ProductID\n"
                    + "						 where type=0\n"
                    + "						) abc)xyz\n"
                    + "\n"
                    + "						WHERE row_num >= (? - 1)*? +1 AND row_num<= ? * ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, pageindex);
            statement.setInt(2, pagesize);
            statement.setInt(3, pageindex);
            statement.setInt(4, pagesize);
            ResultSet rs = statement.executeQuery();
            int id = -1;
            orderTemp oTemp = null;
            ArrayList<OrderLine> orderLines = null;
            while (rs.next()) {
                int xID = rs.getInt("CustomerID");
                String cusName = rs.getNString("CustomerName");
                String phone = rs.getString("Phone");
                String Address = rs.getNString("Address");
                int orderID = rs.getInt("OrderID");
                float price = rs.getFloat("ProductPrice");
                float quantity = rs.getFloat("Quantity");
                Date date = rs.getDate("OrderDate");
                String note = rs.getNString("Note");
                String productName = rs.getNString("ProductName");
                if (xID != id) {
                    if (oTemp != null) {
                        oTemp.setOrderLine(orderLines);
                        orderTemp.add(oTemp);
                    }
                    orderLines = new ArrayList<>();
                    Customer c = new Customer();
                    OrderLine o = new OrderLine();
                    Order order = new Order();
                    oTemp = new orderTemp();
                    c.setAddress(Address);
                    c.setName(cusName);
                    c.setId(xID);
                    c.setPhone(phone);
                    o.setQuantity(quantity);
                    o.setPrice(price);
                    order.setId(orderID);
                    order.setDate(date);
                    order.setNote(note);
                    o.setOrder(order);
                    Product p = new Product();
                    p.setName(productName);
                    p.setPrice(price);
                    o.setProduct(p);
                    orderLines.add(o);
                    oTemp.setCustomer(c);

                    id = xID;
                } else {
                    OrderLine o = new OrderLine();
                    o.setPrice(price);
                    o.setQuantity(quantity);
                    Order order = new Order();
                    order.setId(orderID);
                    order.setDate(date);
                    order.setNote(note);
                    Product p = new Product();
                    p.setName(productName);
                    p.setPrice(price);
                    o.setOrder(order);
                    o.setProduct(p);
                    orderLines.add(o);

                }

            }

            oTemp.setOrderLine(orderLines);
            orderTemp.add(oTemp);

        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderTemp;
    }

    public float layLai(String month) {
        ArrayList<xThongKe> xThongKe = new ArrayList<>();
        float total = 0;
        try {

            String sql = "    select * from ( SELECT *,ROW_NUMBER() OVER (ORDER BY CustomerID ASC) as row_num FROM\n"
                    + "					  ( SELECT        Customer.*, OrderOut.OrderID,  OrderOut.Total, OrderOut.OrderDate, OrderOut.Type, OrderOut.Note, Product.ProductName,Product.CostPrice, OrderOutDetail.ProductPrice, OrderOutDetail.Quantity\n"
                    + "						FROM            Customer INNER JOIN\n"
                    + "											 OrderOut ON Customer.CustomerID = OrderOut.CustomerID INNER JOIN\n"
                    + "										OrderOutDetail ON OrderOut.OrderID = OrderOutDetail.OderID INNER JOIN\n"
                    + "										Product ON OrderOutDetail.ProductID = Product.ProductID\n"
                    + "                 					where MONTH(OrderDate)= ? AND  type=1\n"
                    + "                 				) abc)xyz";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, month);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                xThongKe t = new xThongKe();
                t.setProductName(rs.getString("ProductName"));
                t.setProductPrice(rs.getFloat("ProductPrice"));
                t.setCostPrice(rs.getFloat("CostPrice"));
                t.setQuantity(rs.getFloat("Quantity"));
                xThongKe.add(t);
            }

            for (int i = 0; i < xThongKe.size(); i++) {
                total = total + xThongKe.get(i).tinhLai();
            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public float layLaiNgay(String date) {
        ArrayList<xThongKe> xThongKe = new ArrayList<>();
        float total = 0;
        try {

            String sql = "select * from ( SELECT *,ROW_NUMBER() OVER (ORDER BY CustomerID ASC) as row_num FROM\n"
                    + "					  ( SELECT        Customer.*, OrderOut.OrderID,  OrderOut.Total, OrderOut.OrderDate, OrderOut.Type, OrderOut.Note, Product.ProductName,Product.CostPrice, OrderOutDetail.ProductPrice, OrderOutDetail.Quantity\n"
                    + "						FROM            Customer INNER JOIN\n"
                    + "											 OrderOut ON Customer.CustomerID = OrderOut.CustomerID INNER JOIN\n"
                    + "										OrderOutDetail ON OrderOut.OrderID = OrderOutDetail.OderID INNER JOIN\n"
                    + "										Product ON OrderOutDetail.ProductID = Product.ProductID\n"
                    + "                 					where OrderDate=? and type=1\n"
                    + "                 				) abc)xyz";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, date);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                xThongKe t = new xThongKe();
                t.setProductName(rs.getString("ProductName"));
                t.setProductPrice(rs.getFloat("ProductPrice"));
                t.setCostPrice(rs.getFloat("CostPrice"));
                t.setQuantity(rs.getFloat("Quantity"));
                xThongKe.add(t);
            }

            for (int i = 0; i < xThongKe.size(); i++) {
                total = total + xThongKe.get(i).tinhLai();
            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public ArrayList<xThongKe> getAllOrderByMonth(int pageindex, int pagesize, String month) {
        ArrayList<xThongKe> xThongKe = new ArrayList<>();
        try {

            String sql = "    select * from ( SELECT *,ROW_NUMBER() OVER (ORDER BY CustomerID ASC) as row_num FROM\n"
                    + "					  ( SELECT        Customer.*, OrderOut.OrderID,  OrderOut.Total, OrderOut.OrderDate, OrderOut.Type, OrderOut.Note, Product.ProductName,Product.CostPrice, OrderOutDetail.ProductPrice, OrderOutDetail.Quantity\n"
                    + "						FROM            Customer INNER JOIN\n"
                    + "											 OrderOut ON Customer.CustomerID = OrderOut.CustomerID INNER JOIN\n"
                    + "										OrderOutDetail ON OrderOut.OrderID = OrderOutDetail.OderID INNER JOIN\n"
                    + "										Product ON OrderOutDetail.ProductID = Product.ProductID\n"
                    + "                 					where MONTH(OrderDate)= ? AND  type=1\n"
                    + "                 				) abc)xyz\n"
                    + "								WHERE row_num >= (? - 1)*? +1 AND row_num<= ? * ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, month);
            statement.setInt(2, pageindex);
            statement.setInt(3, pagesize);
            statement.setInt(4, pageindex);
            statement.setInt(5, pagesize);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                xThongKe t = new xThongKe();
                t.setProductName(rs.getString("ProductName"));
                t.setProductPrice(rs.getFloat("ProductPrice"));
                t.setCostPrice(rs.getFloat("CostPrice"));
                t.setQuantity(rs.getFloat("Quantity"));
                xThongKe.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return xThongKe;

    }

    public ArrayList<xThongKe> getAllOrderByDate(int pageindex, int pagesize, String date) {
        ArrayList<xThongKe> xThongKe = new ArrayList<>();
        try {

            String sql = " select * from ( SELECT *,ROW_NUMBER() OVER (ORDER BY CustomerID ASC) as row_num FROM\n"
                    + "					  ( SELECT        Customer.*, OrderOut.OrderID,  OrderOut.Total, OrderOut.OrderDate, OrderOut.Type, OrderOut.Note, Product.ProductName,Product.CostPrice, OrderOutDetail.ProductPrice, OrderOutDetail.Quantity\n"
                    + "						FROM            Customer INNER JOIN\n"
                    + "											 OrderOut ON Customer.CustomerID = OrderOut.CustomerID INNER JOIN\n"
                    + "										OrderOutDetail ON OrderOut.OrderID = OrderOutDetail.OderID INNER JOIN\n"
                    + "										Product ON OrderOutDetail.ProductID = Product.ProductID\n"
                    + "                 					where OrderDate=? and type=1\n"
                    + "                 				) abc)xyz\n"
                    + "								WHERE row_num >= (? - 1)*? +1 AND row_num<= ? * ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, date);
            statement.setInt(2, pageindex);
            statement.setInt(3, pagesize);
            statement.setInt(4, pageindex);
            statement.setInt(5, pagesize);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                xThongKe t = new xThongKe();
                t.setProductName(rs.getString("ProductName"));
                t.setProductPrice(rs.getFloat("ProductPrice"));
                t.setCostPrice(rs.getFloat("CostPrice"));
                t.setQuantity(rs.getFloat("Quantity"));
                xThongKe.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return xThongKe;
    }

    public ArrayList<xThongKe> getAllOrderFromTo(int pageindex, int pagesize, String from, String to) {
        ArrayList<xThongKe> xThongKe = new ArrayList<>();
        try {

            String sql = " select * from ( SELECT *,ROW_NUMBER() OVER (ORDER BY CustomerID ASC) as row_num FROM\n"
                    + "					  ( SELECT        Customer.*, OrderOut.OrderID,  OrderOut.Total, OrderOut.OrderDate, OrderOut.Type, OrderOut.Note, Product.ProductName,Product.CostPrice, OrderOutDetail.ProductPrice, OrderOutDetail.Quantity\n"
                    + "						FROM            Customer INNER JOIN\n"
                    + "											 OrderOut ON Customer.CustomerID = OrderOut.CustomerID INNER JOIN\n"
                    + "										OrderOutDetail ON OrderOut.OrderID = OrderOutDetail.OderID INNER JOIN\n"
                    + "										Product ON OrderOutDetail.ProductID = Product.ProductID\n"
                    + "                 					where OrderDate>=? and OrderDate<=? and type=1\n"
                    + "                 				) abc)xyz\n"
                    + "								WHERE row_num >= (? - 1)*? +1 AND row_num<= ? * ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, from);
            statement.setString(2, to);
            statement.setInt(3, pageindex);
            statement.setInt(4, pagesize);
            statement.setInt(5, pageindex);
            statement.setInt(6, pagesize);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                xThongKe t = new xThongKe();
                t.setProductName(rs.getString("ProductName"));
                t.setProductPrice(rs.getFloat("ProductPrice"));
                t.setCostPrice(rs.getFloat("CostPrice"));
                t.setQuantity(rs.getFloat("Quantity"));
                xThongKe.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return xThongKe;
    }

    public float layLaiTuan(String from, String to) {
        ArrayList<xThongKe> xThongKe = new ArrayList<>();
        float total=0;
        try {

            String sql = " select * from ( SELECT *,ROW_NUMBER() OVER (ORDER BY CustomerID ASC) as row_num FROM\n"
                    + "					  ( SELECT        Customer.*, OrderOut.OrderID,  OrderOut.Total, OrderOut.OrderDate, OrderOut.Type, OrderOut.Note, Product.ProductName,Product.CostPrice, OrderOutDetail.ProductPrice, OrderOutDetail.Quantity\n"
                    + "						FROM            Customer INNER JOIN\n"
                    + "											 OrderOut ON Customer.CustomerID = OrderOut.CustomerID INNER JOIN\n"
                    + "										OrderOutDetail ON OrderOut.OrderID = OrderOutDetail.OderID INNER JOIN\n"
                    + "										Product ON OrderOutDetail.ProductID = Product.ProductID\n"
                    + "                 					where OrderDate>=? and OrderDate<=? and type=1\n"
                    + "                 				) abc)xyz";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, from);
            statement.setString(2, to);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                xThongKe t = new xThongKe();
                t.setProductName(rs.getString("ProductName"));
                t.setProductPrice(rs.getFloat("ProductPrice"));
                t.setCostPrice(rs.getFloat("CostPrice"));
                t.setQuantity(rs.getFloat("Quantity"));
                xThongKe.add(t);
            }
            for (int i = 0; i < xThongKe.size(); i++) {
                total = total + xThongKe.get(i).tinhLai();
            }
        } catch (SQLException ex) {
            Logger.getLogger(getData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

}
