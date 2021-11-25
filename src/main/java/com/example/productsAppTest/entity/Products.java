package com.example.productsAppTest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Products {

	@Id
	@Column(name = "idProduct")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProduct;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private double price;

	public Products() {
		super();
	}

	public Products(Integer idProduct, String name, double price) {
		super();
		this.idProduct = idProduct;
		this.name = name;
		this.price = price;
	}

	public Products(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Products [idProduct=" + idProduct + ", name=" + name + ", price=" + price + "]";
	}
	
}
