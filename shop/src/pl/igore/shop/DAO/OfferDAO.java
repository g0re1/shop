package pl.igore.shop.DAO;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import pl.igore.shop.POJO.Category;
import pl.igore.shop.POJO.Offer;
import pl.igore.shop.POJO.User;

public class OfferDAO  extends DAO{
	
	public OfferDAO(){}
	
	public Offer create(User user,String name,Category cat, double price, String spec,Date startDate,Date endDate ) throws AdException{	
		Offer offer =  new Offer(user,name,cat,price,spec,startDate,endDate);
			try{
				begin();
				getSession().save(offer);
				commit();
			}
			catch(HibernateException e){
				rollback();
				throw new AdException("Could not create offer named = "+name,e);
			}	
			return offer;
		}
	

}
