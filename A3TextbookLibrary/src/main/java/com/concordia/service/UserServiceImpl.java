package com.concordia.service;


import com.concordia.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean createUser(String username, String password) {
        return userMapper.createUser(username,password) > 0;
    }

    @Override
    public String getPassword(String username) {
        return userMapper.getPassword(username);
    }

    @Override
    public boolean updateEnrollment(String username, String enrollment) {
        return userMapper.updateEnrollment(username,enrollment) > 0;
    }

    @Override
    public String getEnrollment(String username) {
        return userMapper.getEnrollment(username);
    }
}
