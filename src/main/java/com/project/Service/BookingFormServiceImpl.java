package com.project.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Dao.BookingFormCrud;
import com.project.Dao.ContactFormCrud;
import com.project.Entity.BookingFormEntity;
import com.project.Model.bookingFormModel;

@Service
public class BookingFormServiceImpl implements BookingFormService {

	private BookingFormCrud bookingFormCrud;
		
	@Autowired
	public void setBookingFormCrud(BookingFormCrud bookingFormCrud) {
		this.bookingFormCrud = bookingFormCrud;
	}
   
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public String saveBookingFormService(bookingFormModel bookingFormModel) {
   	  	
      BookingFormEntity entity=  modelMapper.map(bookingFormModel, BookingFormEntity.class);
     BookingFormEntity object= bookingFormCrud.save(entity);
      if(object!=null)
      {
    	  return "Booking Successfully";
      }
      else
      {
    	  return "Something Went Wrong";
      }
	}


	@Override
	public List<BookingFormEntity> readAllBooking() {
	
		return bookingFormCrud.findAll();
	
	}


	@Override
	public void deleteBookingById(int id) {
		
		bookingFormCrud.deleteById(id);
	}

}
