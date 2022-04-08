package com.concordia.controller;

import com.concordia.domain.Comment;
import com.concordia.domain.Course;
import com.concordia.service.CourseService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;


    @GetMapping(path = "getAllCourse", produces = "application/json")
    public List<Course> getAllCourse(){
        return courseService.getAllCourse();
    }

    @GetMapping(path = "get/{courseNum}",produces = "application/json")
    public Course getCourseByCourseNum(@PathVariable String courseNum){
        return courseService.getCourseByCourseNum(courseNum);
    }

    @PostMapping(path = "createCourse",produces = "application/json")
    public String createCourse(@RequestParam(name = "courseNum") String courseNum,
                                @RequestParam(name = "title") String title,
                                @RequestParam(name = "professor") String professor,
                                @RequestParam(name = "description") String description,
                                @RequestParam(name = "keyWord") String keyWord,
                                @RequestParam(name = "cover") String cover){
        Boolean result = courseService.createCourse(courseNum, title, professor, description, keyWord, cover);
        if(result){
            return "Created new course successfully";
        }
        return "Create course fail";
    }

    @PutMapping(path = "updateCourse",produces = "application/json")
    public String updateCourse(@RequestParam(name = "courseNum") String courseNum,
                               @RequestParam(name = "title") String title,
                               @RequestParam(name = "professor") String professor,
                               @RequestParam(name = "description") String description,
                               @RequestParam(name = "keyWord") String keyWord,
                               @RequestParam(name = "cover") String cover){
        Boolean result = courseService.updateCourse(courseNum, title, professor, description, keyWord, cover);
        if(result){
            return "Updated course successfully";
        }
        return "Update course fail";

    }

    @DeleteMapping(path = "deleteCourse/{courseNum}",produces = "application/json")
    public String deleteCourse(@PathVariable String courseNum){
        if(courseNum.equals("null")||courseNum.length()==0){
            return "Please enter a course Number";
        }
        Boolean result = courseService.deleteCourse(courseNum);
        if(result){
            return "Deleted course successfully";
        }
        return "delete course fail";
    }

    @GetMapping(path = "getCover/{courseNum}",produces = "application/json")
    public String getCourseCover(@PathVariable String courseNum){
        if(courseNum.equals("null")||courseNum.length()==0){
            return "Please enter a course Number";
        }
        return courseService.getCourseCover(courseNum);

    }

    @PutMapping(path = "updateCover/{courseNum}",produces = "application/json")
    public String updateCourseCover(@PathVariable String courseNum, @RequestParam(name = "cover") String cover){
        if(courseNum.equals("null")||courseNum.length()==0){
            return "Please enter a course Number";
        }
        Boolean result = courseService.updateCourseCover(courseNum,cover);
        if(result){
            return "updated course cover successfully";
        }
        return "update course cover fail";

    }

    @PostMapping(path = "createComment",produces = "application/json")
    public String createComment(@RequestParam(name = "username") String username,
                                @RequestParam(name = "courseNum") String courseNum,
                                @RequestParam(name = "comments") String comments){
        if(courseNum.equals("null")||courseNum.length()==0||username.equals("null")||username.length()==0){
            return "Please enter a course Number or an username";
        }
        Boolean result = courseService.createComment(username, courseNum, comments);
        if(result){
            return "created course comment successfully";
        }
        return "create course comment fail";
    }
    @PutMapping(path = "updateComment/{username}/{courseNum}",produces = "application/json")
    public String updateComment(@PathVariable("username") String username,
                                @PathVariable("courseNum") String courseNum,
                                @RequestParam(name = "comments") String comments){
        if(courseNum.equals("null")||courseNum.length()==0||username.equals("null")||username.length()==0){
            return "Please enter a course Number or an username";
        }
        Boolean result = courseService.updateComment(username, courseNum, comments);
        if(result){
            return "updated course comment successfully";
        }
        return "update course comment fail";
    }

    @GetMapping(path = "getAllComment",produces = "application/json")
    public List<Comment> getAllComment(){
        return courseService.getAllComment();
    }

    @GetMapping(path = "getComment/{courseNum}",produces = "application/json")
    public List<Comment> getCommentByCourseNum(@PathVariable String courseNum){
        return courseService.getCommentByCourseNum(courseNum);
    }








}
