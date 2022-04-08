package com.concordia.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testCreateUser(){
        userMapper.createUser("Zhu","2222");

    }
    @Test
    void testUpdateEnrollment(){
        userMapper.updateEnrollment("Zhu","487,445");
    }

    @Test
    void testgetEnrollment(){
        userMapper.getEnrollment("Zhu");
    }




}
