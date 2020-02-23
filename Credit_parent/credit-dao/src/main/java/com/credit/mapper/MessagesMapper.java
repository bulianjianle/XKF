package com.credit.mapper;

import com.credit.pojo.Messages;
import com.credit.pojo.MessagesExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MessagesMapper {
    int countByExample(MessagesExample example);

    int deleteByExample(MessagesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Messages record);

    int insertSelective(Messages record);

    List<Messages> selectByExample(MessagesExample example);

    Messages selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Messages record, @Param("example") MessagesExample example);

    int updateByExample(@Param("record") Messages record, @Param("example") MessagesExample example);

    int updateByPrimaryKeySelective(Messages record);

    int updateByPrimaryKey(Messages record);
}