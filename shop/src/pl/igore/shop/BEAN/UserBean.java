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
   private boolean verified;
  
   
   public boolean isVerified() {return verified;}
   public void setVerified(boolean verified) {this.verified = verified;}
   
   public String getName() { return name; }   
   public void setName(String newValue) { name = newValue; }

   public String getPassword() { return password; }
   public void setPassword(String newValue) { password = newValue; }   
   
   public String getMail(){return mail;}
   public void setMail(String newValue){mail=newValue;}
   
   public String getToString(){
	return "imie : "+ name+" password = "+password+" mail = "+mail;
	   
   }
   
   public String index(){
	   return "index";
   }
   
   public String getVerify(){
	   if(name=="")return null;
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
