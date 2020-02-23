package com.credit.mapper;

import java.util.List;

import com.credit.pojo.AllmajorExample;
import com.credit.pojo.AllmajorKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AllmajorMapper {
    int countByExample(AllmajorExample example);

    int deleteByExample(AllmajorExample example);

    int deleteByPrimaryKey(AllmajorKey key);

    int insert(AllmajorKey record);

    int insertSelective(AllmajorKey record);

    List<AllmajorKey> selectByExample(AllmajorExample example);

    int updateByExampleSelective(@Param("record") AllmajorKey record, @Param("example") AllmajorExample example);

    int updateByExample(@Param("record") AllmajorKey record, @Param("example") AllmajorExample example);
}