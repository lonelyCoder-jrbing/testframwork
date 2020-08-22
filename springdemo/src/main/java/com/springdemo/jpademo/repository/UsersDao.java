package com.springdemo.jpademo.repository;

import com.springdemo.jpademo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersDao extends JpaRepository<Users, Integer> {

    //方法名称命名规则
    List<Users> findByUserNameIs(String string);

    List<Users> findByUserNameLike(String string);

    List<Users> findByUserNameAndUserAgeGreaterThanEqual(String name, Integer age);

    // 基于@Query 注解的查询
    @Query(value = "from Users where userName = :name")
    List<Users> queryUserByNameUseJPQL(String name);

    @Query(value = "from Users where userName like :name")
    List<Users> queryUserByLikeNameUseJPQL(String name);

    @Query("from Users where userName = :name and userAge >= :age")
    List<Users> queryUserByNameAndAge(String name, Integer age);

    //使用@Query注解查询SQL
    //nativeQuery:默认的是false.表示不开启sql查询。是否对value中的语句做转义。
    @Query(value = "select * from t_users where user_name = ?", nativeQuery = true)
    List<Users> queryUserByNameUseSQL(String name);

    @Query(value = "select * from t_users where user_name like ?", nativeQuery = true)
    List<Users> queryUserByLikeNameUseSQL(String name);

    @Query(value = "select * from t_users where user_name = ? and user_age >= ?", nativeQuery = true)
    List<Users> queryUserByNameAndAgeUseSQL(String name, Integer age);

    @Query("update Users set userAge = :age where userId = :id")
    @Modifying
        //@Modifying当前语句是一个更新语句
    void updateUserAgeById(Integer age, Integer id);


}