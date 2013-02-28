package pl.igore.shop;

import pl.igore.shop.DAO.AdException;
import pl.igore.shop.DAO.CategoryDAO;
import pl.igore.shop.DAO.ProductDAO;
import pl.igore.shop.POJO.Category;
import pl.igore.shop.POJO.Product;

public class App {
	
	public static void main(String[] args) {	
		CategoryDAO catD = new CategoryDAO();
		Category cat = null;
		try {
			cat = catD.create("Elektronika");
		} catch (AdException e) {
			e.getMessage();
		}
		
		ProductDAO prodD = new ProductDAO();
		Product prod = null;
		try {
			prod = prodD.create("Komputer");
		} catch (AdException e) {
			e.getMessage();
		}
		
		prod.setCategory(cat);
		System.out.println("Leon");
	}
}
