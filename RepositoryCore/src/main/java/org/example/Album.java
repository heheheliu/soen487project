package org.example;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "album")
@XmlAccessorType(XmlAccessType.FIELD)
public class Album {

    @XmlElement
    private String code;//primary key
    @XmlElement
    private String title;
    @XmlElement
    private String description;
    @XmlElement
    private int year;
    @XmlElement
    private Artist artist;


    public Album(String code, String title, String description, int year, Artist artist) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.year = year;
        this.artist = artist;

    }

    public Album(){

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }



    @Override
    public String toString() {
        return "Album{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", artist='" + artist + '\'' +
                '}';
    }
}
