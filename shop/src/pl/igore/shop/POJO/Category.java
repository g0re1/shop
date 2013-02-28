package pl.igore.shop.POJO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category implements Serializable {
	private int id;
	private String name;
	private Set<Product>products;
	
	public Category(){}
	
	public Category(String name){
		this.name=name;
		this.products= new HashSet<Product>();
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name="category_name",nullable=false, unique=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(targetEntity=Product.class,optional=false)
	@Column(nullable=false)
	public Set<Product> getProdutcs() {
		return products;
	}

	public void setProdutcs(Set<Product> produtcs) {
		this.products = produtcs;
	}
}
