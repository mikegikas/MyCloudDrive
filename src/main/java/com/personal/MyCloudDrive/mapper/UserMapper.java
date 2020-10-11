package com.personal.MyCloudDrive.mapper;

import com.personal.MyCloudDrive.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Select("SELECT * FROM USERS")
    List<User> findAll();

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUserByUsername(String username);

    @Select("SELECT * FROM USERS WHERE userid = #{userId}")
    User getUserById(Integer userId);

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertUser(User user);

    @Delete("DELETE FROM USERS WHERE username = #{username}")
    int deleteUser(String username);

    @Update("UPDATE USERS SET username = #{username}, password = #{password}, salt = #{salt}, firstname = #{firstname}, lastname = #{lastname} WHERE userid = #{userId}")
    int updateUser(User user);
}
