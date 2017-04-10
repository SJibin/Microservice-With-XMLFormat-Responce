package com.newt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newt.entity.Products;
import com.newt.repository.ProductsRepository;
@Service
public class ProductServiceImp implements ProductsService{
	@Autowired
	ProductsRepository productRepo;

	@Override
	public Products addProducts(Products products) {
	return productRepo.save(products);
	}

	@Override
	public Products getProductsByProductId(int productId) {
		return productRepo.findOne(productId);
	}

	@Override
	public List<Products> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public Products updateProducts(Products products) {
		return productRepo.save(products);
	}

	@Override
	public void deleteProducts(int productId) {
		productRepo.delete(productId);
	}

	@Override
	public Products getProductsByProductName(String productName) {
		
		return productRepo.findByproductName(productName);
	}

}
