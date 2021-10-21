package com.app.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Student;
import com.app.repo.StudentRepo;
import com.app.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {
	@Autowired
	private StudentRepo repo;

	@Transactional
	public Integer save(Student st) {
		Integer id=repo.save(st).getId();
		return id;
	}

	@Transactional
	public void update(Student st) {
		repo.save(st);
	}

	@Transactional
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Optional<Student> getOne(Integer id) {
		Optional<Student> opt=repo.findById(id);
		return opt;
	}

	@Transactional(readOnly = true)
	public List<Student> getAll() {
		List<Student> list=repo.findAll();
		return list;
	}

	@Transactional(readOnly = true)
	public boolean isExist(Integer id) {
		boolean exist=repo.existsById(id);
		return exist;
	}

}
