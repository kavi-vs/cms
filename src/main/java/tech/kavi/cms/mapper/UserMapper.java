package tech.kavi.cms.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import tech.kavi.cms.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user")
    public List<User> listUser();

    @Insert("INSERT INTO user (name,age) VALUES (#{name},#{age})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    public Integer save(User user);
}
