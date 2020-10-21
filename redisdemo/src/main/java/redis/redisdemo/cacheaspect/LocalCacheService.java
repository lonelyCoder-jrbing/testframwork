package redis.redisdemo.cacheaspect;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class LocalCacheService {
    Object getOrElsePut(Function function, String cacheKey, int localSeconds,
                        int localSize, UDFun udFun) {
        return null;
    }

    Object get(String obj, String cacheKey, int localSeconds, int localSize) {
        return null;
    }

    boolean set(String str, String cacheKey, Object obj, int localSeconds, int localSize) {
        return false;
    }


    interface UDFun {
        Object apply();

    }


}
