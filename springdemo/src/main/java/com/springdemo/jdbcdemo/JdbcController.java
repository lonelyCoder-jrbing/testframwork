package com.springdemo.jdbcdemo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * create by sumerian on 2020/10/18
 * <p>
 * desc:测试jdbc的更新操作
 **/
@RestController
@RequestMapping("/jdbc")
@Slf4j
public class JdbcController {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Resource(name = "txTemplate")
    private TransactionTemplate txTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    /****
     * 事务没有回滚的示例
     * @return
     * @throws SQLException
     */
    @RequestMapping("/druidData1")
    public String druidData1() throws SQLException {
        String sql1 = "INSERT INTO star(`id`, `post_id`) VALUES(22, 222)";
        // id=1的主键冲突插入失败
        String sql2 = "INSERT INTO star(`id`, `post_id`) VALUES(1, 111)";
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        log.info("1:{}", conn);
        boolean ac = conn.getAutoCommit();
        conn.setAutoCommit(false);
        try {
            int[] rs2 = jdbcTemplate.batchUpdate(new String[]{sql1, sql2});
            log.info("rs2:==={}", rs2);
            conn.commit();
        } catch (Throwable e) {
            log.error("Error occured, cause by: {}", e.getMessage());
            conn.rollback();
        } finally {
            conn.setAutoCommit(ac);
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.error("Error occurred while closing connectin, cause by: {}", e.getMessage());
                }
            }
        }
        return "test";
    }

    /*****
     * 事务生效的使用方法
     * @return
     */

    @RequestMapping("/druidData2")
    public String runTransactionSamples() {
        String sql1 = "INSERT INTO star(`id`, `post_id`) VALUES(23, 222)";
        // id=1的主键冲突插入失败
        String sql2 = "INSERT INTO star(`id`, `post_id`) VALUES(1, 111)";
        txTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                Object savepoint = transactionStatus.createSavepoint();
                // DML执行
                try {
                    int[] rs2 = jdbcTemplate.batchUpdate(new String[]{sql1, sql2});
                } catch (Throwable e) {
                    log.error("Error occured, cause by: {}", e.getMessage());
                    transactionStatus.setRollbackOnly();
                    // transactionStatus.rollbackToSavepoint(savepoint);
                }
                return null;
            }
        });
        return "test2";
    }

    /******
     * 带参数的sql执行
     * @return
     */
    @RequestMapping("/druidData3")
    public String runTransactionSamples2() {
        String sql1 = "INSERT INTO star(`id`, `post_id`) VALUES(?, ?)";
        Object[] args1 = new Object[]{24, 223};
        String sql2 = "INSERT INTO star(`id`, `post_id`) VALUES(?, ?)";
        Object[] args2 = new Object[]{26, 112};
        txTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                Object savepoint = transactionStatus.createSavepoint();
                // DML执行
                try {
                    int rs1 = jdbcTemplate.update(sql1, args1);
                    int rs2 = jdbcTemplate.update(sql2, args2);
                } catch (Throwable e) {
                    log.error("Error occured, cause by: {}", e.getMessage());
                    transactionStatus.setRollbackOnly();
                    // transactionStatus.rollbackToSavepoint(savepoint);
                }
                return null;
            }
        });
        return "test2";
    }

    /******
     * 带参数的sql执行
     * @return
     */
    @RequestMapping("/druidData4")
    public String runTransactionSamples3() {
        String sql1 = "UPDATE star set post_id =? where id = ? ";
        Object[] args1 = new Object[]{223, 24};
        txTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                Object savepoint = transactionStatus.createSavepoint();
                // DML执行
                try {
                    int rs1 = jdbcTemplate.update(sql1, args1);

                } catch (Throwable e) {
                    log.error("Error occured, cause by: {}", e.getMessage());
                    transactionStatus.setRollbackOnly();
                    // transactionStatus.rollbackToSavepoint(savepoint);
                }
                return null;
            }
        });
        return "test2";
    }


    /****
     * ==============================================================================================
     * NamedParameterJdbcTemplate 使用方法
     * ==============================================================================================
     */

    //使用 map作为参数
    @RequestMapping("/testkInsert")
    public Integer testkInsert() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", 12);
        paramMap.put("code", "0001");
        paramMap.put("name", "张小三");
        paramMap.put("age", 12);
        paramMap.put("phoneNo", "18809297467");
        return namedParameterJdbcTemplate.update(
                "insert into student(id,code,name,age,phoneNo) values (:id,:code,:name,:age,:phoneNo)",
                paramMap
        );
    }


    /****
     * ==============================================================================================
     * BeanPropertySqlParameterSource 作为参数
     * ==============================================================================================
     */


    @Data
    public static class StudentDTO {
        private Integer id;
        private String code;
        private String name;
        private int age;
        private String phoneNo;
        //getter,setter
    }

    /******
     * 使用 map作为参数
     * @return
     */
    @RequestMapping("/testBeanPropertySqlParameterSource")
    public Integer testBeanPropertySqlParameterSource() {
        StudentDTO dto = new StudentDTO();//这个DTO为传入数据
        dto.setId(29);
        dto.setName("小红");
        dto.setCode("studentCode");
        dto.setAge(29);
        dto.setPhoneNo("18809297467");
        return namedParameterJdbcTemplate.update("insert into student(id,code,name,age,phoneNo) values (:id,:code,:name,:age,:phoneNo)",
                new BeanPropertySqlParameterSource(dto));

    }

    /******
     * 使用 MapSqlParameterSource 作为参数
     * @return
     */
    @RequestMapping("/mapSqlParameterSource")
    public Integer testMapSqlParameterSource() {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("id", 30)
                .addValue("code", "studentCode01")
                .addValue("name", "小王")
                .addValue("age", 12)
                .addValue("phoneNo", "18809297467");
        return namedParameterJdbcTemplate.update("insert into student(id,code,name,age,phoneNo) values (:id,:code,:name,:age,:phoneNo)", mapSqlParameterSource);
    }



    /****
     * ==============================================================================================
     * 查询
     * ==============================================================================================
     */
    /****
     * 单行查询
     * @return
     */


    @RequestMapping("/testQuery")
    public Integer testQuery() {
        //查询单个值
        String name = namedParameterJdbcTemplate.queryForObject("select name from student where id = 1  limit 1 ", EmptySqlParameterSource.INSTANCE, String.class);
        log.info("name:=>{}", name);

        //BeanPropertyRowMapper会把下划线转化为驼峰属性
        //结果对象可比实际返回字段多或者少
        StudentDTO stu = namedParameterJdbcTemplate.queryForObject(
                "select * from student limit 1", new HashMap<>(), new BeanPropertyRowMapper<StudentDTO>(StudentDTO.class));
        log.info("testQuery::stu=>{}", stu);

        //返回Map形式的单行数据
        Map<String, Object> studentMap = namedParameterJdbcTemplate.queryForMap("select * from student limit 1", new HashMap<>());
        log.info("testQuery::studentMap=>{}", studentMap);

        return namedParameterJdbcTemplate.queryForObject(
                "select count(*) from student", new HashMap<>(), Integer.class);
    }

    /****
     * 返回列表
     * @return List<String>
     */
    @RequestMapping("/queryForList")
    public List<String> queryForList() {
        return namedParameterJdbcTemplate.queryForList("select name from student", new HashMap<>(), String.class);
    }

    /****
     * 返回多行数据
     * @return List<StudentDTO>
     */
    @RequestMapping("/queryForManyRow")
    public List<StudentDTO> queryForManyRow() {
        return namedParameterJdbcTemplate.query("select * from student", new BeanPropertyRowMapper<>(StudentDTO.class));
    }

    /****
     * 返回多行数据
     * @return List<Map < String, Object>>
     */
    @RequestMapping("/queryForManyRow1")
    public List<Map<String, Object>> queryForManyRow1() {
        return namedParameterJdbcTemplate.queryForList("select * from student", new HashMap<>());
    }


}
