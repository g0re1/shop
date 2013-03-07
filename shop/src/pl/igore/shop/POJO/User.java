package pl.igore.shop.POJO;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="shop_user")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@AttributeOverrides({
		@AttributeOverride(name="id", column=@Column(name="user_id") ),
		@AttributeOverride(name="name", column=@Column(name="user_name") ),
		@AttributeOverride(name="password", column=@Column(name="user_password") ),
		@AttributeOverride(name="mail", column=@Column(name="user_mail"))
        })
@DiscriminatorColumn(
		name="Discriminator",
		discriminatorType=DiscriminatorType.INTEGER
		)
@DiscriminatorValue(value = "1")

public class User extends Person {
	private Set<Offer>offers;
	
	public User(){}
	
	public User(String name, String pass, String mail){
		super(name, pass, mail);
		offers=new HashSet<Offer>();
	}

	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}
	
}
