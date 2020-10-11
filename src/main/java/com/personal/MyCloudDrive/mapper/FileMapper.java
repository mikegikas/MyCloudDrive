package com.personal.MyCloudDrive.mapper;

import com.personal.MyCloudDrive.model.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<File> findAllFileByUserId(int userId);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId}")
    File getFileById(int fileid);

    @Select("SELECT * FROM FILES WHERE filename = #{filename} and userid = #{userId}")
    File getFile(String filename, int userId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES(#{filename}, #{contentType}, #{filesize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(File file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileId}")
    int deleteFileByID(int fileid);
}
