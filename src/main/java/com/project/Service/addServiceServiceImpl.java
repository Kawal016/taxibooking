package com.project.Service;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPopupMenu.Separator;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.Dao.addServiceCrud;
import com.project.Entity.addServiceEntity;
import com.project.Model.addServiceModel;

import jakarta.transaction.Transactional;

@Service
public class addServiceServiceImpl implements addServiceServ {

	private addServiceCrud addServiceCrud;
	
	@Autowired
	public void setAddServiceCrud(addServiceCrud addServiceCrud) {
		this.addServiceCrud = addServiceCrud;
	}

	@Autowired
    private ModelMapper modelMapper;
	

	@Transactional(rollbackOn = Exception.class)
	@Override
	public addServiceEntity addServiceData(addServiceModel addServiceModel, MultipartFile multipartFile) throws Exception {
	   
		addServiceEntity save=null;
		
 try {
		  addServiceEntity entity=modelMapper.map(addServiceModel,addServiceEntity.class);
		  save= addServiceCrud.save(entity);  //db me save ho gayi filne name ke sath
		  if(save!=null)
		     {
		  //now agar uploading ke samay project mein exeption aa gaya file ki to iski liye hm rooling back karke means j db me save hui hai uuse undo karnge 
		  //by using transaction annotation
		  String path="E:\\Spring course\\taxibooking\\src\\main\\resources\\static\\myServiceImg\\" + multipartFile.getOriginalFilename();
    	  byte[] bytes = multipartFile.getBytes();
		  FileOutputStream fos= new FileOutputStream(path);
		  fos.write(bytes);
		     }
	}
		catch(Exception e)
		{
			save=null;
			throw e;
			
		}
		
		return save;
	}

	@Override
	public List<addServiceEntity> readServiceData() {
		
	  List<addServiceEntity> lsitEntity= addServiceCrud.findAll();
	  return lsitEntity;

	}

	@Override
	public void deleteService(int id) {
		
		addServiceCrud.deleteById(id);
	}

}
