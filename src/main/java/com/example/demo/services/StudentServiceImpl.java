package com.example.demo.services;


import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.web.dto.StudentRegistrationDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private StudentRepository studentrepository;
    @Override
    public Student save(StudentRegistrationDto registrationDto) {
        Student student=new Student(registrationDto.getUsername(),registrationDto.getEmail(),passwordEncoder.encode(registrationDto.getPassword()), null);
        return studentrepository.save(student);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student=studentrepository.findByEmail(username);
        if(student==null){
            throw new UsernameNotFoundException("User Not Found");
        }

        return new StudentPrincipal(student);
    }

    @Override
    public Student findByEmail(String name) {
        return studentrepository.findByEmail("name");
    }
    // public List<Student> getAllStudents() {
    //     return studentrepository.findAll();
    // }
    // public Student getStudentById(int id) {
    //     return studentrepository.findById(id).orElse(null);
    // }
    // public Student postStudent(Student student) {
    //     return studentrepository.save(student);
    // }
    // public Student putStudent(int id,Student s){
    //     Student stud=studentrepository.findById(id).orElse(null);
    //     stud.setEmail(s.getEmail());
    //     stud.setPassword(s.getPassword());
    //     stud.setUsername(s.getUsername());
    //     stud.setCourses(s.getCourses());

    //     studentrepository.save(stud);
    //     return s;
    // }

    // public String deleteStudent_(int id){
    //     studentrepository.deleteById(id);
    //     return "Student with id"+id+" is deleted";
    // }

}
