package com.rd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rd.model.Search;
import com.rd.model.Student;
import com.rd.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService service;

	@PostMapping("/students")
	public List<Student> searchStudent(@RequestBody Search search) {

		PageRequest pageable = PageRequest.of(search.getPageNo() - 1, search.getCount());
		return service.searchStudent(search, pageable);

	}

	@GetMapping("/students/{id}")
	public Student findOne(@PathVariable Long id) {
		return service.findOne(id);

	}

	@PutMapping("/students/{id}")
	public Student editStudent(@RequestBody Student student, @PathVariable Long id) {
		return service.editStudent(student, id);

	}

}
