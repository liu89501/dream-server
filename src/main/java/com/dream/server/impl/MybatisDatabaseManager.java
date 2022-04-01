package com.dream.server.impl;

import com.dream.container.ArgsTransaction;
import com.dream.container.DatabaseManager;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisDatabaseManager implements DatabaseManager
{
    private final ThreadLocal<ContextParam> context = new ThreadLocal<>();

    private SqlSessionFactory factory;

    @Override
    public void initialize() throws Exception
    {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        factory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Override
    public boolean isOpenConnection()
    {
        return context.get() != null;
    }

    @Override
    public boolean isUseTransaction()
    {
        return context.get().useTransaction;
    }

    @Override
    public void useTransaction()
    {
        context.get().useTransaction = true;
    }

    @Override
    public void openConnection(boolean autoCommit)
    {
        SqlSession sqlSession = factory.openSession(autoCommit);
        context.set(new ContextParam(sqlSession, false));
    }

    @Override
    public void openConnection(boolean autoCommit, ArgsTransaction argsTransaction)
    {
        ExecutorType executorType = argsTransaction.isBatch() ? ExecutorType.BATCH : ExecutorType.REUSE;
        SqlSession sqlSession = factory.openSession(executorType, autoCommit);
        context.set(new ContextParam(sqlSession, false));
    }

    @Override
    public void commit()
    {
        if (isUseTransaction())
        {
            context.get().sqlSession.commit();
        }
    }

    @Override
    public void rollback()
    {
        if (isUseTransaction())
        {
            context.get().sqlSession.rollback();
        }
    }

    @Override
    public void close()
    {
        context.get().sqlSession.close();
        context.remove();
    }

    public SqlSessionFactory getFactory()
    {
        return factory;
    }

    public SqlSession getSqlSession()
    {
        return context.get().sqlSession;
    }

    private static class ContextParam
    {
        SqlSession sqlSession;
        boolean useTransaction;

        public ContextParam(SqlSession sqlSession, boolean useTransaction)
        {
            this.sqlSession = sqlSession;
            this.useTransaction = useTransaction;
        }
    }
}
