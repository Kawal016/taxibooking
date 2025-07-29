package com.project.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private CustomLogoutHandlerConfigImpl customLogoutHandlerConfigImpl;
	
	@Autowired
	public void setCustomLogoutHandlerConfigImpl(CustomLogoutHandlerConfigImpl customLogoutHandlerConfigImpl) {
		this.customLogoutHandlerConfigImpl = customLogoutHandlerConfigImpl;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		
		//USE OF LAMBDA EXPRESSION
		httpSecurity
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(
				authorize -> authorize.requestMatchers("/admin/**").authenticated()
				.requestMatchers("/**").permitAll()
				)
		.formLogin(form->form
				.loginPage("/login")
				.permitAll())
		.logout(logout -> logout
			 .addLogoutHandler(customLogoutHandlerConfigImpl)	
			 .logoutUrl("/dologout")
			);
	    
		return httpSecurity.build();
	}
	

	@Bean
	PasswordEncoder passwordEncoder()
	{
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
}
