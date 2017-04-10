package com.newt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.newt.entity.Products;
@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {

	public <S extends Products> List<S> save(Iterable<S> products);

	public Products findOne(Integer productId);

	public List<Products> findAll();

	public void delete(Integer productId);
	@Query("from Products p where p.productName=?1")
	public Products findByproductName(String productName);
}
