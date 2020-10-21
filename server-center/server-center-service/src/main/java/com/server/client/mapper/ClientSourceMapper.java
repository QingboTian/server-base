package com.server.client.mapper;

import com.server.client.pojo.ClientSourceExample;
import com.server.common.ClientSource;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface ClientSourceMapper {
    @SelectProvider(type=ClientSourceSqlProvider.class, method="countByExample")
    int countByExample(ClientSourceExample example);

    @DeleteProvider(type=ClientSourceSqlProvider.class, method="deleteByExample")
    int deleteByExample(ClientSourceExample example);

    @Delete({
        "delete from client_source",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into client_source (id, app_id, ",
        "app_name)",
        "values (#{id,jdbcType=INTEGER}, #{appId,jdbcType=VARCHAR}, ",
        "#{appName,jdbcType=VARCHAR})"
    })
    int insert(ClientSource record);

    @InsertProvider(type=ClientSourceSqlProvider.class, method="insertSelective")
    int insertSelective(ClientSource record);

    @SelectProvider(type=ClientSourceSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="app_id", property="appId", jdbcType=JdbcType.VARCHAR),
        @Result(column="app_name", property="appName", jdbcType=JdbcType.VARCHAR)
    })
    List<ClientSource> selectByExample(ClientSourceExample example);

    @Select({
        "select",
        "id, app_id, app_name",
        "from client_source",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="app_id", property="appId", jdbcType=JdbcType.VARCHAR),
        @Result(column="app_name", property="appName", jdbcType=JdbcType.VARCHAR)
    })
    ClientSource selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ClientSourceSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ClientSource record, @Param("example") ClientSourceExample example);

    @UpdateProvider(type=ClientSourceSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ClientSource record, @Param("example") ClientSourceExample example);

    @UpdateProvider(type=ClientSourceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ClientSource record);

    @Update({
        "update client_source",
        "set app_id = #{appId,jdbcType=VARCHAR},",
          "app_name = #{appName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ClientSource record);
}
