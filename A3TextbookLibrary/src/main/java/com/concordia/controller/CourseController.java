package com.concordia.controller;

import com.concordia.domain.Comment;
import com.concordia.domain.Course;
import com.concordia.googlemodel.GoogleBook;

import com.concordia.service.CourseService;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@CrossOrigin
@RequestMapping("/courses")
public class CourseController {
    @Value("${google.googleBookApiUrl}")
    private String googleBooksApiUrl;
    private static final String auth_url = "http://localhost:8899/user/auth";


    @Autowired
    private CourseService courseService;


    private boolean validateToken(String token) {
        System.out.println(auth_url);
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(String.format(auth_url));
            httpPost.addHeader("token", token);
            CloseableHttpResponse httpResponse = client.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();
            String isAuthenticated = EntityUtils.toString(entity);
            httpResponse.close();
            if(isAuthenticated.equals("true")){
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    @GetMapping(path = "getAllCourse", produces = "application/json")
    public List<Course> getAllCourse(@RequestHeader(value = "token") String token){
        if(validateToken(token)){
            System.out.println("User is authenticated");
            return courseService.getAllCourse();
        }
        else {
            System.out.println("User is not authenticated");
            return null;
        }

    }

    @GetMapping(path = "get/{courseNum}",produces = "application/json")
    public Course getCourseByCourseNum(@RequestHeader(value = "token") String token,@PathVariable String courseNum){
        if(validateToken(token)){
            System.out.println("User is authenticated");
            return courseService.getCourseByCourseNum(courseNum);
        }
        else {
            System.out.println("User is not authenticated");
            return null;
        }
    }

    @PostMapping(path = "createCourse",produces = "application/json")
    public String createCourse(@RequestHeader(value = "token") String token,
                               @RequestParam(name = "courseNum") String courseNum,
                                @RequestParam(name = "title") String title,
                                @RequestParam(name = "professor") String professor,
                                @RequestParam(name = "description") String description,
                                @RequestParam(name = "keyWord") String keyWord,
                                @RequestParam(name = "cover") String cover){
        if(validateToken(token)){
            System.out.println("User is authenticated");
            Boolean result = courseService.createCourse(courseNum, title, professor, description, keyWord, cover);
            if(result){
                return "Created new course successfully";
            }
            return "Create course fail";
        }
        else {
            System.out.println("User is not authenticated");
            return "User is not authenticated";
        }

    }

    @PutMapping(path = "updateCourse",produces = "application/json")
    public String updateCourse(@RequestHeader(value = "token") String token,
                               @RequestParam(name = "courseNum") String courseNum,
                               @RequestParam(name = "title") String title,
                               @RequestParam(name = "professor") String professor,
                               @RequestParam(name = "description") String description,
                               @RequestParam(name = "keyWord") String keyWord,
                               @RequestParam(name = "cover") String cover){
        if(validateToken(token)){
            System.out.println("User is authenticated");
            Boolean result = courseService.updateCourse(courseNum, title, professor, description, keyWord, cover);
            if(result){
                return "Updated course successfully";
            }
            return "Update course fail";
        }
        else {
            System.out.println("User is not authenticated");
            return "User is not authenticated";
        }
    }

    @DeleteMapping(path = "deleteCourse/{courseNum}",produces = "application/json")
    public String deleteCourse(@RequestHeader(value = "token") String token,@PathVariable String courseNum){
        if(validateToken(token)){
            System.out.println("User is authenticated");
            if(courseNum.equals("null")||courseNum.length()==0){
                return "Please enter a course Number";
            }
            Boolean result = courseService.deleteCourse(courseNum);
            if(result){
                return "Deleted course successfully";
            }
            return "delete course fail";
        }
        else {
            System.out.println("User is not authenticated");
            return "User is not authenticated";

        }

    }

    @GetMapping(path = "getCover/{courseNum}",produces = "application/json")
    public String getCourseCover(@RequestHeader(value = "token") String token,@PathVariable String courseNum){
        if(validateToken(token)){
            System.out.println("User is authenticated");
            if(courseNum.equals("null")||courseNum.length()==0){
                return "Please enter a course Number";
            }
            return courseService.getCourseCover(courseNum);
        }
        else{
            System.out.println("User is not authenticated");
            return "User is not authenticated";
        }


    }

    @PutMapping(path = "updateCover/{courseNum}",produces = "application/json")
    public String updateCourseCover(@RequestHeader(value = "token") String token,@PathVariable String courseNum, @RequestParam(name = "cover") String cover){
        if(validateToken(token)){
            System.out.println("User is authenticated.");
            if(courseNum.equals("null")||courseNum.length()==0){
                return "Please enter a course Number";
            }
            Boolean result = courseService.updateCourseCover(courseNum,cover);
            if(result){
                return "updated course cover successfully";
            }
            return "update course cover fail";
        }
        else {
            System.out.println("User is not authenticated");
            return "User is not authenticated";
        }



    }

    @PostMapping(path = "createComment",produces = "application/json")
    public String createComment(@RequestHeader(value = "token") String token,
                                @RequestParam(name = "username") String username,
                                @RequestParam(name = "courseNum") String courseNum,
                                @RequestParam(name = "comments") String comments){

        if(validateToken(token)){
            System.out.println("User is authenticated.");
            if(courseNum.equals("null")||courseNum.length()==0||username.equals("null")||username.length()==0){
                return "Please enter a course Number or an username";
            }
            Boolean result = courseService.createComment(username, courseNum, comments);
            if(result){
                return "created course comment successfully";
            }
            return "create course comment fail";
        }
        else{
            System.out.println("User is not authenticated");
            return "User is not authenticated";
        }

    }
    @PutMapping(path = "updateComment/{username}/{courseNum}",produces = "application/json")
    public String updateComment(@RequestHeader(value = "token") String token,
                                @PathVariable("username") String username,
                                @PathVariable("courseNum") String courseNum,
                                @RequestParam(name = "comments") String comments){
        if(validateToken(token)){
            System.out.println("User is authenticated.");
            if(courseNum.equals("null")||courseNum.length()==0||username.equals("null")||username.length()==0){
                return "Please enter a course Number or an username";
            }
            Boolean result = courseService.updateComment(username, courseNum, comments);
            if(result){
                return "updated course comment successfully";
            }
            return "update course comment fail";
        }
        else{
            System.out.println("User is not authenticated");
            return "User is not authenticated";
        }

    }

    @GetMapping(path = "getAllComment",produces = "application/json")
    public List<Comment> getAllComment(@RequestHeader(value = "token") String token){
        if(validateToken(token)){
            System.out.println("User is authenticated.");
            return courseService.getAllComment();
        }
        else{
            System.out.println("User is not authenticated");
            return null;
        }
    }

    @GetMapping(path = "getComment/{courseNum}",produces = "application/json")
    public List<Comment> getCommentByCourseNum(@RequestHeader(value = "token") String token,
                                               @PathVariable String courseNum){
        if(validateToken(token)){
            System.out.println("User is authenticated.");
            return courseService.getCommentByCourseNum(courseNum);
        }
        else{
            System.out.println("User is not authenticated");
            return null;
        }

    }

    @DeleteMapping(path = "deleteComment/{username}/{courseNum}",produces = "application/json")
    public String deleteComment(@RequestHeader(value = "token") String token,
                                @PathVariable("username") String username,
                                @PathVariable("courseNum") String courseNum){
        if(validateToken(token)) {
            System.out.println("User is authenticated.");
            Boolean result = courseService.deleteComment(username,courseNum);
            if(result){
                return "deleted course comment successfully";
            }
            else{
                return "delete course comment fail";
            }
        }
        else{
            System.out.println("User is not authenticated");
            return "User is not authenticated";
        }

    }


    @GetMapping(value = "searchGoogleBook/{name}",produces = "application/json")
    public ResponseEntity<GoogleBook> getBook(@RequestHeader(value = "token") String token,
                                              @PathVariable("name") String name) {
        if(validateToken(token)) {
            System.out.println("User is authenticated.");
            name = name.replaceAll(" ", "+");
            String url = googleBooksApiUrl + name + "&maxResults=5";

            // Get the rest template
            RestTemplate restTemplate = new RestTemplate();

            // Call the api to get books
            ResponseEntity<GoogleBook> responseEntity = restTemplate
                    .getForEntity(url, GoogleBook.class );

            GoogleBook book = GoogleBook.builder().build();
            book = responseEntity.getBody();
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        else{
            System.out.println("User is not authenticated");
            return null;
        }

    }








}
