package com.project.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class bookingFormModel {

	@NotEmpty(message="Name can't be empty")
	@NotBlank(message="Name can't be blank")
	@Size(min=3 ,max=30 , message="Invalid name length")
	@Pattern(regexp ="^[A-Za-z]+$"  , message="Name must contain only alphabet")
	private String name;
	
	@NotEmpty(message="Source can't be empty")
	@NotBlank(message="Source can't be blank")
	@Size(min=3 ,max=100 , message="Invalid source length")
	private String source;
	
	@NotEmpty(message="Email can't be empty")
	@NotBlank(message="Email can't be blank")
	@Size(min=7 ,max=50 , message="Invalid email length")
	private String email;
	
	@NotEmpty(message="Destination can't be empty")
	@NotBlank(message="Destination can't be blank")
	@Size(min=3 ,max=100 , message="Invalid Destination length")
	private String destination;
	
	@NotNull(message="Time can't be empty")
	private LocalTime time;
	
	@NotNull(message="Date can't be empty")
	private LocalDate date;
	
	@NotEmpty(message="Comfort can't be empty")
	private String comfort;
	
	@Max(value=4,message="Adult can be atmost 4")
	@Min(value=1,message="Adult can be atleast 1")
	private int adult;
	
	@Max(value=3,message="Children can be atmost 3")
	private int children;
	
	@NotEmpty(message="Message can't be empty")
	@NotBlank(message="Message can't be blank")
	@Size(min=2 ,max=1000 , message="Invalid message length")
	private String message;
}
