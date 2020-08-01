package redis.redisdemo.distributelock.service;

/**
 * create by sumerian on 2020/8/1
 * <p>
 * desc:
 **/
public interface DistributeLockService {

    String method1( String num) throws InterruptedException;


}
