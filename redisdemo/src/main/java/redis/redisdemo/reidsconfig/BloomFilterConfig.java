package redis.redisdemo.reidsconfig;

/**
 * create by sumerian on 2020/8/7
 * <p>
 * desc:
 **/

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class BloomFilterConfig {

    private static final String NEW_BLOOM_FILTER_LUA = "return redis.call('bf.reserve', KEYS[1],ARGV[1],ARGV[2])";
    private static final String BLOOM_FILTER_BATCH_PUT =
            "local key = KEYS[1] local args = ARGV local result = {} " +
                    "for i,v in ipairs(args) do " +
                    "local exists_result = redis.call('bf.add', key, v) " +
                    "table.insert(result,exists_result) " +
                    "end " +
                    "return result";
    private static final String BLOOM_FILTER__BATCH_EXITS =
            "local key = KEYS[1] local args = ARGV local result = {} " +
                    "for i,v in ipairs(args) do " +
                    "local exists_result = redis.call('bf.exists', key, v) " +
                    "table.insert(result,exists_result) " +
                    "end " +
                    "return result";
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 新建布隆过滤器
     * 重复调用会删除旧的过滤器,并新建一个
     *
     * @param key                键
     * @param expectedInsertions 预计数据量
     * @param fpp                容错率
     * @return BloomResult  BloomResult.executeResult: 成功/失败
     */
    public BloomResult newBloomFilter(String key, long expectedInsertions, double fpp) {
        Assert.hasLength(key, "key can not be null or empty");
        Assert.isTrue(expectedInsertions > 0, "expectedInsertions  must greater than 0 ");
        Assert.isTrue(fpp > 0 && fpp < 1, "fpp  must greater than 0 and less than 1");
        boolean result = false;
        try {
            redisTemplate.delete(key);
            val script = new DefaultRedisScript<Boolean>(NEW_BLOOM_FILTER_LUA, Boolean.class);
            result = redisTemplate.execute(script, Collections.singletonList(key), fpp, expectedInsertions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(" newBloomFilter: {}  {} ", key, result);
        return new BloomResult(result, null);
    }

    /**
     * 批量添加多个值到布隆过滤器中
     *
     * @param key       键值
     * @param valueList 需添加到布隆中的值
     * @return BloomResult: BloomResult.executeResult: 执行成功/失败
     * BloomResult.resultList: 每个值设置结果: 1: 布隆过滤器中无此值 并添加成功;
     * 0: 布隆过滤器中已有此值;
     */
    public BloomResult batchPut(String key, List valueList) {
        Assert.hasLength(key, "key can not be null or empty");
        Assert.notNull(valueList, "valueList can not be null");
        boolean result = false;
        List<Long> resultList = null;
        try {
            val script = new DefaultRedisScript<List>(BLOOM_FILTER_BATCH_PUT, List.class);
            resultList = redisTemplate.execute(script, Collections.singletonList(key), valueList.toArray());
            log.info("resultList: {}", resultList);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        BloomResult bloomResult = new BloomResult(result, resultList);
        log.info(" bloom put: key:{}  result: {} ", key, bloomResult);
        return bloomResult;
    }

    /**
     * 添加单个值到布隆过滤器中
     *
     * @param key   键值
     * @param value 需添加到布隆中的值
     * @return BloomResult: BloomResult.executeResult: 执行成功/失败
     * BloomResult.resultList: 每个值设置结果: 1: 布隆过滤器中无此值 并添加成功;
     * 0: 布隆过滤器中已有此值;
     */
    public BloomResult put(String key, String value) {
        Assert.hasLength(key, "key can not be null or empty");
        Assert.hasLength(value, "value can not be null or empty");
        boolean result = false;
        List<String> list = Collections.singletonList(value);
        return batchPut(key, list);
    }

    /**
     * 判断 多个值 是否可能存在
     *
     * @param key       键值
     * @param valueList 需要判断的值的list集合
     * @return BloomResult: BloomResult.executeResult: 执行成功/失败
     * BloomResult.resultList: 每个值判断结果: 1: 可能存在
     * 0: 一定不存在;
     */
    public BloomResult batchExists(String key, List valueList) {
        Assert.hasLength(key, "key can not be null or empty");
        Assert.notNull(valueList, "valueList can not be null");
        boolean result = false;
        List<Long> resultList = null;
        try {
            val script = new DefaultRedisScript<List>(BLOOM_FILTER__BATCH_EXITS, List.class);
            resultList = redisTemplate.execute(script, Collections.singletonList(key), valueList.toArray());
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        BloomResult bloomResult = new BloomResult(result, resultList);
        log.info(" batchExists: key:{}  result: {} ", key, bloomResult);
        return bloomResult;
    }

    /**
     * 判断 单个值 是否可能存在
     *
     * @param key   键值
     * @param value 需要判断的值
     * @return BloomResult: BloomResult.executeResult: 执行成功/失败
     * BloomResult.resultList: 每个值判断结果: 1: 可能存在
     * 0: 一定不存在;
     */
    public BloomResult exists(String key, String value) {
        Assert.hasLength(key, "key can not be null or empty");
        Assert.hasLength(value, "value can not be null or empty");
        boolean result = false;
        List<String> list = Collections.singletonList(value);
        return batchExists(key, list);
    }

    @Data
    public static class BloomResult {
        private Boolean flag;
        private List<Long> resultList;

        public BloomResult(Boolean flag, List<Long> resultList) {
            this.flag = flag;
            this.resultList = resultList;
        }

        public BloomResult() {
        }
    }

}
