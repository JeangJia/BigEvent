package com.jeang.bigeventbackend.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jeang.bigeventbackend.pojo.Article;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ArticleMapper {
    /*
     * 添加文章
     * */
    @Insert("insert into article" +
            "(title,content,cover_img,state,category_id,create_user,create_time,update_time) " +
            "values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},now(),now())")
    void add(Article article);

    /*
     * 删除文章
     * */
    @Delete("delete from article where id = #{id} and create_user = #{createUser}")
    boolean delete(@Param("createUser") Integer createUser, @Param("id") Integer id);

    /*
     * 修改文章
     * */
    @Update("update article " +
            "set title = #{title}, content = #{content}, cover_img = #{coverImg}, state = #{state}, category_id = #{categoryId}, update_time = now() " +
            "where id = #{id} and create_user = #{createUser}")
    boolean update(Article article);

    /*
     * 查询文章详情
     * */
    @Select("select * from article where id = #{id} and create_user = #{createUser}")
    Article findById(@Param("id") Integer id, @Param("createUser") Integer createUser);

    /*
     * 分页查询文章列表：categoryId、state 均可选
     * <script> 中可使用全部动态标签，<where> 会自动去掉多余的 and/or
     * 分页由 MybatisPlusInterceptor 中的 PaginationInnerInterceptor 自动改写 SQL
     * */
    @Select("""
            <script>
                select * from article
                <where>
                    create_user = #{createUser}
                    <if test="categoryId != null">
                        and category_id = #{categoryId}
                    </if>
                    <if test="state != null and state != ''">
                        and state = #{state}
                    </if>
                </where>
                order by create_time desc
            </script>
            """)
    IPage<Article> list(Page<Article> page,
                        @Param("createUser") Integer createUser,
                        @Param("categoryId") Integer categoryId,
                        @Param("state") String state);
}
