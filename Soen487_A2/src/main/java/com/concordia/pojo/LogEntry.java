package com.concordia.pojo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogEntry implements Comparable{
    private String isrc;
    private String timeStamp;
    private String type;

    public LogEntry(String isrc, String type){
        this.isrc = isrc;
        this.type = type;
        this.timeStamp = getPostTime();
    }

    public LogEntry(){

    }


    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int compareTo(Object o) {
        return this.timeStamp.compareTo(((LogEntry)o).getTimeStamp());
    }
    public String getPostTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = formatter.format(now);
        return date;
    }
}
