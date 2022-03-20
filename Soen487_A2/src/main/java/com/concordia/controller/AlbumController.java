package com.concordia.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.concordia.pojo.Album;
import com.concordia.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping
    public List<Album> getAllAlbum(){
        return albumService.getAllAlbum();
    }
    @GetMapping("{code}")
    public Album getAlbumByCode(@PathVariable String code){
        return albumService.getAlbumByCode(code);
    }

    @PostMapping
    public Boolean saveAlbum(@RequestBody Album album) throws SQLException {
        //Blob blob = null;
        String cover = album.getCover();
        if(cover != null ){
            //blob = new SerialBlob(cover);
        }else{
            System.out.println("cover is empty");
        }
        albumService.createLogEntry(albumService.getTime(),"CREATE", album.getCode());
        return albumService.createAlbum(album.getCode(),album.getTitle(),album.getDescription(),album.getYear(),
                album.getAuthor(),cover);
    }

    @PutMapping
    public Boolean updateAlbum(@RequestBody Album album) throws SQLException {
        //Blob blob = null;
        String cover = album.getCover();
        if(cover != null ){
            //blob = new SerialBlob(cover);
        }else{
            System.out.println("cover is empty");
        }
        albumService.createLogEntry(albumService.getTime(), "UPDATE", album.getCode());
        return albumService.updateAlbum(album.getCode(),album.getTitle(),album.getDescription(),album.getYear(),
                album.getAuthor(),cover);
    }

    @PutMapping("{code}")
    public Boolean updateAlbumCover(@PathVariable String code,@RequestParam(value = "cover",required =false) byte[] cover) throws IOException, SQLException {
        if(code == null){
            return false;
        }
        Blob blob = null;
        blob = new SerialBlob(cover);
        albumService.createLogEntry(albumService.getTime(), "UPDATE", code);
        return albumService.updateAlbumCover(code, blob);

    }

    @DeleteMapping("{code}")
    public Boolean deleteAlbum(@PathVariable String code){
        albumService.createLogEntry(albumService.getTime(), "DELETE", code);
        return albumService.deleteAlbum(code);
    }

    @GetMapping("{currentPage}/{pageSize}")
    public IPage<Album> getAlbumPage(@PathVariable int currentPage, @PathVariable int pageSize){
        return albumService.getAlbumPage(currentPage,pageSize);
    }










}
