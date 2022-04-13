package com.concordia.domain;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.SecureRandom;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable{
    @XmlAttribute
    private static final int TOKEN_LENGTH = 20;
    @XmlAttribute
    private String username;
    @XmlAttribute
    private String password;
    @XmlAttribute
    private String token;
    @XmlElement
    private String enrollment;

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getToken(){
        return this.token;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", enrollment='" + enrollment + '\'' +
                '}';
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String doHashing(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytesOfMessage = md.digest();
            byte[] hash = md.digest(bytesOfMessage);
            StringBuilder sb = new StringBuilder();
            for(byte b : bytesOfMessage){
                sb.append(String.format("%02x", b));
            }
            String pass = sb.toString();
            return pass;

        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public void generateToken(){
        String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
        String CHAR_UPPER = CHAR_LOWER.toUpperCase();
        String NUMBER = "0123456789";
        String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;

        StringBuilder sb = new StringBuilder(TOKEN_LENGTH);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < TOKEN_LENGTH; i++) {
            // 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }
        this.token = sb.toString();
    }

    public void destroyToken(){
        this.token = "";
    }

}
