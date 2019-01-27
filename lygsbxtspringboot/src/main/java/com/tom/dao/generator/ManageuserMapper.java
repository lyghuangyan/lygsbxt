package com.tom.dao.generator;

import com.tom.model.generator.Manageuser;
import com.tom.model.generator.ManageuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ManageuserMapper {
    long countByExample(ManageuserExample example);

    int deleteByExample(ManageuserExample example);

    int deleteByPrimaryKey(String manageid);

    int insert(Manageuser record);

    int insertSelective(Manageuser record);

    List<Manageuser> selectByExample(ManageuserExample example);

    Manageuser selectByPrimaryKey(String manageid);

    int updateByExampleSelective(@Param("record") Manageuser record, @Param("example") ManageuserExample example);

    int updateByExample(@Param("record") Manageuser record, @Param("example") ManageuserExample example);

    int updateByPrimaryKeySelective(Manageuser record);

    int updateByPrimaryKey(Manageuser record);
}