package pl.igore.shop.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import pl.igore.shop.POJO.Offer;
import pl.igore.shop.POJO.Transaction;

public class TransactionDAO extends DAO{
	public static final TransactionDAO instance = new TransactionDAO();
	
	public TransactionDAO(){}
	
	public Transaction create(Offer offer) throws AdException{
		Transaction trans = new Transaction(offer);
		
		try{
			begin();
			getSession().save(trans);
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not create transaction from offer named = "+offer.getName(),e);
		}	
		finally{
			close();
		}
		return trans;
	}
	
	public List<Transaction> list() throws AdException{
		List<Transaction> list =  null;
		try{
			begin();
			Query query = getSession().createQuery("from Transaction");
			list=query.list();
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not get Transaction list ",e);
		}	
		return list;
	}
}
