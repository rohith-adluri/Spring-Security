package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Student" , uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private String password;

    @OneToMany
    @JoinColumn(name ="scfk",referencedColumnName = "id")
    private List<Course>courses;

    
    public Student() {
    }
    public Student(int id, String username, String email, String password, List<Course> courses) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.courses = courses;
    }
    public Student(String username, String email, String password, Object object) {
        this.username=username;
        this.password=password;
        this.email=email;
        
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    
}
