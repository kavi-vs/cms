package tech.kavi.cms.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import tech.kavi.cms.entity.Book;

import java.util.List;

@Mapper
public interface BookMapper {

    @Select("select * from book")
    public List<Book> listBook();

    @Insert("INSERT INTO book (name) VALUES (#{name}=)")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    public Integer save(Book book);
}
