package pl.igore.shop;

import java.io.BufferedReader;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import pl.igore.shop.DAO.AdException;
import pl.igore.shop.DAO.CategoryDAO;
import pl.igore.shop.DAO.UserDAO;

public class App {
	
	public static void add100RandomOffers(){
		
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
