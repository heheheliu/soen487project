package com.concordia.domain;


import com.concordia.exception.DuplicationCourseException;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Course {
    static Set<String> courseNums = new HashSet<>();
    @XmlAttribute
    private String courseNum;
    @XmlElement
    private String title;
    @XmlElement
    private String professor;
    @XmlElement
    private String description;
    @XmlElement
    private String keyWord;
    @XmlElement
    private String cover;




    public Course() {
    }

    public Course(String courseNum, String title, String professor, String description,
                  String keyWord,String cover) throws DuplicationCourseException
    {
        if(courseNums.contains(courseNum)){
            throw new DuplicationCourseException();
        }
        this.courseNum = courseNum;
        this.title = title;
        this.professor = professor;
        this.description = description;
        this.keyWord = keyWord;
        this.cover = cover;

        courseNums.add(this.courseNum);
    }

    public static Set<String> getCourseNums() {
        return courseNums;
    }

    public static void setCourseNums(Set<String> courseNums) {
        Course.courseNums = courseNums;
    }

    public String getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseNum='" + courseNum + '\'' +
                ", title='" + title + '\'' +
                ", professor='" + professor + '\'' +
                ", description='" + description + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}
