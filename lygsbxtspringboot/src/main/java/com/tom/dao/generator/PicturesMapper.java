package com.tom.dao.generator;

import com.tom.model.generator.Pictures;
import com.tom.model.generator.PicturesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PicturesMapper {
    long countByExample(PicturesExample example);

    int deleteByExample(PicturesExample example);

    int deleteByPrimaryKey(String pictureid);

    int insert(Pictures record);

    int insertSelective(Pictures record);

    List<Pictures> selectByExample(PicturesExample example);

    Pictures selectByPrimaryKey(String pictureid);

    int updateByExampleSelective(@Param("record") Pictures record, @Param("example") PicturesExample example);

    int updateByExample(@Param("record") Pictures record, @Param("example") PicturesExample example);

    int updateByPrimaryKeySelective(Pictures record);

    int updateByPrimaryKey(Pictures record);
}