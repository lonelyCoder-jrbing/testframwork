package com.springdemo.scheduletask.quartz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component("URL")
public class URLCallbackStrategy implements CallbackStrategy {
    private Logger logger = LoggerFactory.getLogger(URLCallbackStrategy.class);
    @Autowired(required = false)
    private RestTemplate restTemplate = new RestTemplate();

    public URLCallbackStrategy() {
    }

    @Override
    public boolean callback(JobModel jobModel) {
        this.logger.debug("Start to invoke URLCallback...");
        if (this.logger.isTraceEnabled()) {
            this.logger.trace("jobModel = [{}]", JSON.toJSONStringWithDateFormat(jobModel, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[0]));
        }

        String callbackUrl = jobModel.getCallbackUrl();
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(jobModel.getConnectTimeout());
        requestFactory.setReadTimeout(jobModel.getReadTimeout());
        this.restTemplate.setRequestFactory(requestFactory);

        try {
            ResponseEntity<String> result = this.restTemplate.getForEntity(callbackUrl, String.class, new Object[0]);
            int code = result.getStatusCodeValue();
            if (HttpStatus.OK.value() == code) {
                this.logger.info("Callback for url - {} successfully.", callbackUrl);
                return true;
            }
        } catch (RestClientException var6) {
            this.logger.error("Callback for url - [{}] failed due to error.", callbackUrl, var6);
            return false;
        }

        this.logger.error("Callback for url - {} failed.", callbackUrl);
        return false;
    }
}