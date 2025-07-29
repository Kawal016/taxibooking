package com.project.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.Dao.AdminLoginCrud;
import com.project.Entity.AdminLoginEntity;

import jakarta.annotation.PostConstruct;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	private AdminLoginCrud adminLoginCrud;
	
	@Autowired
	public void setAdminLoginCrud(AdminLoginCrud adminLoginCrud) {
		this.adminLoginCrud = adminLoginCrud;
	}
	
	@PostConstruct
	public void init()
	{
	  long count= adminLoginCrud.count();
	  if(count==0)
	  {
		  AdminLoginEntity adminLoginEntity = new AdminLoginEntity();
		  adminLoginEntity.setUsername("Admin");
		  adminLoginEntity.setPassword(passwordEncoder.encode("Admin123"));
		  
		  adminLoginCrud.save(adminLoginEntity);
	  }
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
       Optional<AdminLoginEntity> user= adminLoginCrud.findByUsername(username);
       AdminLoginEntity object=  user.orElseThrow(  ()-> new UsernameNotFoundException("Admin Does Not Exist")  );
       return User.withUsername(object.getUsername())
           .password(object.getPassword())
           .build();
		
	}

	

}
