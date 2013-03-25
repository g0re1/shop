package pl.igore.shop.BEAN;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import pl.igore.shop.DAO.AdException;
import pl.igore.shop.DAO.OfferDAO;
import pl.igore.shop.POJO.Offer;

@ManagedBean(name="account") // lub @Named("user")
@SessionScoped

public class AccountBean implements Serializable{
	@ManagedProperty(value="#{user}")
	private UserBean userBean;
	
	public void setUserBean(UserBean userBean){this.userBean=userBean;}
	
	public AccountBean(){
		
	}
	
	public List<Offer> getBoughtList(){
		OfferDAO offerD = OfferDAO.instance;
		List<Offer> list = null;
		
		try {
			list = offerD.notActiveListByBuyer(userBean.getName());
		} catch (AdException e) {
			e.getMessage();
		}
		
		return list;
	}
	
	public List<Offer> getSoldList(){
		OfferDAO offerD = OfferDAO.instance;
		List<Offer> list = null;
		
		try {
			list = offerD.notActiveListBySeller(userBean.getName());
		} catch (AdException e) {
			e.getMessage();
		}

		return list;
	}
}
