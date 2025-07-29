package com.project.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Dao.ContactFormCrud;
import com.project.Entity.ContactFormEntity;
import com.project.Model.contactFormModel;

@Service
public class ContactFormServiceImpl implements ContactFormService {

	@Autowired
	private ContactFormCrud crud;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public String saveContactFormService(contactFormModel cFormModel) {
		
		ContactFormEntity entity=modelMapper.map(cFormModel, ContactFormEntity.class);
		ContactFormEntity contactFormEntity= crud.save(entity);
          if(contactFormEntity != null)
          {
        	  return "MESSAGE SENT SUCCESSFULLY";
          }
          else
          {
        	  return "SOMTHING WENT WRONG";
          }
		
        
	}

	@Override
	public List<ContactFormEntity> readAllContactService() {
		List<ContactFormEntity> list=crud.findAll();
		return list;
	}

	@Override
	public void deleteContactService(int id) {
		
		crud.deleteById(id);
	}



}
