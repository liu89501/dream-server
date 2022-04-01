package com.dream.server.database.mapper;

import com.dream.server.database.model.TemplateItem;

public interface TemplateItemMapper {
    int deleteByPrimaryKey(Integer itemGuid);

    int insert(TemplateItem record);

    int insertSelective(TemplateItem record);

    TemplateItem selectByPrimaryKey(Integer itemGuid);

    int updateByPrimaryKeySelective(TemplateItem record);

    int updateByPrimaryKey(TemplateItem record);
}