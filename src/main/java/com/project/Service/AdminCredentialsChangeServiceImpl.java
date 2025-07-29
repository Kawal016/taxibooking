package com.project.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.Dao.AdminLoginCrud;
import com.project.Entity.AdminLoginEntity;

@Service
public class AdminCredentialsChangeServiceImpl implements AdminCredentialChangeService {

	private AdminLoginCrud adminLoginCrud;
	
	@Autowired
	public void setAdminLoginCrud(AdminLoginCrud adminLoginCrud) {
		this.adminLoginCrud = adminLoginCrud;
	}

   private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public String checkCredentials(String oldusername, String oldpassword) {
     
		
	Optional<AdminLoginEntity> byUsername= 	adminLoginCrud.findByUsername(oldusername);
	  if(byUsername.isPresent())
	  {
		 AdminLoginEntity admin= byUsername.get();  //return sno,user,pass
		boolean matches= passwordEncoder.matches(oldpassword, admin.getPassword());
		 if(matches)
		 {
			 return "Success";
		 }
		 else
		 {
			 return "Wrong Credentials";
		 }
	  }
	  else
	  {
		  return "Wrong Credentials";
	  }
	  
    
	}

	@Override
	public String updateCredentials(String newusername, String newpassword, String oldusername) {
		
		int update=adminLoginCrud.updateCredentials(newusername, passwordEncoder.encode(newpassword), oldusername);
		if(update==1)
		{
			return "Updated Successfully";
		}
		else
		{
			return "Failed To Update";
		}
	
		
	}

}
