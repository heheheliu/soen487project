package com.concordia.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.concordia.pojo.Album;
import com.concordia.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public Boolean saveAlbum(@RequestBody Album album){
        return albumService.createAlbum(album);
    }

    @PutMapping
    public Boolean updateAlbum(@RequestBody Album album){
        return albumService.updateAlbum(album);
    }

    @DeleteMapping("{code}")
    public Boolean deleteAlbum(@PathVariable String code){
        return albumService.deleteAlbum(code);
    }

    @GetMapping("{currentPage}/{pageSize}")
    public IPage<Album> getAlbumPage(@PathVariable int currentPage, @PathVariable int pageSize){
        return albumService.getAlbumPage(currentPage,pageSize);
    }










}
