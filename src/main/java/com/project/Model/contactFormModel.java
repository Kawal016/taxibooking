package com.project.Model;


import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class contactFormModel {

	
	@NotEmpty(message="Name Cannot Be Empty")
	@Size(min=2 , max=30, message="Invalid Name Size")
	private String name;
	
	@NotEmpty(message="Email Cannot Be Empty")
	@Size(min=8 , max=40, message="Invalid Email Size")
	private String email;
	
	@NotEmpty(message="Phone Number can not be empty")
	@Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
	private String phone;
	
	@NotEmpty(message="Message Cannot Be Empty")
	@Size(min=10 , max=500, message="Invalid Message Size")
	private String message;
	
	
}
