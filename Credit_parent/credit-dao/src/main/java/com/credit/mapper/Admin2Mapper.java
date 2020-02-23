package com.credit.mapper;

import java.util.List;

import com.credit.pojo.Admin2;
import com.credit.pojo.Admin2Example;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface Admin2Mapper {
    int countByExample(Admin2Example example);

    int deleteByExample(Admin2Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin2 record);

    int insertSelective(Admin2 record);

    List<Admin2> selectByExample(Admin2Example example);

    Admin2 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin2 record, @Param("example") Admin2Example example);

    int updateByExample(@Param("record") Admin2 record, @Param("example") Admin2Example example);

    int updateByPrimaryKeySelective(Admin2 record);

    int updateByPrimaryKey(Admin2 record);
}