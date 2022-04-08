package com.concordia.exception;

public class DuplicationCourseException extends Exception{
    public DuplicationCourseException(){
        super("This course has been existed!");
    }
}
