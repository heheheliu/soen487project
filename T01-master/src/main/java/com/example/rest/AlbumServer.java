package com.example.rest;


import org.example.Album;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("albums")
public class AlbumServer {

    private AlbumCrud albumCrud = new AlbumCrud();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Album> getAlbums(){
        System.out.println("getAlbums called.....");
        return albumCrud.getAlbums();

    }

//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    @Path("{code}")
//    public String getAlbumByCode(@PathParam("code") String code){
//        System.out.println("getAlbumByCode called.....");
//        return albumCrud.getAlbum(code);
//    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAlbum(Album album){

        System.out.println("createAlbum called...");
        albumCrud.createAlbum(album);
    }

//    @PUT
//    @Path("{code}/{title}/{description}/{year}/{name}")
//    public String updateAlbumByCode(@PathParam("code") String code, @PathParam("title") String title,
//                                    @PathParam("description") String description, @PathParam("year") int year,
//                                    @PathParam("name") String name){
//        System.out.println("updateAlbumByCode called...");
//        return albumCrud.updateAlbumByCode(code,title,description,year,name);
//    }

//    @DELETE
//    @Path("{code}")
//    public String deleteAlbum(@PathParam("code") String code){
//        System.out.println("deleteAlbum called...");
//        return albumCrud.deleteAlbum(code);
//    }


}
