package com.es.esdemo.requestTest;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.RequestLine;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.threadpool.Scheduler;

import java.io.IOException;
public class Demo01 {


    public static void main(String[] args) throws IOException {
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200, "http"),
                new HttpHost("localhost", 9201, "http")).build();
        Request request = new Request(
                "GET",
                "/sdb/user/1");
        request.addParameter("pretty", "true");
        request.setEntity(new NStringEntity(
                "{\"json\":\"text\"}",
                ContentType.APPLICATION_JSON));
        Response response = restClient.performRequest(request);
        //获取结果数据
        RequestLine requestLine = response.getRequestLine();
        HttpHost host = response.getHost();
//        Returns the status line of the current response
        int statusCode = response.getStatusLine().getStatusCode();
//        Returns all the response headers
        Header[] headers = response.getHeaders();
        String responsebody = EntityUtils.toString(response.getEntity());
//        responsebody
        System.out.println("response:  "+responsebody);



    }




}
