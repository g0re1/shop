package pl.igore.shop.BEAN;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pl.igore.shop.DAO.AdException;
import pl.igore.shop.DAO.AdminDAO;
import pl.igore.shop.POJO.Admin;

@ManagedBean(name="admin") // lub @Named("user")
@SessionScoped

public class AdminBean extends UserBean{
	
	public AdminBean(){
	}
	
	@PostConstruct
	public void initialize(){
		AdminDAO adminD = new AdminDAO();
		try {
			if( !adminD.contains("admin")){
				adminD.create("admin","a","admin@lib-gore.rhcloud.com");
			}
		} catch (AdException e) {
			e.printStackTrace();
		}
	}
	
	  public String verifyUser(){
		   AdminDAO adminD = new AdminDAO();
		   try {
			if (!adminD.contains(super.getName()) ){
				super.setVerify(-1);
				return"";
			   }
		} catch (AdException e) {
			e.getMessage();
		}
		   Admin admin = null;
		   try {
			admin = adminD.get(getName());
		} catch (AdException e) {
			e.getMessage();
		}
		   if(super.getPassword().equals(admin.getPassword())){
			   super.setVerified(true);
			   super.setNotVerified(false);
			   return "index";
		   }
		   else{
			   super.setVerify(-2);
			   return "";
		   } 
	   }
	
	public String create(){
		AdminDAO adminD = new AdminDAO();
		try {
			if( !adminD.contains(super.getName())){
				adminD.create(super.getName(),super.getPassword(),super.getMail());
			}
		} catch (AdException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}



