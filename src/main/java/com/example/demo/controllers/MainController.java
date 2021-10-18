package com.example.demo.controllers;

import java.security.Principal;

import com.example.demo.model.Course;
import com.example.demo.services.CourseService;
import com.example.demo.services.StudentPrincipal;
import com.example.demo.services.StudentServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    Logger log=LoggerFactory.getLogger(MainController.class);


    @Autowired
    CourseService service;
    @Autowired
    StudentServiceImpl studservice;
    @GetMapping("/login")
    public String login(){
        log.info(">>>>>>>>>> login called");
        return "login";
    }
    @GetMapping("")
    public String home(Model model,Principal principal){
        log.info(">>>>>>>>>> home page called");
        StudentPrincipal user=(StudentPrincipal) studservice.loadUserByUsername(principal.getName());
        model.addAttribute("uname",user.student.getUsername());
        return "home";
    }
    @GetMapping("/getcourses")
    public String getAllCourses(Model m,Principal principal){
        log.info(">>>>>>>>>> getAllCourses api called");
        StudentPrincipal user=(StudentPrincipal) studservice.loadUserByUsername(principal.getName());
        m.addAttribute("courses",service.getAllCourses(user.student.getId()));
        m.addAttribute("greet", "hello from controller");
        return "courses";
    }
    @GetMapping("/addnewcourse")
    public String createNewCourse(Model model){
        log.info(">>>>>>>>>> new Course created ");
        Course course=new Course();
        model.addAttribute("course", course);
        return "create_course";
    }
    @PostMapping("/getcourses")
    public String getAllCourses(@ModelAttribute Course course,Principal principal){
        log.info(">>>>>>>>>> getAllCourses post mapping called");
        System.out.println(">>>>>>>>>>>>>"+principal.getName());
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Student student = (Student)authentication.getPrincipal();
        // int userId = student.getId();
//         service.saveCourse(course,userId);
        // System.out.println(">>>>>>>>>>>>>>"+userId);
        //service.addFk(principal.getName());
        StudentPrincipal user=(StudentPrincipal) studservice.loadUserByUsername(principal.getName());
        System.out.println(user.student.getId());
        service.saveCourse(course,user.student.getId());
        return "redirect:/getcourses";
    }
    @GetMapping("/getcourses/edit/{courseid}")
    public String editCourseForm(@PathVariable("courseid") int courseid,Model model){
        log.info(">>>>>>>>>> Course update button clicked");
        model.addAttribute("course", service.getCourseByCourseid(courseid));
        return "edit_course";
    }

    @PostMapping("/getcourses/{courseid}")
    public String updateCourse(@PathVariable("courseid") int courseid ,@ModelAttribute("course")Course course,Model model){
        log.info(">>>>>>>>>> course successfully updated");
        Course existingcourse=service.getCourseByCourseid(courseid);
        existingcourse.setCourseid(course.getCourseid());
        existingcourse.setCoursename(course.getCoursename());
        existingcourse.setDuration(course.getDuration());
        existingcourse.setStatus(course.getStatus());
        
        service.updateCourse(existingcourse);

        return "redirect:/getcourses";
        
    }
    @GetMapping("/getcourses/{id}")
    public String deleteCourse(@PathVariable("id")int id){
        log.info(">>>>>>>>>> course deleted ");
        service.deleteCourseByCourseid(id);
        return "redirect:/getcourses";

    }
    
}
