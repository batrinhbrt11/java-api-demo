package com.springapi.springapi.model;


import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tblProduct")
public class Product {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private int id;
    @Column(nullable = false,unique = true, length = 300)
    private String productName;
    private int year;
    private Double price;
    private String url;
    public Product(){
        
    }
    @Transient //calculated field in sql = transient java 
    private int age; 
    public int getAge(){
        return Calendar.getInstance().get(Calendar.YEAR) - year;
    }
    public Product( String productName, int year, Double price, String url) {
  
        this.productName = productName;
        this.year = year;
        this.price = price;
        this.url = url;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    @Override
    public String toString() {
        return "Product [id=" + id + ", price=" + price + ", productName=" + productName + ", url=" + url + ", year="
                + year + "]";
    }
    
}
