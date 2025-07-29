package com.project.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="BookingForm")
public class BookingFormEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(length=30)
	private String name;
	
	@Column(length=100)
	private String source;
	
	@Column(length=50)
	private String email;
	
	@Column(length=100)
	private String destination;
	
	private LocalTime time;
	private LocalDate date;
	private String comfort;
	private int adult;
	private int children;
	
	@Column(length=1000)
	private String message;
}
