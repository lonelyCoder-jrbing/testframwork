package com.es.esdemo.controller;

import com.alibaba.fastjson.JSON;
import com.es.esdemo.receiver.FirstConsumer;
import com.es.esdemo.requestTest.pojo.Person;
import com.es.esdemo.utils.EsScrollUtil;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/es")
public class GetAgeByNameController {
    @Autowired
    private FirstConsumer firstConsumer;
    @Autowired
    RestHighLevelClient clients;

    @GetMapping("/name")
    public Integer getAgeByName(String name) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        SearchRequest request = new SearchRequest("esdb");
        searchSourceBuilder.query(QueryBuilders.matchPhrasePrefixQuery("userName", name));
        request.source(searchSourceBuilder);
        //使用游标查询
        List<SearchHit> hits = EsScrollUtil.scrollSearchAll(clients, 300L, request);
//        SearchResponse response = clients.search(request, RequestOptions.DEFAULT);
//        SearchHit[] hits = response.getHits().getHits();
        List<Person> res = new ArrayList<>(hits.size());
        for (SearchHit hit : hits) {
            Person person = JSON.parseObject(hit.getSourceAsString(), Person.class);
            res.add(person);
        }
        Person person = null;
        if (Objects.nonNull(res)) {
            person = res.get(0);
        }
        return person.getAge();

    }

    @PostMapping("/insertuser")
    @ResponseBody
    public void insertIntoEs(@RequestBody Person person) throws IOException {
        System.out.println("person:   " + person);
        String uuid = UUID.randomUUID().toString();
        Map<String, Object> personMap = new HashMap<>();
        personMap.put("userName", person.getUserName());
        personMap.put("age", person.getAge());
        personMap.put("gender", person.getGender());
        IndexRequest source = new IndexRequest("esdb", "table", uuid).source(personMap);
        clients.index(source, RequestOptions.DEFAULT);
    }


}
