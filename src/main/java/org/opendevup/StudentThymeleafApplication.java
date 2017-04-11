package org.opendevup;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.opendevup.dao.StudentRepository;
import org.opendevup.entities.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class StudentThymeleafApplication {

	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx = SpringApplication.run(StudentThymeleafApplication.class, args);
	StudentRepository studentRepository = ctx.getBean(StudentRepository.class);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	studentRepository.save(new Student("Alex",df.parse("1980-08-12"),"alex@example.com","alex.jpg"));
	studentRepository.save(new Student("Alen",df.parse("1980-08-12"),"alen@example.com","alen.jpg"));
	studentRepository.save(new Student("Wube",df.parse("1980-08-12"),"wube@example.com","wube.jpg"));
	
	Page<Student> stu = studentRepository.loadStudents("%A%", new PageRequest(0,5));
	stu.forEach(e->System.out.println(e.getName()));
	
	
	
	
}
}
