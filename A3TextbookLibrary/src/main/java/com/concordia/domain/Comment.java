package com.concordia.domain;


import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Comment {
    @XmlAttribute
    private String username;
    @XmlAttribute
    private String courseNum;
    @XmlElement
    private String comments;

    public Comment(){

    }

    public Comment(String username, String courseNum, String comments) {
        this.username = username;
        this.courseNum = courseNum;
        this.comments = comments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getComment() {
        return comments;
    }

    public void setComment(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "username='" + username + '\'' +
                ", courseNum='" + courseNum + '\'' +
                ", comment='" + comments + '\'' +
                '}';
    }
}
