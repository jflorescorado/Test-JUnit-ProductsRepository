package com.example.productsAppTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.productsAppTest.dao.ProductsRepository;
import com.example.productsAppTest.entity.Products;

@DataJpaTest
public class ProductsTests {

	@Autowired
	private ProductsRepository repository;
	
	@Test
	public void testSaveProduct() {
		Products products = new Products("SmartTV Samsung HD", 300);
		repository.save(products);
	}
}
