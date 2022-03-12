package com.concordia.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.concordia.pojo.Album;

import java.sql.Blob;
import java.util.List;

public interface AlbumService {
    Boolean createAlbum(Album album);
    Boolean updateAlbum(Album album);
    Boolean deleteAlbum(String code);
    Album getAlbumByCode(String code);
    List<Album> getAllAlbum();
    IPage<Album> getAlbumPage(int currentPage, int pageSize);


    Boolean createLogEntry(String timestamp,String type,String isrc);
    Boolean updateAlbumCover(String isrc, Blob cover);
    Boolean updateAlbumNoCover(String isrc, String title, String description, String year, String author);
    Boolean deleteAlbumCover(String isrc);
    byte[] getAlbumCover(String isrc);




}
