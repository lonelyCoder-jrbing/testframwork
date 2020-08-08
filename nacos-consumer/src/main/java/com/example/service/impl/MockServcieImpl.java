package com.example.service.impl;

import com.example.service.MockService;

/**
 * create by sumerian on 2020/8/5
 * <p>
 * desc:
 **/
public class MockServcieImpl  implements MockService {
    @Override
    public String getOrderFormat() {
            return "MockService";
    }
}
