package com.tom.dao.generator;

import com.tom.model.generator.Officeuser;
import com.tom.model.generator.OfficeuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OfficeuserMapper {
    long countByExample(OfficeuserExample example);

    int deleteByExample(OfficeuserExample example);

    int deleteByPrimaryKey(String officerid);

    int insert(Officeuser record);

    int insertSelective(Officeuser record);

    List<Officeuser> selectByExample(OfficeuserExample example);

    Officeuser selectByPrimaryKey(String officerid);

    int updateByExampleSelective(@Param("record") Officeuser record, @Param("example") OfficeuserExample example);

    int updateByExample(@Param("record") Officeuser record, @Param("example") OfficeuserExample example);

    int updateByPrimaryKeySelective(Officeuser record);

    int updateByPrimaryKey(Officeuser record);
}