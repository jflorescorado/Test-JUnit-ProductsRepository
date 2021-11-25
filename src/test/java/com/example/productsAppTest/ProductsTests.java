package com.example.productsAppTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.productsAppTest.dao.ProductsRepository;
import com.example.productsAppTest.entity.Products;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
//Indica que realice las operaciones en la base de datos de real y ya no solamente en memoria H2
public class ProductsTests {

	@Autowired
	private ProductsRepository repository;
	
	@Test
	@Rollback(false)
	//por default es true, pero para poder guardar datos tiene que ser false
	public void testSaveProduct() {
		Products products = new Products("SmartTV Samsung HD", 300);
		Products productsSave = repository.save(products);
		
		assertNotNull(productsSave);
		//Confirma la prueba unitaria siempre y cuando el valor no sea nulo
	}
}
