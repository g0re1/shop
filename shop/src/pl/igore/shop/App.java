package pl.igore.shop;

import java.io.BufferedReader;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import pl.igore.shop.DAO.AdException;
import pl.igore.shop.DAO.CategoryDAO;

public class App {
	
	public static void main(String[] args) {
		load();
	}
	
	public static void load(){
		BufferedReader we = null;
		String linia;
		CategoryDAO catD = new CategoryDAO();

		try{
			 we = new BufferedReader( new FileReader("/home/gore/Projects/shop/WebContent/TestData/cat.txt"));
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
