package com.project.Controller;

import java.util.List;

import javax.lang.model.element.ModuleElement.RequiresDirective;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Entity.BookingFormEntity;
import com.project.Entity.ContactFormEntity;
import com.project.Entity.addServiceEntity;
import com.project.Model.addServiceModel;
import com.project.Service.AdminCredentialChangeService;
import com.project.Service.BookingFormServiceImpl;
import com.project.Service.ContactFormServiceImpl;
import com.project.Service.addServiceServ;


import jakarta.servlet.annotation.MultipartConfig;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private ContactFormServiceImpl contactFormServiceImpl;
	
	@Autowired
	private  BookingFormServiceImpl bookingFormServiceImpl;
	
	@Autowired
	private addServiceServ  serv;
	
	private  AdminCredentialChangeService adminCredentialChangeService;
	
	@Autowired
	public void setAdminCredentialChangeService(AdminCredentialChangeService adminCredentialChangeService) {
		this.adminCredentialChangeService = adminCredentialChangeService;
	}

	@GetMapping("dashboard")
	public String viewDashboard()
	{
		return "admin/dashboard";
	}
	
	@GetMapping("changeCredent")
	public String changeCredentialView()
	{
		return "admin/changeCredentials";
	}
	
	@GetMapping("readAllContact")
	public String readContact(Model m)
	{
	  List<ContactFormEntity> list=	contactFormServiceImpl.readAllContactService();
	  m.addAttribute("listofcontact", list);
	  
		return "admin/readAllContact";
	}
	
	@GetMapping("deleteContact/{id}")
	public String deleteContactById(@PathVariable int id, RedirectAttributes redirectAttributes)
	{
		
		contactFormServiceImpl.deleteContactService(id);
		redirectAttributes.addFlashAttribute("message", "Contact Deleted Successfully" );
		return "redirect:/admin/readAllContact";
	}
	
	@GetMapping("readAllBooking")
	public String readBooking(Model m)
	{
		List<BookingFormEntity> list=bookingFormServiceImpl.readAllBooking();
		m.addAttribute("listofbooking", list);
		return "admin/readAllBooking";
	}
	
	@GetMapping("deleteBooking/{id}")
	public String deleteBook(@PathVariable int id,RedirectAttributes redirectAttributes)
	{
		bookingFormServiceImpl.deleteBookingById(id);
		redirectAttributes.addFlashAttribute("message", "Contact Deleted Successfully" );
		return "redirect:/admin/readAllBooking";
	}
	
	@GetMapping("addService")
	public String addServiceView()
	{
		return "admin/adminService";
	}
	
	@GetMapping("readAllService")
	public String readService(Model m)
	{
	  List<addServiceEntity> listofService	=serv.readServiceData();
	  m.addAttribute("listOfService", listofService);
		return "admin/readService";
	}
	
	@GetMapping("deleteService/{id}")
	public String deleteAllService(@PathVariable int id,RedirectAttributes redirectAttributes)
	{
	   serv.deleteService(id);	
	   redirectAttributes.addFlashAttribute("message", "Service Deleted Successfully");
	  return "redirect:/admin/readAllService";	
	}
	
	@PostMapping("updateCredential")
    public String changeCredentialUpdate(@RequestParam("oldusername") String oldusername,
    		                             @RequestParam("oldpassword") String oldpassword,
    		                             @RequestParam("newusername") String newusername,
    		                             @RequestParam("newpassword") String newpassword,
    		                            RedirectAttributes redirectAttributes)
	
	
	{
		String result=adminCredentialChangeService.checkCredentials(oldusername, oldpassword);
		if(result.equals("Success"))
		{
			result=adminCredentialChangeService.updateCredentials(newusername, newpassword, oldusername);
			 redirectAttributes.addFlashAttribute("message", result);
		}
		else
		{
		 redirectAttributes.addFlashAttribute("message", result);
		}
		System.out.println(result);
		return "redirect:/admin/changeCredent";
	}
	
	@InitBinder
	public void stopBindingImage(WebDataBinder webDataBinder)
	{
		webDataBinder.setDisallowedFields("image");
	}
	
	@PostMapping("serviceAdd")
	public String addService(@ModelAttribute addServiceModel addServiceModel,
			@RequestParam("image") MultipartFile multipartFile,
			RedirectAttributes redirectAttributes)
	{
	    String name= multipartFile.getOriginalFilename();
		addServiceModel.setImage(name);
	    try {
	    	 
	     addServiceEntity result=	serv.addServiceData(addServiceModel, multipartFile);
		 if(result!=null)
		 {
			redirectAttributes.addFlashAttribute("message", "Service Added Successfully"); 
		 }
		 else
		 {
			 redirectAttributes.addFlashAttribute("message", "Something Went Wrong"); 
		 }
	     
	    } catch (Exception e) {
	    	redirectAttributes.addFlashAttribute("message", "Something Went Wrong"); 
		}
		return "redirect:/admin/addService";
	}
}
