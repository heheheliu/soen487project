package com.concordia.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseServiceTest {
    @Autowired
    private CourseService courseService;

    @Test
    void testCreateCourse(){
        courseService.createCourse("348", "Principles of Programming Languages","Elnaka",
                "programming languages","Ruby","123");
    }

    @Test
    void testUpdateCourse(){
        courseService.updateCourse("348", "Principles of Programming Languages","Elnaka",
                "programming languages","Ruby LISP","12345");
    }
    @Test
    void testDeleteCourse(){
        courseService.deleteCourse("348");
    }
    @Test
    void testgetCourseByCourseNum(){
        courseService.getCourseByCourseNum("445");
    }
    @Test
    void testGetAllCourse(){
        courseService.getAllCourse();
    }

    @Test
    void testCreateComment(){
        courseService.createComment("He","445", "very good");
    }

    @Test
    void testGetAllComment(){
        courseService.getAllComment();
    }
}
