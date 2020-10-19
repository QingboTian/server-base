package com.server.sso.mapper;

import com.server.sso.pojo.Userinfo;
import com.server.sso.pojo.UserinfoExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface UserinfoMapper {
    @SelectProvider(type=UserinfoSqlProvider.class, method="countByExample")
    int countByExample(UserinfoExample example);

    @DeleteProvider(type=UserinfoSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserinfoExample example);

    @Insert({
        "insert into userinfo (union_id, username, ",
        "password, created, ",
        "status, phone, email, ",
        "gender)",
        "values (#{unionId,jdbcType=VARCHAR}, #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{created,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=INTEGER}, #{phone,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
        "#{gender,jdbcType=INTEGER})"
    })
    int insert(Userinfo record);

    @InsertProvider(type=UserinfoSqlProvider.class, method="insertSelective")
    int insertSelective(Userinfo record);

    @SelectProvider(type=UserinfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="union_id", property="unionId", jdbcType=JdbcType.VARCHAR),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="gender", property="gender", jdbcType=JdbcType.INTEGER)
    })
    List<Userinfo> selectByExample(UserinfoExample example);

    @UpdateProvider(type=UserinfoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Userinfo record, @Param("example") UserinfoExample example);

    @UpdateProvider(type=UserinfoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Userinfo record, @Param("example") UserinfoExample example);
}
