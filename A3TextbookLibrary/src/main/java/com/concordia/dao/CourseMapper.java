package com.concordia.dao;


import com.concordia.domain.Comment;
import com.concordia.domain.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Insert("insert into course(courseNum,title,professor,description,keyWord,cover)"+
    "values(#{courseNum},#{title},#{professor},#{description},#{keyWord},#{cover})")
    public int createCourse(String courseNum, String title, String professor,
                          String description, String keyWord, String cover);

    @Update("update course Set title=#{title},professor=#{professor},description=#{description}," +
            "keyWord=#{keyWord},cover=#{cover} where courseNum=#{courseNum}")
    public int updateCourse(String courseNum, String title, String professor,
                          String description, String keyWord, String cover);


    @Delete("delete from course where courseNum=#{courseNum}")
    public int deleteCourseByCourseNum(String courseNum);


    @Select("select * from course where courseNum = #{courseNum}")
    public Course getCourseByCourseNum(String courseNum);

    @Select("select cover from course where courseNum = #{courseNum}")
    public String getCourseCover(String courseNum);

    @Select("select * from course")
    public List<Course> getAllCourse();

    @Update("UPDATE course SET cover = #{cover} WHERE courseNum = #{courseNum}")
    public int updateCourseCover(String courseNum, String cover);

    @Insert("insert into comment(username, courseNum,comments)" +
    "values(#{username}, #{courseNum}, #{comments})")
    public int createComment(String username, String courseNum, String comments);

    @Update("update comment set comments = #{comments} where username=#{username} and courseNum=#{courseNum}")
    public int updateComment(String username, String courseNum, String comments);

    @Select("select * from comment")
    public List<Comment> getAllComment();

    @Select("select * from comment where courseNum = #{courseNum}")
    public List<Comment> getCommentByCourseNum(String courseNum);

    @Delete("delete from comment where username=#{username} and courseNum=#{courseNum}")
    public int deleteComment(String username, String courseNum);













}
