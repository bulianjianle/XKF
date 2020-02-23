package com.credit.mapper;

import com.credit.pojo.Prize;
import com.credit.pojo.PrizeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PrizeMapper {
    int countByExample(PrizeExample example);

    int deleteByExample(PrizeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Prize record);

    int insertSelective(Prize record);

    List<Prize> selectByExample(PrizeExample example);

    Prize selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Prize record, @Param("example") PrizeExample example);

    int updateByExample(@Param("record") Prize record, @Param("example") PrizeExample example);

    int updateByPrimaryKeySelective(Prize record);

    int updateByPrimaryKey(Prize record);
}