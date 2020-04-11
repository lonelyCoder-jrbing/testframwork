package com.es.esdemo.controller;

import com.alibaba.fastjson.JSON;
import com.es.esdemo.requestTest.pojo.Person;
import com.es.esdemo.utils.EsScrollUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/es")
public class GetAgeByNameController {


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


}
