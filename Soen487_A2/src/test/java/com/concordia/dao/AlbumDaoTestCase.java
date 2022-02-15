package com.concordia.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.concordia.pojo.Album;
import com.concordia.pojo.Artist;
import org.apache.ibatis.annotations.Select;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AlbumDaoTestCase {
    @Autowired
    private AlbumDao albumDao;


    @Test
    void testGetByCode() {
        String code = "1111";
        LambdaQueryWrapper<Album> lqw = new LambdaQueryWrapper<>();
        lqw.eq(code!=null,Album::getCode,code);

        albumDao.selectList(lqw);
    }

    @Test
    void testGetAll() {
        albumDao.selectList(null);}

    @Test
    void testSave(){
        Album album = new Album();
        album.setCode("8888");
        album.setTitle("wertt");
        album.setDescription("qweer");
        album.setYear(1234);
        album.setAuthor("ttt");
        albumDao.insert(album);

    }

    @Test
    void testUpdate(){
        Album album = new Album();
        album.setCode("8888");
        album.setTitle("wertt1");
        album.setDescription("qweer1");
        album.setYear(12341);
        album.setAuthor("ttt1");
        albumDao.updateById(album);

    }
    @Test
    void testDelete(){
        String code = "1111";
        LambdaQueryWrapper<Album> lqw = new LambdaQueryWrapper<>();
        lqw.eq(code!=null,Album::getCode,code);

        albumDao.delete(lqw);

    }

    @Test
    void testGetPage(){
        IPage page = new Page(1,5);
        albumDao.selectPage(page,null);
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getRecords());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());

    }
    //    @Test
//    void testGetById() {
//        albumDao.getAlbumByCode("1111");
//
//    }
//
//    @Test
//    void testGetAlbumList(){
//        albumDao.getAlbumList();
//    }
//
//    @Test
//    void testGetArtistList(){
//        albumDao.getArtistList();
//    }
//
//    @Test
//    void createArtist(){
//        albumDao.createArtist("ccc","c","cc","rewef");
//    }



}
