package com.example.demo.services;

import java.util.List;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepo;
import com.example.demo.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    CourseRepo repo;

    @Autowired
    StudentRepository studentrepo;
    public List<Course>getAllCourses(int id){
         return repo.findBy_scfk(id);
    }
    public void saveCourse(Course course, int userId) {
        repo.save(course);
        repo.updatefk(userId,course.getCourseid());
    }
    public Course getCourseByCourseid(int id){
        return repo.findById(id).get();
    }

    public Course updateCourse(Course course){
        return repo.save(course);
    }
    public void deleteCourseByCourseid(int id){
        repo.deleteByCourseid(id);
    }
    public List<Course> getCourses_() {
        return repo.findAll();
    }
    public ResponseEntity<Object> getCourseById_(int id) {
        Course c = repo.findById(id).orElse(null);
        if(c==null){
            return new ResponseEntity<>("No Course found with given id",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    public ResponseEntity<Object> postCourse_(Course course) {
        if(course.getDuration()<=0){
            return new ResponseEntity<>("Course not created ,Important fields are missing",HttpStatus.BAD_REQUEST);
        }
         repo.save(course);
         return new ResponseEntity<>("course successfully created",HttpStatus.CREATED);
    }
    public ResponseEntity<Object> putCourse_(int id, Course course) {
        Course curr=repo.findById(id).orElse(null);
        if(course.getDuration()<=0 || curr==null){
            return new ResponseEntity<>("Invalid fields or Bad request",HttpStatus.BAD_REQUEST);
        }
        curr.setCourseid(course.getCourseid());
        curr.setCoursename(course.getCoursename());
        curr.setDuration(course.getDuration());
        curr.setStatus(course.getStatus());
        repo.save(curr);

        return new ResponseEntity<>(curr,HttpStatus.OK);
    }
    public ResponseEntity<Object> deleteCourse_(int id) {
        Course c=repo.findById(id).orElse(null);
        if(c==null){
            return new ResponseEntity<>("Nothing to be deleted here",HttpStatus.NOT_FOUND);
        }
        repo.deleteById(id);
        return new ResponseEntity<>("Course Successfully deleted",HttpStatus.OK);
    }
}
