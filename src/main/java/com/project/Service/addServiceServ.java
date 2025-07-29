package com.project.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.Dao.addServiceCrud;
import com.project.Entity.addServiceEntity;
import com.project.Model.addServiceModel;

public interface addServiceServ {

	public addServiceEntity addServiceData(addServiceModel addServiceModel,MultipartFile multipartFile) throws Exception;
	
	public List<addServiceEntity> readServiceData();   
	
	public void deleteService(int id);
}
