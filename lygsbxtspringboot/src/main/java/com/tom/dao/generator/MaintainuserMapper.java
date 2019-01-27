package com.tom.dao.generator;

import com.tom.model.generator.Maintainuser;
import com.tom.model.generator.MaintainuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaintainuserMapper {
    long countByExample(MaintainuserExample example);

    int deleteByExample(MaintainuserExample example);

    int deleteByPrimaryKey(String maintainid);

    int insert(Maintainuser record);

    int insertSelective(Maintainuser record);

    List<Maintainuser> selectByExample(MaintainuserExample example);

    Maintainuser selectByPrimaryKey(String maintainid);

    int updateByExampleSelective(@Param("record") Maintainuser record, @Param("example") MaintainuserExample example);

    int updateByExample(@Param("record") Maintainuser record, @Param("example") MaintainuserExample example);

    int updateByPrimaryKeySelective(Maintainuser record);

    int updateByPrimaryKey(Maintainuser record);
}