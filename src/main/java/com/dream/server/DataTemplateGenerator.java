package com.dream.server;

import com.dream.server.database.mapper.GameStoreItemMapper;
import com.dream.server.database.mapper.TaskTemplateMapper;
import com.dream.server.database.mapper.TemplateItemMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.dream.server.database.model.GameStoreItemWithBLOBs;
import com.dream.server.database.model.TaskTemplateWithBLOBs;
import com.dream.server.database.model.TemplateItem;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import java.util.List;

public class DataTemplateGenerator
{
    public static void main(String[] args) throws Exception
    {
        //String path = args[0];
        //ParserConfig.getGlobalInstance().addAccept("com.dream.");

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = factory.openSession(ExecutorType.BATCH, false);

        generateStoreItems(sqlSession, "D:/gen_items.json");
        generateTasks(sqlSession, "D:/gen_task.json");

        sqlSession.commit();
        sqlSession.close();
    }

    static void generateTasks(SqlSession sqlSession, String jsonFilePath) throws IOException
    {
        TaskTemplateMapper templateMapper = sqlSession.getMapper(TaskTemplateMapper.class);
        List<TaskTemplateWithBLOBs> templates = resolverJson(jsonFilePath, new TypeReference<>(){});

        for (TaskTemplateWithBLOBs template : templates)
        {
            templateMapper.insert(template);
        }
    }

    static void generateStoreItems(SqlSession sqlSession, String jsonFilePath) throws IOException
    {
        GameStoreItemMapper storeItemMapper = sqlSession.getMapper(GameStoreItemMapper.class);
        List<GameStoreItemWithBLOBs> templates = resolverJson(jsonFilePath, new TypeReference<>(){});
        for (GameStoreItemWithBLOBs template : templates)
        {
            storeItemMapper.insert(template);
        }
    }

    static void generateItems(SqlSession sqlSession, String jsonFilePath) throws IOException
    {
        TemplateItemMapper templateMapper = sqlSession.getMapper(TemplateItemMapper.class);
        List<TemplateItem> templates = resolverJson(jsonFilePath, new TypeReference<>(){});
        for (TemplateItem template : templates)
        {
            templateMapper.insert(template);
        }
    }

    private static <T> T resolverJson(String jsonFilePath, TypeReference<T> typeReference) throws IOException
    {
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.readValue(new File(jsonFilePath), typeReference);
    }
}
