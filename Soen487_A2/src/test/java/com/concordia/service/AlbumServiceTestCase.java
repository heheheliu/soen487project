package com.concordia.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.concordia.pojo.Album;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AlbumServiceTestCase {
    @Autowired
    private AlbumService albumService;

    @Test
    void testGetByCode(){
        System.out.println(albumService.getAlbumByCode("2222"));
    }

    @Test
    void testGetAll(){
        albumService.getAllAlbum();
    }
    @Test
    void testSave(){
        Album album = new Album();
        album.setCode("21112uuu");
        album.setTitle("yyy");
        album.setDescription("ioiioio");
        album.setYear(9090);
        album.setAuthor("ppp");
        albumService.createAlbum(album);
    }
    @Test
    void testUpdate(){
        Album album = new Album();
        album.setCode("21112uuu");
        album.setTitle("yyy2");
        album.setDescription("ioiioio2");
        album.setYear(90902);
        album.setAuthor("ppp2");
        albumService.updateAlbum(album);
    }
    @Test
    void testDelete(){
        albumService.deleteAlbum("21112uuu");
    }


    @Test
    void testGetPage(){
        IPage page = albumService.getAlbumPage(1,5);

    }

}
