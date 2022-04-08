package com.concordia.dao;

import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper {
    @Insert("insert into user " + "values(#{username}, #{password}, null)")
    public int createUser(String username, String password);

    @Select("select password from user where username = #{username}")
    public String getPassword(String username);

    @Update("update user Set enrollment=#{enrollment} where username=#{username}")
    public int updateEnrollment(String username,String enrollment);

    @Select("select enrollment from user where username = #{username}")
    public String getEnrollment(String username);









}
