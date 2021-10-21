package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.model.Student;

public interface IStudentService {
	Integer save(Student st);
	void update(Student st);
	
	void delete(Integer id);
	Optional<Student> getOne(Integer id);
	
	List<Student> getAll();
	boolean isExist(Integer id);

}
