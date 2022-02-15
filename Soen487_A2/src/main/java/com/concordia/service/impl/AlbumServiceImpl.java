package com.concordia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.concordia.dao.AlbumDao;
import com.concordia.pojo.Album;
import com.concordia.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDao albumDao;

    @Override
    public Boolean createAlbum(Album album) {
        return albumDao.insert(album) > 0;
    }

    @Override
    public Boolean updateAlbum(Album album) {
        String code = album.getCode();
        LambdaQueryWrapper<Album> lqw = new LambdaQueryWrapper<>();
        lqw.eq(code!=null,Album::getCode,code);
        return albumDao.update(album,lqw) > 0;
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
}
