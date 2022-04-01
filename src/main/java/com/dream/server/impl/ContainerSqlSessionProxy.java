package com.dream.server.impl;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public record ContainerSqlSessionProxy(MybatisDatabaseManager databaseManager) implements SqlSession
{

    @Override
    public <T> T selectOne(String statement)
    {
        return databaseManager.getSqlSession().selectOne(statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter)
    {
        return databaseManager.getSqlSession().selectOne(statement, parameter);
    }

    @Override
    public <E> List<E> selectList(String statement)
    {
        return databaseManager.getSqlSession().selectList(statement);
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter)
    {
        return databaseManager.getSqlSession().selectList(statement, parameter);
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds)
    {
        return databaseManager.getSqlSession().selectList(statement, parameter, rowBounds);
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, String mapKey)
    {
        return databaseManager.getSqlSession().selectMap(statement, mapKey);
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey)
    {
        return databaseManager.getSqlSession().selectMap(statement, parameter, mapKey);
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds)
    {
        return databaseManager.getSqlSession().selectMap(statement, parameter, mapKey, rowBounds);
    }

    @Override
    public <T> Cursor<T> selectCursor(String statement)
    {
        return databaseManager.getSqlSession().selectCursor(statement);
    }

    @Override
    public <T> Cursor<T> selectCursor(String statement, Object parameter)
    {
        return databaseManager.getSqlSession().selectCursor(statement, parameter);
    }

    @Override
    public <T> Cursor<T> selectCursor(String statement, Object parameter, RowBounds rowBounds)
    {
        return databaseManager.getSqlSession().selectCursor(statement, parameter, rowBounds);
    }

    @Override
    public void select(String statement, Object parameter, ResultHandler handler)
    {
        databaseManager.getSqlSession().select(statement, parameter, handler);
    }

    @Override
    public void select(String statement, ResultHandler handler)
    {
        databaseManager.getSqlSession().select(statement, handler);
    }

    @Override
    public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler)
    {
        databaseManager.getSqlSession().select(statement, parameter, rowBounds, handler);
    }

    @Override
    public int insert(String statement)
    {
        return databaseManager.getSqlSession().insert(statement);
    }

    @Override
    public int insert(String statement, Object parameter)
    {
        return databaseManager.getSqlSession().insert(statement, parameter);
    }

    @Override
    public int update(String statement)
    {
        return databaseManager.getSqlSession().insert(statement);
    }

    @Override
    public int update(String statement, Object parameter)
    {
        return databaseManager.getSqlSession().update(statement, parameter);
    }

    @Override
    public int delete(String statement)
    {
        return databaseManager.getSqlSession().delete(statement);
    }

    @Override
    public int delete(String statement, Object parameter)
    {
        return databaseManager.getSqlSession().delete(statement, parameter);
    }

    @Override
    public void commit()
    {
        databaseManager.getSqlSession().commit();
    }

    @Override
    public void commit(boolean force)
    {
        databaseManager.getSqlSession().commit(force);
    }

    @Override
    public void rollback()
    {
        databaseManager.getSqlSession().rollback();
    }

    @Override
    public void rollback(boolean force)
    {
        databaseManager.getSqlSession().rollback(force);
    }

    @Override
    public List<BatchResult> flushStatements()
    {
        return databaseManager.getSqlSession().flushStatements();
    }

    @Override
    public void close()
    {
        databaseManager.getSqlSession().close();
    }

    @Override
    public void clearCache()
    {
        databaseManager.getSqlSession().clearCache();
    }

    @Override
    public Configuration getConfiguration()
    {
        return databaseManager.getSqlSession().getConfiguration();
    }

    @Override
    public <T> T getMapper(Class<T> type)
    {
        return databaseManager.getSqlSession().getMapper(type);
    }

    @Override
    public Connection getConnection()
    {
        return databaseManager.getSqlSession().getConnection();
    }
}
