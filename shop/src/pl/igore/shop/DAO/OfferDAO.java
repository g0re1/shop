package pl.igore.shop.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import pl.igore.shop.POJO.Category;
import pl.igore.shop.POJO.Offer;

public class OfferDAO  extends DAO{
	
	public OfferDAO(){}
	
	public Offer create(String name) throws AdException{
		
		Offer offer =  new Offer(name);
			try{
				begin();
				getSession().save(offer);
				commit();
			}
			catch(HibernateException e){
				rollback();
				throw new AdException("Could not create Product named = "+name,e);
			}	
			return offer;
		}
	
}
