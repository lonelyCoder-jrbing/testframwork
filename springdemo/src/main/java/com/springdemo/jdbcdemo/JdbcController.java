package com.springdemo.jdbcdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;

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
        Object[] args1 = new Object[] {24, 223};
        String sql2 = "INSERT INTO star(`id`, `post_id`) VALUES(?, ?)";
        Object[] args2 = new Object[] {26, 112};
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
        Object[] args1 = new Object[] {223,24};
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








}
