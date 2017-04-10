package com.newt.entity;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "PRODUCTS")
@XmlRootElement
public class Products {
	@Id
	
	private int productId;
	
	private String productName;
	
	private double price;
	
	private long totalInStock;

	public Products() {
		super();
	}

	public Products(int productId, String productName, double price, long totalInStock) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.totalInStock = totalInStock;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getTotalInStock() {
		return totalInStock;
	}

	public void setTotalInStock(long totalInStock) {
		this.totalInStock = totalInStock;
	}

	
}
