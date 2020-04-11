package com.es.esdemo.requestTest;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

//@Configuration
public class ElasticsearchBean {


    /**
     * @return 封装 RestClient
     */
//    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient(){
        return new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.1.102", 9200, "http")));
    }

    public static void main(String[] args) throws Exception {
        //CreateIndexRequest 实例， 需要注意包的版本 我这里用的7.2的版本 org.elasticsearch.client.indices;
        CreateIndexRequest request = new CreateIndexRequest("hard");
        //封装属性 类似于json格式
        Map<String, Object> jsonMap = new HashMap<>();
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> content = new HashMap<>();
        content.put("type", "integer");
        Map<String, Object>account = new HashMap<>();
        content .put("type", "text");
        content .put("analyzer", "ik_max_word");
        properties.put("id", content);
        properties.put("account", account);
        jsonMap.put("properties", properties);
        //设置分片
        request.settings(Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2)
        );
//        request.mapping(jsonMap);
//        我使用的同步的方式 异步请参考官方文档
//        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
//        if (!createIndexResponse.isAcknowledged()){
//            throw new Exception("创建索引失败");
//        }

    }


}
