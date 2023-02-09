package com.example.awssecretmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.awssecretmanager.entity.Course;
import com.example.awssecretmanager.respository.CourseRepository;


@Service
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	
	public List<Course> findCourses() {
		return courseRepository.findAll();
	}

	public Optional<Course> findCourse(int id) {
		return courseRepository.findById(id);
	}

}
