package com.example.rest;

import org.example.Album;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumCrud {

    private static List<Album> albums = new ArrayList<>();

//    public AlbumCrud(){
//        albums = new ArrayList<>();
//
//        Album a1 = new Album();
//        a1.setCode("A101");
//        a1.setDescription("null");
//        a1.setTitle("ABC");
//        a1.setYear(1999);
//        a1.setNickname("A");
//        albums.add(a1);
//
//    }


    public String getAlbums() {
        return albums.stream().map(Object::toString).collect(Collectors.joining(".\n"));
    }


    public String getAlbum(String code){
        Album album = albums.stream().filter(album1 -> album1.getCode().equals(code)).findFirst().orElse(null);
        if(album != null){
            return album.toString();
        }
        else{
            return "album not found!";
        }
    }

    public String createAlbum(String code, String title, String des, int year, String name){

        Album newAlbum = new Album();
        newAlbum.setCode(code);
        newAlbum.setTitle(title);
        newAlbum.setDescription(des);
        newAlbum.setYear(year);
        newAlbum.setNickname(name);
        for(Album a : albums){
            if(a.getCode().equals(newAlbum.getCode())){
                return "this Album ISRC exist!";
            }
        }
        albums.add(newAlbum);
        return "create a new Album";

    }

    public String updateAlbumByCode(String code, String title, String des, int year, String name){
        for(Album a : albums){
            if(a.getCode().equals(code)){
                a.setTitle(title);
                a.setDescription(des);
                a.setYear(year);
                a.setNickname(name);
                return "updated ...";
            }
        }
        return "Album not found!!";
    }

    public String deleteAlbum(String code){
        for (Album a : albums) {
            if(a.getCode().equals(code)){
                albums.remove(a);
                return "deleted...";
            }
        }
        return "no such Album!";

    }









}
