package redis.redisdemo.service;

import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

/**
 * create by sumerian on 2020/7/29
 * <p>
 * desc:
 **/
public interface TokenService {
    public String createToken();

    void checkToken(HttpServletRequest request) throws ServiceException;



}
