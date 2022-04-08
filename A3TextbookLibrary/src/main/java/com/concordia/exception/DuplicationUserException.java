package com.concordia.exception;

public class DuplicationUserException extends Exception{
    public DuplicationUserException(){
        super("this username has been exist!");
    }
}
