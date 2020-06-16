package redis.redisdemo.interfaces.Impl;

import lombok.extern.slf4j.Slf4j;
import redis.redisdemo.interfaces.Claims;

/**
 * create by sumerian on 2020/6/16
 * <p>
 * desc:
 **/
@Slf4j
public class ClaimsImpl implements Claims {
    @Override
    public void fixLoss1() {
      log.info("ClaimsImpl------fixLoss1....");
    }

    @Override
    public void fixLoss2() {
        log.info("ClaimsImpl------fixLoss2....");
    }

    @Override
    public void fixLoss3() {
        log.info("ClaimsImpl------fixLoss3....");
    }
}
