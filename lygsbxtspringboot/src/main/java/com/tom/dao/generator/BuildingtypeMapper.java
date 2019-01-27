package com.tom.dao.generator;

import com.tom.model.generator.Buildingtype;
import com.tom.model.generator.BuildingtypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuildingtypeMapper {
    long countByExample(BuildingtypeExample example);

    int deleteByExample(BuildingtypeExample example);

    int deleteByPrimaryKey(String typeid);

    int insert(Buildingtype record);

    int insertSelective(Buildingtype record);

    List<Buildingtype> selectByExample(BuildingtypeExample example);

    Buildingtype selectByPrimaryKey(String typeid);

    int updateByExampleSelective(@Param("record") Buildingtype record, @Param("example") BuildingtypeExample example);

    int updateByExample(@Param("record") Buildingtype record, @Param("example") BuildingtypeExample example);

    int updateByPrimaryKeySelective(Buildingtype record);

    int updateByPrimaryKey(Buildingtype record);
}