package com.concordia.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.concordia.pojo.Album;
import com.concordia.pojo.Artist;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Blob;
import java.util.List;

@Mapper
public interface AlbumDao extends BaseMapper<Album> {
//    @Select("select * from album where code = #{code}")
//    public Album getAlbumByCode(String code);
//
//    @Select("select * from album")
//    public List<Album> getAlbumList();
//
//    @Select("select * from artist")
//    public List<Artist> getArtistList();
//
//    @Select("select cover from album where code = #{code}")
//    public byte[] getCover(String code);
//
//    @Insert("insert into album(code, title, description, year, author, cover"+
//            "values(#{code}, #{title},#{description},#{year},#{author},#{cover})")
//    public int createAlbum(String code, String title, String description, int year, String author, Blob cover);
//
//    @Insert("insert into artist(nickname,firstname,lastname,shortbio)" +
//            "values(#{nickname},#{firstname},#{lastname},#{shortbio})")
//    public int createArtist(String nickname,String firstname, String lastname, String shortbio);

}
