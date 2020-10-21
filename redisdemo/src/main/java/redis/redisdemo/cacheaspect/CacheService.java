package redis.redisdemo.cacheaspect;


import org.springframework.stereotype.Component;

@Component
public class CacheService {


    boolean exists(String str1, String str2) {
        return false;
    }

    String get(String str1, String str2) {
        return "";
    }

    boolean set(String str1, String str2, int var3, String var4) {
        return false;
    }
}
