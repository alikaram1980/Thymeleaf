package org.opendevup.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.opendevup.dao.StudentRepository;
import org.opendevup.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/Student")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Value("${dir.images}")
	private String imageDir;
	
	@RequestMapping(value="/Index")
	public String Index(Model model,
			@RequestParam(name="page",defaultValue="0")int p,
			@RequestParam(name="keyWord",defaultValue="")String mc
			){
		Page<Student> listStudent= studentRepository.loadStudents("%"+mc+"%",new PageRequest(p,5));
		
		int pagesCount=listStudent.getTotalPages();
		int[] pages = new int[pagesCount];
		for(int i=0;i<pagesCount;i++) pages[i]=i;
		model.addAttribute("pages", pages);
		model.addAttribute("students", listStudent);
		model.addAttribute("pageCurrent", p);
		model.addAttribute("keyWord", mc);
		return "students";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String formStudent(Model model){
		model.addAttribute("student", new Student());
		return"FormStudent";
	}
	
	@RequestMapping(value="/SaveStudent", method=RequestMethod.POST)
	public String save(@Valid Student student,
			BindingResult bindingResult,
			@RequestParam(name="picture")MultipartFile file) throws Exception{
		if(bindingResult.hasErrors()){
			
			return"FormStudent";
		}
		
		if(!(file.isEmpty())){student.setPhoto(file.getOriginalFilename());}
		
		studentRepository.save(student);
		if(!(file.isEmpty())){
			student.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir+student.getId()));
		}
		
		return"redirect:Index";
	}

	@RequestMapping(value="/remove")
	public String remove(long id){
		
		studentRepository.delete(id);
		
		return"redirect:Index";
		
	}
	@RequestMapping(value="/edit")
	public String edit(long id,Model model){
		Student student = studentRepository.findOne(id);
		model.addAttribute("student", student);
		return "EditStudent";
	}
	
	@RequestMapping(value="/UpdateStudent", method=RequestMethod.POST)
	public String update (@Valid Student student,
			BindingResult bindingResult,
			@RequestParam(name="picture")MultipartFile file) throws Exception{
		if(bindingResult.hasErrors()){
			
			return"EditStudent";
		}
		
		if(!(file.isEmpty())){student.setPhoto(file.getOriginalFilename());}
		
		studentRepository.save(student);
		if(!(file.isEmpty())){
			student.setPhoto(file.getOriginalFilename());
			file.transferTo(new File(imageDir+student.getId()));
		}
		
		return"redirect:Index";
	}
	
}
