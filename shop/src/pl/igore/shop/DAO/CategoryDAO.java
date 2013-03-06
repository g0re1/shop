package pl.igore.shop.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import pl.igore.shop.POJO.Category;

public class CategoryDAO  extends DAO{
	
	public CategoryDAO(){}
	
	public Category create(String name) throws AdException{
		Category cat =  new Category(name);
		try{
			begin();
			getSession().save(cat);
			commit();
		}
		catch(HibernateException e){
			rollback();
			throw new AdException("Could not create Category named = "+name,e);
		}	
		return cat;
	}
}
