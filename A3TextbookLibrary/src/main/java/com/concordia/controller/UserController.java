package com.concordia.controller;

import com.concordia.domain.User;
import com.concordia.exception.DuplicationUserException;
import com.concordia.service.MyResponse;
import com.concordia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController implements Serializable {
    private static ArrayList<User> users = new ArrayList<>();
    private static Map<String, String> tokenUsername = new HashMap<String, String>();
    private static Map<String, Date> tokenExpiration = new HashMap<String, Date>();

    @Autowired
    private UserService userService;


    @PostMapping(path = "register", produces = "application/json")
    public String createUser(@FormParam("username") String username, @FormParam("password") String password) throws DuplicationUserException {
        User user = new User(username, password);
        if(userService.getPassword(username) != null){
            throw new DuplicationUserException();
        }
        else{
            users.add(user);
            userService.createUser(username,password);
            return "Created user: " + username+ " successfully";
        }

    }

    @PostMapping(path = "login", produces = "application/json")
    public Response login(@FormParam("username") String username, @FormParam("password") String password) {
        System.out.println(username +" : "+password);
        User user = users.stream().filter(user1 -> user1.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        MyResponse authResponse;
        Response.Status status;
        if (user != null) {
            if (user.getPassword().equals(password)) {
                user.generateToken();
                System.out.println("Login token1: "+user.getToken());
                tokenUsername.put(user.getToken(), username);
                tokenExpiration.put(user.getToken(), new Date());
                System.out.println("Login token: "+user.getToken());
                authResponse = new MyResponse(true, user.getToken());
                status = Response.Status.OK;
            } else {
                authResponse = new MyResponse(false, "");
                status = Response.Status.UNAUTHORIZED;
            }
        } else {
            authResponse = new MyResponse(false, "");
            status = Response.Status.FORBIDDEN;
        }
        return Response.status(status).entity(authResponse).build();
    }

    @PostMapping(path = "logout")
    @Produces("application/json")
    public String logout(@RequestHeader(value = "token") String token) {
        String u = null;
        for (String t: tokenUsername.keySet())
        {
            if (t.equalsIgnoreCase(token))
            {
                u = tokenUsername.get(t);
                break;
            }
        }
        final String username = u;
        User user = users.stream().filter(user1 -> user1.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        if (user != null) {
            if (user.getToken().equals("")) {
                return "Not logged in.";
            } else {
                tokenUsername.remove(user.getToken());
                tokenExpiration.remove(user.getToken());
                user.destroyToken();
                return "Logged out. Token successfully destroyed.";
            }
        } else {
            return "User does not exist!";
        }
    }

    @GetMapping(path = "enrollment/{username}",produces = "application/json")
    public String getEnrollment(@RequestHeader(value = "token") String token,@PathVariable String username){

        if(validateToken(token).getBody().equals("true")){
            return userService.getEnrollment(username);
        }
        return "access fail";
    }

    @PutMapping(path = "enrollment/{username}")
    public String updateEnrollment(@RequestHeader(value = "token") String token,@PathVariable("username") String username, @FormParam("enrollment") String enrollment){
        if(validateToken(token).getBody().equals("true")){
            if(userService.updateEnrollment(username,enrollment)){
                return "updated enrollment successfully";
            }else {
                return "update enrollment fail";
            }
        }
        return "access fail";
    }


    @PostMapping(path = "auth", produces = MediaType.APPLICATION_JSON_VALUE)
    public static ResponseEntity<String> validateToken(@RequestHeader("token") String token){
//        Map<String, String> body = new HashMap<>();
        if (tokenUsername.containsKey(token)) {
//            body.put("success", "true");
            Date timeNow = new Date();
            long diff = timeNow.getTime() - tokenExpiration.get(token).getTime();
            long tokenDuration = TimeUnit.MILLISECONDS.toMinutes(diff);
            System.out.println("Duration: " + tokenDuration);
            if (tokenDuration > 30) {
                tokenUsername.remove(token);
                tokenExpiration.remove(token);
            } else {
                return new ResponseEntity<String>("true", HttpStatus.OK);
            }
        }
//        body.put("success", "false");
        return new ResponseEntity<String>("false", HttpStatus.UNAUTHORIZED);
    }


}
