package redis.redisdemo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerResponse;
import redis.redisdemo.service.TokenService;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;
import java.time.Duration;
import java.util.UUID;


/**
 * create by sumerian on 2020/7/29
 * <p>
 * desc:
 **/
@Service
@Slf4j
public class TokenServiceImpl implements TokenService {
    private static final String TOKEN_NAME = "token";
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String createToken() {

        String str = UUID.randomUUID().toString();
        StrBuilder token = new StrBuilder();
        token.append(Constants.TOKEN_PREFIX).append(str);
        log.info("token:  =={}", token);
        redisTemplate.opsForValue().set(token.toString(), token.toString(), Duration.ofSeconds(Constants.EXPIRE_TIME_MINUTE));

        return token.toString();
    }

    @Override
    public void checkToken(HttpServletRequest request) throws ServiceException {
        String token = request.getHeader(TOKEN_NAME);
        log.info("checkToken====={}", token);
        // header中不存在token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(TOKEN_NAME);
            // parameter中也不存在token
            if (StringUtils.isBlank(token)) {
                throw new ServiceException("");
            }
        }
        String s = redisTemplate.opsForValue().get(token);
        log.info("s=={}", s);
        if (redisTemplate.opsForValue().get(token) == null) {
            //第一次已经将token删除掉，所以在第二次访问的时候，已经get不到值了，所以抛出一个异常
            throw new ServiceException("请勿重复提交");
        }
        Boolean delete = redisTemplate.delete(token);
        log.info("delete=={}", delete);
        if (!delete) {
            throw new ServiceException("删除token失败!");
        }
    }
}

