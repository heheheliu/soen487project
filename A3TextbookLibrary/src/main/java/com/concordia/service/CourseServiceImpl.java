package com.concordia.service;

import com.concordia.dao.CourseMapper;
import com.concordia.domain.Comment;
import com.concordia.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    CourseMapper courseMapper;

    @Override
    public boolean createCourse(String courseNum, String title, String professor, String description, String keyWord, String cover) {
        return courseMapper.createCourse(courseNum, title, professor, description, keyWord, cover) > 0;
    }

    @Override
    public boolean updateCourse(String courseNum, String title, String professor, String description, String keyWord, String cover) {
        return courseMapper.updateCourse(courseNum, title, professor, description, keyWord, cover) > 0;
    }

    @Override
    public boolean deleteCourse(String courseNum) {
        return courseMapper.deleteCourseByCourseNum(courseNum) > 0;
    }

    @Override
    public Course getCourseByCourseNum(String courseNum) {
        return courseMapper.getCourseByCourseNum(courseNum);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseMapper.getAllCourse();
    }

    @Override
    public String getCourseCover(String courseNum) {
        return courseMapper.getCourseCover(courseNum);
    }

    @Override
    public boolean updateCourseCover(String courseNum, String cover) {
        return courseMapper.updateCourseCover(courseNum, cover) > 0;
    }

    @Override
    public boolean createComment(String username, String courseNum, String comments) {
        return courseMapper.createComment(username, courseNum, comments) > 0;
    }

    @Override
    public boolean updateComment(String username, String courseNum, String comments) {
        return courseMapper.updateComment(username, courseNum, comments) > 0;
    }

    @Override
    public List<Comment> getAllComment() {
        return courseMapper.getAllComment();
    }

    @Override
    public List<Comment> getCommentByCourseNum(String courseNum) {
        return courseMapper.getCommentByCourseNum(courseNum);
    }

    @Override
    public boolean deleteComment(String username, String courseNum, String comment) {
        return courseMapper.deleteComment(username, courseNum, comment) > 0;
    }
}
