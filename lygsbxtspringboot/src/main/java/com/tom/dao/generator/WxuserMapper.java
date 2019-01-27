package com.tom.dao.generator;

import com.tom.model.generator.Wxuser;
import com.tom.model.generator.WxuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxuserMapper {
    long countByExample(WxuserExample example);

    int deleteByExample(WxuserExample example);

    int deleteByPrimaryKey(String openid);

    int insert(Wxuser record);

    int insertSelective(Wxuser record);

    List<Wxuser> selectByExample(WxuserExample example);

    Wxuser selectByPrimaryKey(String openid);

    int updateByExampleSelective(@Param("record") Wxuser record, @Param("example") WxuserExample example);

    int updateByExample(@Param("record") Wxuser record, @Param("example") WxuserExample example);

    int updateByPrimaryKeySelective(Wxuser record);

    int updateByPrimaryKey(Wxuser record);
}