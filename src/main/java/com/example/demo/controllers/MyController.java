package com.example.demo.controllers;

import java.util.List;

import com.example.demo.model.Course;
import com.example.demo.services.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    @Autowired
    CourseService service;
    @GetMapping("/api/getCourses")
    public List<Course>getCourses(){
        return service.getCourses_();
    }

    @GetMapping("/api/getCourses/{id}")
    public ResponseEntity<Object> getCourseById(@PathVariable("id")int id){
        return service.getCourseById_(id);
    }

    @PostMapping("/api/addCourse")
    public ResponseEntity<Object> addStudent(@RequestBody Course course){
        return service.postCourse_(course);
    }
    @PutMapping("/api/updateCourse/{id}")
    public ResponseEntity<Object> addStudent(@RequestBody Course course,@PathVariable("id")int id){
        return service.putCourse_(id,course);
    }
    @DeleteMapping("/api/deleteCourse/{id}")
    public ResponseEntity<Object> deleteCourse_(@PathVariable("id")int id){
        return service.deleteCourse_(id);
    }
}
