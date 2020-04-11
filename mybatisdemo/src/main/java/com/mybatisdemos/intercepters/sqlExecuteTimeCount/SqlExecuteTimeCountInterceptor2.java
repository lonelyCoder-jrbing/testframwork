package com.mybatisdemos.intercepters.sqlExecuteTimeCount;

import com.zaxxer.hikari.pool.HikariProxyPreparedStatement;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Properties;


@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})})
//@Component
public class SqlExecuteTimeCountInterceptor2 implements Interceptor {
    //拦截器拦截mapper.xml中的sql

    private static Logger logger = LoggerFactory.getLogger(SqlExecuteTimeCountInterceptor2.class);

    /**
     * 打印的参数字符串的最大长度
     */
    private final static int MAX_PARAM_LENGTH = 50;

    /**
     * 记录的最大SQL长度
     */
    private final static int MAX_SQL_LENGTH = 200;


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
//        //MappedStatement维护了一条mapper.xml中的select，update，delete，insert节点的封装
//        System.out.printf("statement:  "+invocation.getArgs()[0]);
//        HikariProxyPreparedStatement hikariProxyPreparedStatement = (HikariProxyPreparedStatement)invocation.getArgs()[0];
//        System.out.print("hikariProxyPreparedStatement：  "+hikariProxyPreparedStatement.getFetchDirection());
//        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
//        Object parameter = null;
//        if (invocation.getArgs().length > 1) {
//            parameter = invocation.getArgs()[1];
//        }
//        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
//        Configuration configuration = mappedStatement.getConfiguration();
//        Object returnVal = invocation.proceed();
//        //获取sql语句
//        String sql = getSql(configuration,boundSql);
//        System.out.println("mysbatis 拦截器获取sql");
//        return returnVal;
       return null;
    }

    private String getSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");

        if (parameterObject == null || parameterMappings.size() == 0) {
             return  sql;
        }
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        if(typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())){
            sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
        }else {
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            for (ParameterMapping parameterMapping : parameterMappings) {
                String propertyName = parameterMapping.getProperty();
                if (metaObject.hasGetter(propertyName)) {
                    Object obj = metaObject.getValue(propertyName);
                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
                } else if (boundSql.hasAdditionalParameter(propertyName)) {
                    Object obj = boundSql.getAdditionalParameter(propertyName);
                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
                }
            }
        }
        return sql;
    }
    private String getParameterValue(Object obj) {
        String value = null;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(obj) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value;
    }


    /**
     * 格式化/美化 SQL语句
     *
     * @param sql                  sql 语句
     * @param parameterObject      参数的Map
     * @param parameterMappingList 参数的List
     * @return 格式化之后的SQL
     */
    private String formatSQL(String sql, Object parameterObject, List<ParameterMapping> parameterMappingList) {
        // 输入sql字符串空判断
        if (sql == null || sql.length() == 0) {
            return "";
        }
        // 美化sql
        sql = beautifySql(sql);
        // 不传参数的场景，直接把sql美化一下返回出去
        if (parameterObject == null || parameterMappingList == null || parameterMappingList.size() == 0) {
            return sql;
        }
        return LimitSQLLength(sql);
    }


    /**
     * 返回限制长度之后的SQL语句
     *
     * @param sql 原始SQL语句
     */
    private String LimitSQLLength(String sql) {
        if (sql == null || sql.length() == 0) {
            return "";
        }
        if (sql.length() > MAX_SQL_LENGTH) {
            return sql.substring(0, MAX_SQL_LENGTH);
        } else {
            return sql;
        }
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }


    /**
     * 替换SQL 中? 所对应的值, 只保留前50个字符
     *
     * @param sql     sql语句
     * @param valueOf ?对应的值
     */
    private String replaceValue(String sql, String valueOf) {
        //超过50个字符只取前50个
        if (valueOf != null && valueOf.length() > MAX_PARAM_LENGTH) {
            valueOf = valueOf.substring(0, MAX_PARAM_LENGTH);
        }
        sql = sql.replaceFirst("\\?", valueOf);
        return sql;
    }

    /**
     * 美化sql
     *
     * @param sql sql语句
     */
    private String beautifySql(String sql) {
        sql = sql.replaceAll("[\\s\n ]+", "  ");
        return sql;
    }


}
