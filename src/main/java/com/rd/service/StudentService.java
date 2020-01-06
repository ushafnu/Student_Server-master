package com.rd.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.rd.model.Search;
import com.rd.model.Student;
import com.rd.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;

	public Student findOne(Long Id) {
		return repository.findById(Id).get();
	}

	public Student editStudent(Student student, Long id) {

		return repository.findById(id).map(x -> {
			x.setFirstName(student.getFirstName());
			x.setLastName(student.getLastName());
			return repository.save(x);
		}).orElseGet(() -> {
			student.setId(id);
			return repository.save(student);
		});
	}

	public List<Student> searchStudent(Search search, Pageable page) {

		Page<Student> students = repository.findAll(new Specification<Student>() {

			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> predicates = new ArrayList<>();

				if (search.getFirstName() != null) {
					predicates.add(
							cb.like(cb.lower(root.get("firstName")), "%" + search.getFirstName().toLowerCase() + "%"));
				}

				if (search.getLastName() != null) {
					predicates.add(
							cb.like(cb.lower(root.get("lastName")), "%" + search.getLastName().toLowerCase() + "%"));
				}

				return cb.and(predicates.toArray(new Predicate[0]));
			}

		},page);
		
		
		return students.getContent();
	}

}
