package com.springdemo.ehcachedemo.dto;

import com.springdemo.ehcachedemo.entity.Users;
import lombok.Data;

import java.util.List;

/**
 * create by sumerian on 2020/8/24
 * <p>
 * desc:
 **/


@Data
public class GetUserRequest {


    private List<Integer> ids;
    private String group;


}
