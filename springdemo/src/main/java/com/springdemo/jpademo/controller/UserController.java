package com.springdemo.jpademo.controller;

import com.springdemo.jpademo.entity.Users;
import com.springdemo.jpademo.repository.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * create by sumerian on 2020/8/22
 * <p>
 * desc:测试jpa
 **/
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UsersDao usersDao;

    @PostMapping(path = "adduser")
    public void saveUser(@RequestBody Users users) {

        this.usersDao.save(users);

    }

    @GetMapping(path = "findByUsernameIs")
    public List<Users> findByUsernameIs() {
        /**
         * 判断相等的条件，有三种表示方式
         * 1,什么都不写，默认的就是做相等判断
         * 2,Is
         * 3,Equal
         */
        List<Users> list = this.usersDao.findByUserNameIs("王五");
        for (Users users : list) {
            System.out.println(users);
        }
        return list;
    }

    /**
     * 需求：根据用户姓名做Like处理
     * Like:条件关键字
     */
    @GetMapping(path = "findByUsernameLike")
    public List<Users> findByUsernameLike() {
        List<Users> list = this.usersDao.findByUserNameLike("王%");
        for (Users users : list) {
            System.out.println(users);
        }
        return list;
    }

    /**
     * 需求：查询名称为王五，并且他的年龄大于等于22岁
     */
    @GetMapping(path = "findByUsernameAndUserageGreaterThanEqual")
    public List<Users> findByUsernameAndUserageGreaterThanEqual() {
        List<Users> list = this.usersDao.findByUserNameAndUserAgeGreaterThanEqual("王五", 22);
        for (Users users : list) {
            System.out.println(users);
        }
        return list;

    }
    @GetMapping(path = "queryUserByNameUseJPQL")
    public List<Users> queryUserByNameUseJPQL(){
        List<Users> list = this.usersDao.queryUserByNameUseJPQL("王六");
        for (Users users : list) {
            System.out.println(users);
        }
        return list;
    }

    /**
     * 测试@Query查询 JPQL
     */
    @GetMapping(path = "queryUserByLikeNameUseJPQL")
    public List<Users> queryUserByLikeNameUseJPQL(){
        List<Users> list = this.usersDao.queryUserByLikeNameUseJPQL("王%");
        for (Users users : list) {
            System.out.println(users);
        }
        return list;
    }

    /**
     * 测试@Query查询 JPQL
     */
    @GetMapping(path = "queryUserByNameAndAge")
    public List<Users> queryUserByNameAndAge(){
        List<Users> list = this.usersDao.queryUserByNameAndAge("王五", 22);
        for (Users users : list) {
            System.out.println(users);
        }
        return list;
    }


    /**
     * 测试@Query查询 SQL
     */
    @GetMapping(path = "queryUserByNameUseSQL")
    public void queryUserByNameUseSQL(){
        List<Users> list = this.usersDao.queryUserByNameUseSQL("王五");
        for (Users users : list) {
            System.out.println(users);
        }

    }

    /**
     * 测试@Query查询 SQL
     */
    @GetMapping(path = "queryUserByLikeNameUseSQL")
    public void queryUserByLikeNameUseSQL(){
        List<Users> list = this.usersDao.queryUserByLikeNameUseSQL("王%");
        for (Users users : list) {
            System.out.println(users);
        }
    }

    /**
     * 测试@Query查询 SQL
     */
    @GetMapping(path = "queryUserByNameAndAgeUseSQL")
    public void queryUserByNameAndAgeUseSQL(){
        List<Users> list = this.usersDao.queryUserByNameAndAgeUseSQL("王五", 22);
        for (Users users : list) {
            System.out.println(users);
        }
    }

    /**
     * 测试@Query update
     */
    @GetMapping(path = "updateUserAgeById")
    @Transactional(rollbackFor = Throwable.class)
    public void test10(){
        this.usersDao.updateUserAgeById(24, 1);
    }


}
