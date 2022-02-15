package com.concordia.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.concordia.pojo.Album;

import java.util.List;

public interface AlbumService {
    Boolean createAlbum(Album album);
    Boolean updateAlbum(Album album);
    Boolean deleteAlbum(String code);
    Album getAlbumByCode(String code);
    List<Album> getAllAlbum();
    IPage<Album> getAlbumPage(int currentPage, int pageSize);

}
