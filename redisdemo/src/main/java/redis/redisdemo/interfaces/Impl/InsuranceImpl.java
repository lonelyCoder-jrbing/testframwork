package redis.redisdemo.interfaces.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import redis.redisdemo.interfaces.Insurance;

/**
 * create by sumerian on 2020/6/16
 * <p>
 * desc:
 **/
@Slf4j
//@Service
public class InsuranceImpl implements Insurance {
    @Override
    public void buy1() {
        log.info("InsuranceImpl.......buy1.......");
    }

    @Override
    public void buy2() {
        log.info("InsuranceImpl.......buy2.......");
    }

    @Override
    public void buy3() {
        log.info("InsuranceImpl.......buy3.......");
    }
}
