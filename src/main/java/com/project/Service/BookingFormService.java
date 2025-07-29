package com.project.Service;

import java.util.List;

import com.project.Entity.BookingFormEntity;
import com.project.Model.bookingFormModel;

public interface BookingFormService {

	public String saveBookingFormService(bookingFormModel bookingFormModel);
	
	public List<BookingFormEntity> readAllBooking();
	
	public void deleteBookingById(int id);
}
