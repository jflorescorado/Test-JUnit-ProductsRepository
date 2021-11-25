package com.example.productsAppTest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productsAppTest.entity.Products;

public interface ProductsRepository extends JpaRepository<Products, Integer>{

}
