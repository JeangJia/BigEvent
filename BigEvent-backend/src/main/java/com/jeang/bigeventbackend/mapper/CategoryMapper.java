package com.jeang.bigeventbackend.mapper;

import com.jeang.bigeventbackend.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    // 添加分类
    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time) " +
            "values(#{categoryName},#{categoryAlias},#{createUser},now(),now())")
    void add(Category category);

    /*
     * 查询所有分类
     * */
    @Select("select * from category where create_user = #{id}")
    List<Category> list(@Param("id") Integer id);

    /*
     * 查询分类
     * */
    @Select("select * from category where id = #{id} and create_user = #{createUser}")
    Category findById(@Param("id") Integer id, @Param("createUser") Integer createUser);


    /*
     * 更新文章分类
     * */
    @Update("update category " +
            "set category_name = #{categoryName}, category_alias = #{categoryAlias}, update_time = now() " +
            "where id = #{id} and create_user = #{createUser}")
    int update(Category category);

    /*
     * 删除文章分类
     * */
    @Delete("delete from category where id = #{id} and create_user = #{createUser}")
    boolean delete(@Param("createUser") Integer createUser, @Param("id") Integer id);
}
