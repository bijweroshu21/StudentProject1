package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.model.Student;
import com.app.service.IStudentService;
@Controller // =Object + HTTP Request
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private IStudentService service;

	//1. Show Register Page
	/**
	 *  URL :/register, Type:GET 
	 *  Goto Page StudentRegister.html
	 */
	@GetMapping("/register")
	public String showRegister(Model model) {
		//Form backing Object 
		model.addAttribute("student", new Student());
		return "StudentRegister";
	}


	//2. save : on click submit
	/**
	 * URL: /save, Type: POST
	 * Goto : StudentRegister
	 */
	@PostMapping("/save")
	public String save(
			//read from Data from UI(given by container)
			@ModelAttribute Student student,
			Model model //to send data to UI
			)
	{
		//perform save operation
		Integer id=service.save(student);
		//construct one message
		String message="Student '"+id+"' saved successfully";
		//send message to UI
		model.addAttribute("message", message);
		//Form backing Object 
		model.addAttribute("student", new Student());
		//Goto Page
		return "StudentRegister";
	}

	//3. Display data
	@GetMapping("/all")
	public String fetchAll(Model model){
		List<Student> list=service.getAll();
		model.addAttribute("list", list);
		return "StudentData";
	}

	/**
	 * From Client(browser) enter URL like
	 * /delete/10 -> copy 10 into id path variable
	 * read id using @PathVariable DataType key syntax
	 * After Delete successful send message to UI
	 * Also select other rows from DB.
	 */

	//4. Remove one by Id
	@GetMapping("/delete/{id}")
	public String removeById(@PathVariable Integer id,Model model) 
	{
		String msg=null;
		if(service.isExist(id)) {

			service.delete(id);
			msg="Student '"+id+"' deleted!";

		}else {
			msg="Student '"+id+"' Not exist!";
		}

		model.addAttribute("message", msg);
		//show other rows
		List<Student> list=service.getAll();
		model.addAttribute("list", list);

		return "StudentData";
	}

	/**
	 * On click Edit HyperLink at UI,
	 * read one PathVariable and fetch data from 
	 * service, if exist send to edit page
	 * else redirect to data page
	 */
	//5. Show Edit Page with data
	@GetMapping("/edit/{id}")
	public String showEdit(@PathVariable Integer id,Model model)
	{
		String page=null;
		Optional<Student> opt=service.getOne(id);

		if(opt.isPresent()) {
			Student st=opt.get();
			model.addAttribute("student", st);
			page="StudentEdit";
		}else {
			page="redirect:../all";
		}
		return page;
	}

	/**
	 * On click update button,read form data and perform update operation
	 * send back to Data page with success message.
	 */
	//6. Update: on click update
	@PostMapping("/update")
	public String update(
			@ModelAttribute Student student,
			Model model)
	{
		service.update(student);
		String msg="Student '"+student.getId()+"' Updated!";
		model.addAttribute("message", msg);

		//new data from Db
		List<Student> list=service.getAll();
		model.addAttribute("list", list);
		return "StudentData";
	}




}
