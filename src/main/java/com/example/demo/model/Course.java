package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseid;
    private String coursename;
    private int duration;
    private String status;

    
    public Course() {
    }


    public Course(int courseid, String coursename, int duration, String status) {
        this.courseid = courseid;
        this.coursename = coursename;
        this.duration = duration;
        this.status = status;
    }


    public Course(String coursename, int duration, String status) {
        this.coursename=coursename;
        this.duration=duration;
        this.status=status;
    }


    public int getCourseid() {
        return courseid;
    }


    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }


    public String getCoursename() {
        return coursename;
    }


    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }


    public int getDuration() {
        return duration;
    }


    public void setDuration(int duration) {
        this.duration = duration;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
