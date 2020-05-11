/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author VLT
 */
public class Order {

    private String id;

    private String customerName;

    private String address;

    private String phone;

    private String product;

    private int amount;

    private String price;

    private String warrantyPeriod;

    private String intoMoney;

    private Date date;

    private String methods;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getIntoMoney() {
        return intoMoney;
    }

    public void setIntoMoney(String intoMoney) {
        this.intoMoney = intoMoney;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    public Order(String id, String customerName, String address, String phone, String product, int amount, String price, String warrantyPeriod, String intoMoney, Date date, String methods) {
        this.id = id;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.warrantyPeriod = warrantyPeriod;
        this.intoMoney = intoMoney;
        this.date = date;
        this.methods = methods;
    }

    public Order() {
    }

}
