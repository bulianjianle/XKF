package com.credit.mapper;

import java.util.List;

import com.credit.pojo.Index;
import com.credit.pojo.IndexExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IndexMapper {
    int countByExample(IndexExample example);

    int deleteByExample(IndexExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Index record);

    int insertSelective(Index record);

    List<Index> selectByExample(IndexExample example);

    Index selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Index record, @Param("example") IndexExample example);

    int updateByExample(@Param("record") Index record, @Param("example") IndexExample example);

    int updateByPrimaryKeySelective(Index record);

    int updateByPrimaryKey(Index record);
}