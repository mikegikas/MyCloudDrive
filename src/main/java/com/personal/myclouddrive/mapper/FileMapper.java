package com.personal.myclouddrive.mapper;

import com.personal.myclouddrive.model.File;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
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
