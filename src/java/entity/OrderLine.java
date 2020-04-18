/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author sonnt
 */
public class OrderLine {

    private Order order;
    int line;
    private Product product;
    private float quantity;
    private float price;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public float getTotal() {
        return quantity * price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderLine{" + "order=" + order + ", line=" + line + ", product=" + product + ", quantity=" + quantity + ", price=" + price + '}';
    }

}
