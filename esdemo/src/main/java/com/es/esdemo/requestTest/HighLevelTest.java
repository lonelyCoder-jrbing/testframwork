package com.es.esdemo.requestTest;

import com.alibaba.fastjson.JSON;
import com.es.esdemo.EsdemoApplication;
import com.es.esdemo.requestTest.pojo.Person;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.*;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsdemoApplication.class)// 就是你springboot的启动类
public class HighLevelTest {


    //    public RestHighLevelClient restHighLevelClient() {
//        return new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.1.102", 9200, "http")));
//    }
    @Autowired
    private RestHighLevelClient client;

    @Test
    public void man() throws IOException {
//        HighLevelTest highLevelTest = new HighLevelTest();
        addTest();
        searchTest();
    }

    @Test
    public void searchTest() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
//        searchSourceBuilder.query(QueryBuilders.matchPhrasePrefixQuery("userName", "jurongbing01"));
        SearchRequest request = new SearchRequest("esdb");
        request.source(searchSourceBuilder);
//        RestHighLevelClient restHighLevelClient = new HighLevelTest().restHighLevelClient();
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        SearchHit[] hits = response.getHits().getHits();
        List<Person> res = new ArrayList<>(hits.length);
        for (SearchHit hit : hits) {
            Person person = JSON.parseObject(hit.getSourceAsString(), Person.class);
            res.add(person);
        }
        System.out.println("res:  " + res);
    }

    @Test
    public void updateTest() throws IOException {
//        XContentBuilder xContentBuilder = new XContentBuilder();
        Map<String, Object> updateMap = new HashMap<String, Object>();
        updateMap.put("userName", "jrbing");
        updateMap.put("age", 23);
        UpdateRequest updateRequest = new UpdateRequest("esdb", "1").doc(updateMap);
//        RestHighLevelClient restHighLevelClient = new HighLevelTest().restHighLevelClient();
        client.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Test
    public void addTest() throws IOException {

        String id = UUID.randomUUID().toString();
        Map<String, Object> addMap = new HashMap<>();
        addMap.put("userName", "boyingyue");
        addMap.put("age", 34);
        addMap.put("gender", "w");
        IndexRequest source = new IndexRequest("esdb", "table", id).source(addMap);
//        RestHighLevelClient restHighLevelClient = new HighLevelTest().restHighLevelClient();
        client.index(source, RequestOptions.DEFAULT);


    }


}
