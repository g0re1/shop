package pl.igore.shop.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import pl.igore.shop.POJO.Category;
import pl.igore.shop.POJO.Product;

public class ProductDAO  extends DAO{
	
	public ProductDAO(){}
	
	public Product create(String name) throws AdException{
		
			Product prod =  null;
			try{
				begin();
				Query query = getSession().createQuery("from Product where product_name=:name");
				query.setParameter("name", name);
				prod = (Product) query.uniqueResult();
				commit();
			}
			catch(HibernateException e){
				rollback();
				throw new AdException("Could not create Product named = "+name,e);
			}	
			return prod;
		}
	
}
