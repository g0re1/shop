package pl.igore.shop.BEAN;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pl.igore.shop.DAO.AdException;
import pl.igore.shop.DAO.AdminDAO;
import pl.igore.shop.DAO.CategoryDAO;
import pl.igore.shop.DAO.OfferDAO;
import pl.igore.shop.DAO.UserDAO;
import pl.igore.shop.POJO.Category;
import pl.igore.shop.POJO.Offer;
import pl.igore.shop.POJO.User;

@ManagedBean(name="sell")
@SessionScoped

public class SellBean implements Serializable{
	private String userS;
	private String name;
	private String categoryS;
	private String specification;
	private int price;
	private String startDate;
	private int startDateMin;
	private int startDateHour;
	private int days;
	
	public SellBean(){
		setStartDate("10-10-2013");
	}
	
	public String createOffer(){
		UserDAO userD =UserDAO.instance;
		CategoryDAO catD = CategoryDAO.instance;
		OfferDAO offerD = OfferDAO.instance;
		
		User user = null;
		Category cat = null;
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String,String> params = context.getExternalContext().getInitParameterMap();
	//	userS = params.get(userS);
				
		try {
			user = userD.get("gore");
			cat = catD.get(categoryS);
		} catch (AdException e) {
			e.getMessage();
		}
		
		Offer offer = new Offer();
		offer.setUser(user);
		offer.setName(name);
		offer.setCategory(cat);
		offer.setPrice(price);
		offer.setStartDate(formatDate(startDate,startDateMin,startDateHour));
		
		Date endDate = new Date();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(endDate);
		cal.add(Calendar.DAY_OF_YEAR,days);
		offer.setEndDate(endDate);
		
		offer.setSpecification(specification);
		
		try {
			offerD.create(offer);
		} catch (AdException e) {
			e.printStackTrace();
		}
		
		System.out.println(offer.toString());
		return"";
	}
	
	public Date formatDate(String dateS,int minute,int hour){
		StringTokenizer t = new StringTokenizer(dateS,"-");
		Date date = new Date();
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, new Integer(t.nextToken()));
		cal.set(Calendar.MONTH, new Integer(t.nextToken())-1);
		cal.set(Calendar.YEAR, new Integer(t.nextToken()) );
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		return cal.getTime();
	}
	
	public List<String>getCatNamesList(){
		CategoryDAO catD = CategoryDAO.instance;
		List<Category>list=null;
		try {
			list=catD.list();
		} catch (AdException e) {
			e.getMessage();
		}
		Iterator<Category> it = list.iterator();
		List<String> catNamesList = new ArrayList<String>();
		while(it.hasNext()){
			catNamesList.add(it.next().getName());
		}
		return catNamesList;
	}
	
	public List<Integer> getDaysList(){
		List<Integer>list = new ArrayList<Integer>();
		for(int i=4;i<15;i++){
			list.add(i);
		}
		return list;
	}
	
	public List<Integer> getMinute(){
		List<Integer>list = new ArrayList<Integer>();
		for(int i=0;i<60;i++){
			list.add(i);
		}
		return list;
	}
	
	public List<Integer>getHour(){
		List<Integer>list = new ArrayList<Integer>();
		for(int i=0;i<24;i++){
			list.add(i);
		}
		return list;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return categoryS;
	}

	public void setCategory(String category) {
		this.categoryS = category;
	}

	public String getUser() {
		return userS;
	}

	public void setUser(String user) {
		this.userS = user;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	

	public int getStartDateMin() {
		return startDateMin;
	}

	public void setStartDateMin(int startDateMin) {
		this.startDateMin = startDateMin;
	}

	public int getStartDateHour() {
		return startDateHour;
	}

	public void setStartDateHour(int startDateHour) {
		this.startDateHour = startDateHour;
	}

	public void setDays(int days) {
		this.days = days;
	}
	
	public int getDays(){
		return days;
	}
	
}
