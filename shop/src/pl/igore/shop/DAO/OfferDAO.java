package pl.igore.shop.DAO;

import java.util.Date;
import java.util.List;

import org.apache.catalina.Session;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import pl.igore.shop.POJO.Category;
import pl.igore.shop.POJO.Offer;
import pl.igore.shop.POJO.User;

public class OfferDAO  extends DAO{
	public static final OfferDAO instance = new OfferDAO();
	
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
	
	public List<Offer> list() throws AdException{
		List<Offer> list =  null;
		try{
			begin();
			Query query = getSession().createQuery("from Offer");
			list=query.list();
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not get Offer list ",e);
		}	
		return list;
	}
	
	public List<Offer> listByCategory(String catS) throws AdException{
		List<Offer> list =  null;
		//CategoryDAO catD = CategoryDAO.instance;
		//Category cat = catD.get(catS);
		try{
			begin();
			Criteria crit = getSession().createCriteria(Offer.class);
			Criteria catCrit = crit.createCriteria("category");
			catCrit.add(Restrictions.eq("name", catS));
			list=crit.list();
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not get listByCategory  ",e);
		}	
		return list;
	}
}


