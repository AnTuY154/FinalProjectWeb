/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Darkness_King
 */
public class xThongKe {
    String productName;
    float costPrice;
    float ProductPrice;
    float quantity;

    public float tinhLai(){
        return quantity*ProductPrice-quantity*costPrice;
    }
    public float thanhTien(){
        return quantity*ProductPrice;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(float costPrice) {
        this.costPrice = costPrice;
    }

    public float getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(float ProductPrice) {
        this.ProductPrice = ProductPrice;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }


    
}
