package com.concordia.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.concordia.pojo.Album;
import com.concordia.pojo.Artist;

import com.soen487.log_ws.LogEntry;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Insert("insert into album(code, title, description, year, author, cover)"+
            "values(#{code}, #{title},#{description},#{year},#{author},#{cover})")
    public int createAlbum(String code, String title, String description, int year, String author, Blob cover);
//
//    @Insert("insert into artist(nickname,firstname,lastname,shortbio)" +
//            "values(#{nickname},#{firstname},#{lastname},#{shortbio})")
//    public int createArtist(String nickname,String firstname, String lastname, String shortbio);
    @Update("update album set title=#{title},description=#{description},year=#{year}," +
            "author=#{author},cover=#{cover} where code = #{code}")
    public int updateAlbum(String code,String title, String description, int year, String author, Blob cover);


    @Select("select * from logentry where type = #{type} and " +
            "STRCMP(timestamp,#{from}) >= 0 and STRCMP(timestamp,#{to}) <= 0")
    public List<LogEntry> getLogEntry(String from, String to, String type);

    @Select("select * from logentry order by timestamp")
    public List<LogEntry> getAllLogEntry();


    @Insert("insert into logentry(timestamp,type,isrc) values (#{timestamp},#{type},#{isrc})")
    public int createLogEntry(String timestamp,String type, String isrc);

    @Select("select cover from album where code = #{code}")
    public byte[] getCover(String code);

    @Update("UPDATE album SET title=#{title},description=#{description},year=#{year}," +
            "author=#{author} WHERE code = #{code}")
    public int updateAlbumNoCover(String code, String title, String description, String year, String author);

    @Update("UPDATE album SET cover = #{cover} WHERE code = #{code}")
    public int updateCover(String code,Blob cover);
}
