package com.credit.mapper;

import com.credit.pojo.Stujoin;
import com.credit.pojo.StujoinExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface StujoinMapper {
    int countByExample(StujoinExample example);

    int deleteByExample(StujoinExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Stujoin record);

    int insertSelective(Stujoin record);

    List<Stujoin> selectByExample(StujoinExample example);

    Stujoin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Stujoin record, @Param("example") StujoinExample example);

    int updateByExample(@Param("record") Stujoin record, @Param("example") StujoinExample example);

    int updateByPrimaryKeySelective(Stujoin record);

    int updateByPrimaryKey(Stujoin record);
}