package com.concordia.pojo;


import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Album {

    static Set<String> idSet = new HashSet<>();
    @XmlAttribute
    private String code;//primary key
    @XmlAttribute
    private String title;
    @XmlAttribute
    private String description;
    @XmlAttribute
    private int year;
    @XmlElement
    private String author;
    @XmlAttribute
    public byte[] cover;

    public Album(String title, String description, int year, String author, byte[] cover) {
        String id = UUID.randomUUID().toString().toLowerCase().substring(0,6);
        while(idSet.contains(id)){
            id = UUID.randomUUID().toString().toLowerCase().substring(1,7);
        }
        this.code = id;
        this.title = title;
        this.description = description;
        this.year = year;
        this.author = author;
        this.cover = cover;
        idSet.add(this.code);
    }

    public Album(String code, String title, String description, int year, String author, byte[] cover) {
        //if(idSet.contains(code)) throw new DuplicatedIdException();
        this.code = code;
        this.title = title;
        this.description = description;
        this.year = year;
        this.author = author;
        this.cover = cover;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

//    public Artist getArtist() {
//        return artist;
//    }
//
//    public void setArtist(Artist artist) {
//        this.artist = artist;
//    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Album{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", artist='" + author + '\'' +
                '}';
    }
}
