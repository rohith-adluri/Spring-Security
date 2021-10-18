package com.example.demo.services;

import com.example.demo.model.Student;
import com.example.demo.web.dto.StudentRegistrationDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface StudentService extends UserDetailsService{
    Student save(StudentRegistrationDto registrationDto);

    Student findByEmail(String name);

}
