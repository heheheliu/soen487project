package com.concordia.service;

import com.concordia.domain.Comment;
import com.concordia.domain.Course;

import java.util.List;

public interface CourseService {

    boolean createCourse(String courseNum, String title, String professor,
                         String description, String keyWord, String cover);

    boolean updateCourse(String courseNum, String title, String professor,
                         String description, String keyWord, String cover);
    boolean deleteCourse(String courseNum);
    Course getCourseByCourseNum(String courseNum);
    List<Course> getAllCourse();
    String getCourseCover(String courseNum);
    boolean updateCourseCover(String courseNum, String cover);
    boolean createComment(String username, String courseNum, String comments);
    boolean updateComment(String username, String courseNum, String comments);
    List<Comment> getAllComment();
    List<Comment> getCommentByCourseNum(String courseNum);
    boolean deleteComment(String username, String courseNum, String comment);

}
