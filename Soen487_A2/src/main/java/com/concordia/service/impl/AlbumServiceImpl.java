package com.concordia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.concordia.dao.AlbumDao;
import com.concordia.pojo.Album;
import com.concordia.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {


    @Autowired
    private AlbumDao albumDao;

    @Override
    public String getTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = formatter.format(now);
        return date;
    }

    @Override
    public Boolean createAlbum(String code, String title, String description, int year, String author, Blob cover) {
        return albumDao.createAlbum(code, title, description, year, author, cover) > 0;
    }

    @Override
    public Boolean updateAlbum(String code,String title, String description, int year, String author, Blob cover) {
        if(code != null){
            return albumDao.updateAlbum(code, title, description, year, author, cover) > 0;
        }
        return false;

    }

    @Override
    public Boolean deleteAlbum(String code) {
        LambdaQueryWrapper<Album> lqw = new LambdaQueryWrapper<>();
        lqw.eq(code!=null,Album::getCode,code);
        return albumDao.delete(lqw) > 0;
    }

    @Override
    public Album getAlbumByCode(String code) {
        LambdaQueryWrapper<Album> lqw = new LambdaQueryWrapper<>();
        lqw.eq(code!=null,Album::getCode,code);
        return albumDao.selectOne(lqw);
    }

    @Override
    public List<Album> getAllAlbum() {
        return albumDao.selectList(null);
    }

    @Override
    public IPage<Album> getAlbumPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage, pageSize);
        return albumDao.selectPage(page, null);
    }

    @Override
    public Boolean createLogEntry(String timestamp, String type, String isrc) {
        return albumDao.createLogEntry(timestamp,type,isrc) > 0;
    }

    @Override
    public Boolean updateAlbumCover(String isrc, Blob cover) {
        return albumDao.updateCover(isrc,cover) > 0;
    }

    @Override
    public Boolean updateAlbumNoCover(String isrc, String title, String description, String year, String author) {
        return albumDao.updateAlbumNoCover(isrc, title, description, year, author) > 0;
    }

    @Override
    public Boolean deleteAlbumCover(String isrc) {
        return albumDao.updateCover(isrc,null) > 0;
    }

    @Override
    public byte[] getAlbumCover(String isrc) {
        return albumDao.getCover(isrc);
    }
}
