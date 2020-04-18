/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author Darkness_King
 */
public class orderTemp {

    Customer customer;
    int orderID;
    ArrayList<OrderLine> orderLine;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<OrderLine> getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(ArrayList<OrderLine> orderLine) {
        this.orderLine = orderLine;
    }

}
