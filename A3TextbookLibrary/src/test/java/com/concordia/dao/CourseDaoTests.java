package com.concordia.dao;


import com.concordia.domain.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseDaoTests {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    void testGetByCourseNum() {
        String num = "487";
        courseMapper.getCourseByCourseNum(num);

    }
    @Test
    void testGetAll(){
        courseMapper.getAllCourse();
    }
    @Test
    void testCreate(){

        courseMapper.createCourse("287","Web Programming", "Kaustubha",
                "designing web page","JavaScript","");

    }
    @Test
    void testUpdate(){
        courseMapper.updateCourse("287","Web Programming", "Kaustubha Mendhurwar",
                "designing web page","JavaScript","");
    }
    @Test
    void testDelete(){
        courseMapper.deleteCourseByCourseNum("287");
    }

    @Test
    void testCommentCreate(){
        courseMapper.createComment("He","487", "good");
    }
    @Test
    void testCommentUpdate(){
        courseMapper.updateComment("He", "487", "nice");
    }


}
