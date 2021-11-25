package com.example.productsAppTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
@TestMethodOrder(OrderAnnotation.class)
public class ProductsTests {

	@Autowired
	private ProductsRepository repository;
	
	@Test
	@Rollback(false)
	//por default es true, pero para poder guardar datos tiene que ser false
	@Order(1)
	public void testSaveProduct() {
		Products products = new Products("SmartPhone Huawei p40", 300);
		Products productsSave = repository.save(products);
		
		assertNotNull(productsSave);
		//Confirma la prueba unitaria siempre y cuando el valor no sea nulo
	}
	
	@Test
	@Order(2)
	public void testFindByNameProduct() {
		String name = "SmartTV Samsung 4k";
		Products products = repository.findByName(name);
		
		assertThat(products.getName()).isEqualTo(name);
	}
	
	@Test
	@Order(3)
	public void testFindByNameProductNotExistence() {
		String name = "iPhone 11";
		Products products = repository.findByName(name);
		
		assertNull(products);
	}
	
	@Test
	@Rollback(false)
	//por default es true, pero para poder guardar datos tiene que ser false
	@Order(4)
	public void testUpdateProduct() {
		String nameProduct = "iPhone 12";//el nuevo valor 
		Products products = new Products(nameProduct, 320);//valores nuevos
		products.setIdProduct(4);//id de product a modificar
		
		repository.save(products);
		
		Products productsUpdate = repository.findByName(nameProduct);
		assertThat(productsUpdate.getName()).isEqualTo(nameProduct);
	}
	
	@Test
	@Order(5)
	public void testListProduct() {
		List<Products> products = (List<Products>) repository.findAll();
		
		for (Products product : products) {
			System.out.println(products);
		}
		
		assertThat(products).size().isGreaterThan(0);
		//El tamaño de la lista se va a confirmar si es mayor a 0
	}
	
	@Test
	@Rollback(false)
	//por default es true, pero para poder guardar datos tiene que ser false
	@Order(6)
	public void testDeleteProduct() {
		Integer id = 4;
		
		boolean isExistingBeforeDeleting = repository.findById(id).isPresent();
		//Pregunta si es presente, y si lo es sera true, sino false
		
		repository.deleteById(id);
		
		boolean isNotExistingLaterDeleting = repository.findById(id).isPresent();
		//Comprueba si ya no existe luego de eliminar
		
		assertTrue(isExistingBeforeDeleting);//Confirma si el valor es true
		assertFalse(isNotExistingLaterDeleting);//Confirma si el valor es false
	}
}
