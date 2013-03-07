package pl.igore.shop.POJO;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="offer")
public class Offer implements Serializable {
	private int id;
	private User user;
	private String name;
	private Category category;
	private double price;
	private String specification;
	private Date startDate;
	private Date endDate;
	
	public Offer(){}
	
	public Offer(User user,String name,Category cat, double price, String spec,Date startDate,Date endDate ){		
		this.name=name;
		this.category=cat;
		this.price=price;
		this.specification=spec;
		this.startDate=endDate;
		this.endDate=endDate;
	}
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	@ManyToOne(targetEntity=User.class)
	@Column(nullable=false)
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="product_name",nullable=false, unique=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(targetEntity=Category.class)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	@Column(nullable=false)
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	@Column(nullable=false)
	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}
	@Column(nullable=false)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(nullable=false)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
