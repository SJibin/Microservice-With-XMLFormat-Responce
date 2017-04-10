package com.newt.service;

import java.util.List;

import com.newt.entity.Products;


public interface ProductsService {
	public Products addProducts(Products products);
	public Products getProductsByProductId(int productId);
	public List<Products> getAllProducts();
	public Products updateProducts(Products products);
	public void deleteProducts(int productId);
	public Products getProductsByProductName(String productName);
	
}
