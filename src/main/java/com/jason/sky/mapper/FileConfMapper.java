package com.jason.sky.mapper;

import com.jason.sky.entity.FileConf;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileConfMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(FileConf record);

    FileConf selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileConf record);

    /**
     *
     * @param bizType
     * @return
     */
    FileConf selectByBizType(String bizType);
}