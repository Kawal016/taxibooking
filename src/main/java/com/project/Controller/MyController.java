package com.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.Entity.addServiceEntity;
import com.project.Model.addServiceModel;
import com.project.Model.bookingFormModel;
import com.project.Model.contactFormModel;
import com.project.Service.BookingFormService;
import com.project.Service.ContactFormServiceImpl;
import com.project.Service.addServiceServ;
import com.project.Service.addServiceServiceImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;

@Controller
public class MyController {

	@Autowired
    private addServiceServ addServiceServ;

	@Autowired 
	private ContactFormServiceImpl contactFormServiceImpl;
	
	private BookingFormService bookingFormService;
	
	@Autowired
	public void setBookingFormService(BookingFormService bookingFormService) {
		this.bookingFormService = bookingFormService;
	}


	@GetMapping(path= {"/","home","view","welcome"})
	public String welcomeView(HttpServletRequest req,Model m)
	{
		String requestUri=req.getRequestURI();
		m.addAttribute("myRequestUri", requestUri);
		m.addAttribute("bookingFormModel", new bookingFormModel());
		return "index";
	}
	
	
	@GetMapping("about")
	public String aboutView(HttpServletRequest req,Model m)
	{
		String requestUri=req.getRequestURI();
		m.addAttribute("myRequestUri", requestUri);
		return "about";
	}
	
	@GetMapping("car")
	public String carsView(HttpServletRequest req,Model m)
	{
		String requestUri=req.getRequestURI();
		m.addAttribute("myRequestUri", requestUri);
		return "cars";
	}
	
	@GetMapping("service")
	public String servicesView(HttpServletRequest req,Model m)
	{
		String requestUri=req.getRequestURI();
		m.addAttribute("myRequestUri", requestUri);
		
	   List<addServiceEntity> list =addServiceServ.readServiceData();
		m.addAttribute("allServices", list);
		return "services";
	}
	
	@GetMapping("contact")
	public String contactsView(HttpServletRequest req,Model m)
	{
		String requestUri=req.getRequestURI();
		m.addAttribute("myRequestUri", requestUri);
		m.addAttribute("contactFormModel", new contactFormModel());
		return "contacts";
	}
	
	@GetMapping("/login")
	public String adminLoginView(HttpServletRequest request,Model m)
	{
	 ServletContext servletContext=  request.getServletContext();
	 Object att= servletContext.getAttribute("logout");
	 if(att instanceof Boolean)
	 {
        m.addAttribute("logout", att);
        servletContext.removeAttribute("logout");
	 }
		return "adminLogin";
	}
	
	
	@PostMapping("contactForm")
	public String submitContactForm(@Valid @ModelAttribute contactFormModel cFormModel,
			BindingResult bindingResult,	Model m, RedirectAttributes redirectAttributes )
	{
		if(bindingResult.hasErrors())
		{
			m.addAttribute("validationMessage", bindingResult);
			return "contacts";
		}
		else
		{
			String result=contactFormServiceImpl.saveContactFormService(cFormModel);
			redirectAttributes.addFlashAttribute("Message", result);
			
		}
		
		System.out.println(cFormModel);
		return "redirect:contact";
	}
	
	
	@PostMapping("bookingform")
	public String submitBookingForm(@Valid @ModelAttribute bookingFormModel bFormModel,
			BindingResult bindingResult, Model m,RedirectAttributes redirectAttributes)
	{
		if(bindingResult.hasErrors())
		{
			m.addAttribute("validationMessage", bindingResult);
			
			return "index";
		}
		else if(bFormModel.getAdult() + bFormModel.getChildren() > 4)
		{
			m.addAttribute("message", "Total no. of Adult and Children cannot exceed 4");
			return "index";
		}
		else
		{
		  String result=bookingFormService.saveBookingFormService(bFormModel);
		  redirectAttributes.addFlashAttribute("message", result);
		}
		System.out.println(bFormModel);
        return "redirect:home";
	}
}
