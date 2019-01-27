package com.tom.dao.generator;

import com.tom.model.generator.Maintainorder;
import com.tom.model.generator.MaintainorderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaintainorderMapper {
    long countByExample(MaintainorderExample example);

    int deleteByExample(MaintainorderExample example);

    int deleteByPrimaryKey(String orderid);

    int insert(Maintainorder record);

    int insertSelective(Maintainorder record);

    List<Maintainorder> selectByExample(MaintainorderExample example);

    Maintainorder selectByPrimaryKey(String orderid);

    int updateByExampleSelective(@Param("record") Maintainorder record, @Param("example") MaintainorderExample example);

    int updateByExample(@Param("record") Maintainorder record, @Param("example") MaintainorderExample example);

    int updateByPrimaryKeySelective(Maintainorder record);

    int updateByPrimaryKey(Maintainorder record);
}