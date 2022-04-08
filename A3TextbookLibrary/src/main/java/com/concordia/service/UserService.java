package com.concordia.service;




public interface UserService {
    boolean createUser(String username, String password);
    String getPassword(String username);
    boolean updateEnrollment(String username,String enrollment);
    String getEnrollment(String username);
}
