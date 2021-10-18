package com.example.demo.repository;


import java.util.List;

import com.example.demo.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository 
public interface CourseRepo extends JpaRepository<Course,Integer>{

    Course getCourseByCourseid(int courseid);
    @Query(value="select *from course where scfk=?1" , nativeQuery = true)
    List<Course> findBy_scfk(int i);

    @Transactional
    @Modifying  
    @Query(value="update course set scfk =?1 where courseid =?2" ,nativeQuery = true)
    void updatefk(int userId,int courseid);
    @Transactional
    @Modifying  
    void deleteByCourseid(int id);


}
