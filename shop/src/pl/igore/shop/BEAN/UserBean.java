package pl.igore.shop.BEAN;

import java.io.Serializable;
import javax.faces.bean.ManagedBean; 
   // lub import javax.inject.Named;
import javax.faces.bean.SessionScoped; 

import pl.igore.shop.DAO.AdException;
import pl.igore.shop.DAO.UserDAO;
import pl.igore.shop.POJO.User;

@ManagedBean(name="user") // lub @Named("user")
@SessionScoped
public class UserBean implements Serializable {
   private String name;
   private String password;
   private String mail;
   private UserDAO userD;
   private int verify;
   
   public UserBean(){
	   userD = new UserDAO();
	   verify=0; // -1 wrong password, 0 user dosn't exist, 1 correct pass
	   name="";
   }
   
   public String getName() { return name; }   
   public void setName(String newValue) { name = newValue; }

   public String getPassword() { return password; }
   public void setPassword(String newValue) { password = newValue; }   
   
   public String getMail(){return mail;}
   public void setMail(String newValue){mail=newValue;}
   
   public String getToString(){
	return "imie : "+ name+" password = "+password+" mail = "+mail;
	   
   }
   
   public String verifyUser(){
	   User user = null;
	   if(name.equals(new String()) ) return"";
	   try {
		if (!userD.contains(name)){
			verify=-1;
			return"";
		}
		user = userD.get(name);
	   } 
	   catch (AdException e) {
		e.getMessage();
	   }
	   if(user.getPassword().equals(password)) return "welcome";
	   else{verify=-2;return"";}
   }
   
   public String getVerify(){
	   if(name=="")return"";
	   if(verify==-2) return"Wrong password";
	   if(verify==-1) return"User doesn't exist";
	   else{return"";}
   }
   
   public String create(){	   
	   try {
		userD.create(name, password,mail);
	} catch (AdException e) {
		e.getMessage();
	}
	return "registered";
   }
}
