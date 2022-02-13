package com.example.rest;

import org.example.Album;
import org.example.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumCrud {

    Connection con = null;


    public AlbumCrud(){
        String url = "jdbc:mysql://localhost:3306/dcbapp";
        String username = "root";
        String password = "12345";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public List<Album> getAlbums(){
        List<Album> albums = new ArrayList<>();
        String sql = "select * from album";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                Album a = new Album();
                a.setCode(rs.getString(1));
                a.setTitle(rs.getString(2));
                a.setDescription(rs.getString(3));
                a.setYear(rs.getInt(4));
                Artist artist = new Artist();
                artist.setNickName(rs.getString(5));
                artist.setFirstName(rs.getString(6));
                artist.setLastName(rs.getString(7));
                artist.setShortBio(rs.getString(8));
                a.setArtist(artist);
                albums.add(a);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return albums;
    }

    public void createAlbum(Album album){
        String sql = "insert into album values(?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, album.getCode());
            st.setString(2, album.getTitle());
            st.setString(3, album.getDescription());
            st.setInt(4, album.getYear());
            Artist artist = album.getArtist();
            st.setString(5, artist.getNickName());
            st.setString(6, artist.getFirstName());
            st.setString(7, artist.getLastName());
            st.setString(8, artist.getShortBio());
            st.executeUpdate();


        } catch (Exception e){
            e.printStackTrace();
        }

    }



//    private static List<Album> albums = Collections.synchronizedList(new ArrayList<>());
//
////    public AlbumCrud(){
////        albums = new ArrayList<>();
////
////        Album a1 = new Album();
////        a1.setCode("A101");
////        a1.setDescription("null");
////        a1.setTitle("ABC");
////        a1.setYear(1999);
////        a1.setNickname("A");
////        albums.add(a1);
////
////    }
//
//
//    public String getAlbums() {
//        return albums.stream().map(Object::toString).collect(Collectors.joining(".\n"));
//    }
//
//
//    public String getAlbum(String code){
//        Album album = albums.stream().filter(album1 -> album1.getCode().equals(code)).findFirst().orElse(null);
//        if(album != null){
//            return album.toString();
//        }
//        else{
//            return "album not found!";
//        }
//    }
//
//    public String createAlbum(String code, String title, String des, int year, String name){
//
//        Album newAlbum = new Album();
//        newAlbum.setCode(code);
//        newAlbum.setTitle(title);
//        newAlbum.setDescription(des);
//        newAlbum.setYear(year);
//        newAlbum.setNickname(name);
//        for(Album a : albums){
//            if(a.getCode().equals(newAlbum.getCode())){
//                return "this Album ISRC exist!";
//            }
//        }
//        albums.add(newAlbum);
//        return "create a new Album";
//
//    }
//
//    public String updateAlbumByCode(String code, String title, String des, int year, String name){
//        for(Album a : albums){
//            if(a.getCode().equals(code)){
//                a.setTitle(title);
//                a.setDescription(des);
//                a.setYear(year);
//                a.setNickname(name);
//                return "updated ...";
//            }
//        }
//        return "Album not found!!";
//    }
//
//    public String deleteAlbum(String code){
//        for (Album a : albums) {
//            if(a.getCode().equals(code)){
//                albums.remove(a);
//                return "deleted...";
//            }
//        }
//        return "no such Album!";
//
//    }









}
