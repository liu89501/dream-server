package com.dream.server.impl;

import com.dream.container.DatabaseManager;
import com.dream.container.DependencyDesc;
import com.dream.container.Params;
import com.dream.container.anno.Assign;
import com.dream.container.anno.Component;
import com.dream.container.anno.ToContainer;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Collection;
import java.util.List;

@Component(proxy = false)
public class MybatisDependencies
{
    @Assign
    private DatabaseManager databaseManager;

    @ToContainer
    public List<DependencyDesc> mappers()
    {
        MybatisDatabaseManager mybatisDatabaseManager = (MybatisDatabaseManager) this.databaseManager;
        SqlSessionFactory factory = mybatisDatabaseManager.getFactory();
        Collection<Class<?>> mappers = factory.getConfiguration().getMapperRegistry().getMappers();

        ContainerSqlSessionProxy proxySqlSession = new ContainerSqlSessionProxy(mybatisDatabaseManager);

        return mappers.stream()
                .map(e -> new DependencyDesc(e, factory.getConfiguration().getMapper(e, proxySqlSession), Params.DEFAULT_UID))
                .toList();
    }
}
