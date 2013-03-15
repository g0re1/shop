package pl.igore.shop.BEAN;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;


import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pl.igore.shop.DAO.AdException;
import pl.igore.shop.DAO.CategoryDAO;
import pl.igore.shop.DAO.OfferDAO;
import pl.igore.shop.POJO.Category;
import pl.igore.shop.POJO.Offer;

@ManagedBean(name="offer") // lub @Named("user")
@ApplicationScoped


public class OfferBean implements Serializable {
	private String categoryS;
	private String offId;

	
	public OfferBean(){}
	
	public void setOffId(String id){
		this.offId=id;
	}
	
	public String getOffId(){
		return offId;
	}
	
	public String setCat(String catS){
		categoryS=catS;
		return "category";
	}
	
	public List<Category>getCategoryList(){
		CategoryDAO catD = CategoryDAO.instance;
		List<Category>list=null;
		try {
			list=catD.list();
		} catch (AdException e) {
			e.getMessage();
		}
		return list;
	}
	
	public List<Offer>getOffersList(){
		OfferDAO offerD = OfferDAO.instance;
		List<Offer>list=null;
		try {
			list=offerD.listByCategory(categoryS);
		} catch (AdException e) {
			e.getMessage();
		}
		/*	Iterator<Offer> it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.hashCode()+ it.next().getName() );
		}*/
		
		return list;
	}

	public String getCategoryS() {
		return categoryS;
	}

	public void setCategoryS(String categoryS) {
		this.categoryS = categoryS;
	}
	
	
	public String getOffId(String id){
		this.offId=id;
		return"offer";
	}
	
	public List<Offer> getOfferDetails(){
		OfferDAO offerD = OfferDAO.instance;
		List<Offer> offer=null;
		try {
			offer = offerD.getById(new Integer(offId));
		} catch (AdException e) {
			e.getMessage();
		}
		if (offer==null) 
		System.out.println("nullem jest");

		return offer;
	}

}
