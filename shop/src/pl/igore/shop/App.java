package pl.igore.shop;

import java.io.BufferedReader;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;



import pl.igore.shop.DAO.AdException;
import pl.igore.shop.DAO.CategoryDAO;
import pl.igore.shop.DAO.OfferDAO;
import pl.igore.shop.DAO.UserDAO;
import pl.igore.shop.POJO.Category;
import pl.igore.shop.POJO.Offer;
import pl.igore.shop.POJO.User;

public class App {
	public static void showCat(){
		CategoryDAO catD = CategoryDAO.instance;
		List<Category>list=null;
		try {
			list=catD.list();
		} catch (AdException e) {
			e.getMessage();
		}
		Iterator<Category> it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next().getName());
		}
	}
	
	public static void add100RandomOffers(){
		UserDAO userD = UserDAO.instance;
		CategoryDAO catD = CategoryDAO.instance;
		OfferDAO offerD = OfferDAO.instance;
		List<User>userList =null;
		List<Category>catList=null;
		List<Offer>offerList = null;
		try {
			userList = userD.list();
			catList = catD.list();
			offerList = offerD.list();
		} catch (AdException e) {
			e.printStackTrace();
		}
		int x = offerList.size()-1;
		Random random = new Random();
		for(int i=0;i<100;i++){
			try {
				offerD.create(userList.get(random.nextInt(userList.size()-1)),"Oferta"+x+i, 
						catList.get(random.nextInt(catList.size()-1)),random.nextDouble()*1000,
						"Opis Oferty"+x+i,new Date(),new Date() 
						);
			} catch (AdException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void loadUsers(){
		BufferedReader we = null;
		String linia;
		UserDAO userD = new UserDAO();

		try{
			 we = new BufferedReader( new FileReader("/root/git/shop/shop/WebContent/TestData/users.txt"));
			 while( (linia = we.readLine())  != null) {		
				StringTokenizer t = new StringTokenizer(linia,"|");
				userD.create(t.nextToken(),t.nextToken(),t.nextToken());
			}
		}
		catch(FileNotFoundException e){e.printStackTrace();} 
		catch (IOException e) { e.printStackTrace(); }
		catch(AdException e){e.printStackTrace();}
			
	}
	
	public static void loadCat(){
		BufferedReader we = null;
		String linia;
		CategoryDAO catD = new CategoryDAO();

		try{
			 we = new BufferedReader( new FileReader("/root/git/shop/shop/WebContent/TestData/cat.txt"));
			 while( (linia = we.readLine())  != null) {		
				StringTokenizer t = new StringTokenizer(linia,"|");
				catD.create(t.nextToken());
			}
		}
		catch(FileNotFoundException e){e.printStackTrace();} 
		catch (IOException e) { e.printStackTrace(); }
		catch(AdException e){e.printStackTrace();}
			
	}
}
