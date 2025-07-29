package com.project.Service;

import java.util.List;

import com.project.Entity.ContactFormEntity;
import com.project.Model.contactFormModel;

public interface ContactFormService{

	public String saveContactFormService(contactFormModel cFormModel);
	
	public List<ContactFormEntity> readAllContactService();
	
	public void deleteContactService(int id);
}
