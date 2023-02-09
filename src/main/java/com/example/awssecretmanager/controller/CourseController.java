package com.example.awssecretmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.awssecretmanager.entity.Course;
import com.example.awssecretmanager.service.CourseService;

import lombok.SneakyThrows;

@RequestMapping("/course")
@RestController
public class CourseController {
	
	@Autowired
	private CourseService courseService;

	@PostMapping
	public Course saveCourse(@RequestBody Course course) {
		return courseService.saveCourse(course);
	}

	@GetMapping
	public List<Course> findCourses() {
		return courseService.findCourses();
	}

	@SneakyThrows
	@GetMapping("/{id}")
	public Optional<Course> findCourse(@PathVariable int id) {
		return courseService.findCourse(id);
	}
}
