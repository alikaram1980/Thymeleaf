package org.opendevup.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Student implements Serializable{
	@Id
	@GeneratedValue
	
private Long id;
	@Column(name="NAME",length=30)
	@NotEmpty
	@Size(min=0,max=80,message="the size must be between ")
private String name;
	@DateTimeFormat(pattern="yyyy-MM-dd")
private Date dateOfBirth;
@NotEmpty
private String email;
private String photo;
public Student() {
	super();
	// TODO Auto-generated constructor stub
}

public Student(String name, Date dateOfBirth, String email, String photo) {
	super();
	this.name = name;
	this.dateOfBirth = dateOfBirth;
	this.email = email;
	this.photo = photo;
}

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Date getDateOfBirth() {
	return dateOfBirth;
}
public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhoto() {
	return photo;
}
public void setPhoto(String photo) {
	this.photo = photo;
}

}
