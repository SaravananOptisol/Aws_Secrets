package com.example.awssecretmanager.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.awssecretmanager.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
	
}