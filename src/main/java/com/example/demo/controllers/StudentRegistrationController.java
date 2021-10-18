package com.example.demo.controllers;

import com.example.demo.services.StudentService;
import com.example.demo.web.dto.StudentRegistrationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class StudentRegistrationController {
    @Autowired
    public StudentService studentService;

    public StudentRegistrationController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }
    @GetMapping
    public String showRegistrationForm(Model model){
        model.addAttribute("student",new StudentRegistrationDto());
        return "registration";
    }
    @PostMapping
    public String registerStudentAccount(@ModelAttribute("student") StudentRegistrationDto registrationDto){
        studentService.save(registrationDto);
        return "redirect:/registration?success";
    }

    @ModelAttribute("student")
    public StudentRegistrationDto studentRegistrationDto(){
        return new StudentRegistrationDto();
    }

}
