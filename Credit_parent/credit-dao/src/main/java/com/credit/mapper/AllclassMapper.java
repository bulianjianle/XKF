package com.credit.mapper;

import java.util.List;

import com.credit.pojo.Allclass;
import com.credit.pojo.AllclassExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AllclassMapper {
    int countByExample(AllclassExample example);

    int deleteByExample(AllclassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Allclass record);

    int insertSelective(Allclass record);

    List<Allclass> selectByExample(AllclassExample example);

    Allclass selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Allclass record, @Param("example") AllclassExample example);

    int updateByExample(@Param("record") Allclass record, @Param("example") AllclassExample example);

    int updateByPrimaryKeySelective(Allclass record);

    int updateByPrimaryKey(Allclass record);
}