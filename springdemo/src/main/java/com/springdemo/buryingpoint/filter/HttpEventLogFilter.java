package com.springdemo.buryingpoint.filter;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springdemo.buryingpoint.model.EventLog;
import com.springdemo.buryingpoint.model.ResultVO;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * 埋点Filter
 *
 * @author Ice sun
 * @date 2020/11/2 19:27
 */
public class HttpEventLogFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger("event_log");
    /**
     * 预留日志总开关，通过配置开启或关闭
     */
    private boolean logEnabed = true;
    /**
     * 排除上传文件类请求
     */
    private static final String IGNORE_CONTENT_TYPE = "multipart/form-data";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }

        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }

        int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
        LocalDateTime start = LocalDateTime.now();
        try {
            filterChain.doFilter(request, response);
            status = response.getStatus();
        } catch (Exception e) {
            log.error("TraceLogFilter", e);
        } finally {
            if (logEnabed && !Objects.equals(IGNORE_CONTENT_TYPE, request.getContentType())) {
                ObjectMapper mapper = new ObjectMapper();

                LocalDateTime end = LocalDateTime.now();
                Duration duration = Duration.between(start, end);
                Long useTime = duration.toMillis();
                HttpSession session = request.getSession(false);

                EventLog eventLog = new EventLog();
                //请求参数
                StringBuffer requestParams = new StringBuffer();
                if ("POST".equals(request.getMethod())) {
                    String params = getRequestBody(request);
                    requestParams.append(params);
                } else {
                    String queryString = request.getQueryString();
                    requestParams.append(queryString);
                }
                //响应结果,如果没有统一返回格式，此处需要改写
                String result = getResponseBody(response);
                JSONObject jsonObject = JSON.parseObject(result);
                Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
                  while(iterator.hasNext()){
                      Map.Entry<String, Object> next = iterator.next();
                      log.info("iterator.next key::{} value::{}" ,next.getKey(),next.getValue());
                  }

                ResultVO resultVO = new ResultVO();//API统一响应封装类
                resultVO = mapper.readValue(result, ResultVO.class);
                String ip = getRealIP(request);
                String header = request.getHeader("User-Agent");
                //hutools工具类
                UserAgent ua = UserAgentUtil.parse(header);
                eventLog.setParams(requestParams.toString());
                eventLog.setUrl(request.getRequestURI());
                eventLog.setIp(ip);
                eventLog.setBrowser(ua == null ? "" : ua.getBrowser() == null ? "" : ua.getBrowser().toString());
                eventLog.setOperatingSystem(ua == null ? "" : ua.getPlatform() == null ? "" : ua.getPlatform().toString());
                eventLog.setStatus(status);
                eventLog.setUseTime(useTime);
                eventLog.setCode(resultVO.getCode());
                eventLog.setData(resultVO.getDate());
                eventLog.setMsg(resultVO.getMsg());
                eventLog.setCreateTime(start);

                log.info(mapper.writeValueAsString(eventLog));
            }
        }

    }

    /**
     * 获取请求的真实IP
     *
     * @param request
     * @return
     */
    public static String getRealIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    private String getRequestBody(HttpServletRequest request) {
        String requestBody = "";
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            try {
                requestBody = IOUtils.toString(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());
            } catch (IOException e) {
                // NOOP
            }
        }
        return requestBody;
    }

    private String getResponseBody(HttpServletResponse response) {
        String responseBody = "";
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        if (wrapper != null) {
            try {
                responseBody = IOUtils.toString(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());
            } catch (IOException e) {
                // NOOP
            }
        }
        return responseBody;
    }

}