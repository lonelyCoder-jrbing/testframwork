package com.mybatisdemos.intercepters.sqlExecuteTimeCount;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.List;
import java.util.Properties;
/*
使用@Component注解来把我们自己新增的一个插件注入到ioc中,执行sql就会自动进行拦截,打印每条sql的时间.

当然了,这个还可以做一些扩展:

1.自定义慢SQL的时间,写入到配置文件中, 从配置中获取自定义慢SQL的时间,对sql执行时间进行判断,保存慢sql,进行分析比对

2.对执行的sql进行参数的替换,现在记录的SQL参数都是? , 其实作为分析用是够了,所以这个影响并不大.
————————————————
版权声明：本文为CSDN博主「长河」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/u010398771/article/details/93973054
 */


@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})})
@Component
public class SqlExecuteTimeCountInterceptor implements Interceptor {
 
    private static Logger logger = LoggerFactory.getLogger(SqlExecuteTimeCountInterceptor.class);
 
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
        Object target = invocation.getTarget();
        long startTime = System.currentTimeMillis();
        StatementHandler statementHandler = (StatementHandler) target;
        try {
            return invocation.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long timeCount = endTime - startTime;

            BoundSql boundSql = statementHandler.getBoundSql();
            String sql = boundSql.getSql();
            Object parameterObject = boundSql.getParameterObject();
            List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();

            // 格式化Sql语句，去除换行符，替换参数
//            sql = formatSQL(sql, parameterObject, parameterMappingList);

            logger.info("执行 SQL：[ , {} ]执行耗时[ {} ms]", sql, timeCount);
        }
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
