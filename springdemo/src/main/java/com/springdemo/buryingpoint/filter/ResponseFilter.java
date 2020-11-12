package com.springdemo.buryingpoint.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


/**
 * 返回值输出过滤器，这里用来加密返回值
 *
 * @author kokJuis
 * @Title: ResponseFilter
 * @Description:
 * @date 上午9:52:42
 */
public class ResponseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        ResponseWrapper wrapperResponse = new ResponseWrapper((HttpServletResponse) response);//转换成代理类
        // 这里只拦截返回，直接让请求过去，如果在请求前有处理，可以在这里处理
        filterChain.doFilter(request, wrapperResponse);
        byte[] content = wrapperResponse.getContent();//获取返回值
        //判断是否有值
        if (content.length > 0) {

            String str = new String(content, "UTF-8");
            System.out.println("返回值:" + str);
            String ciphertext = null;

            try {
                //......根据需要处理返回值
            } catch (Exception e) {
                e.printStackTrace();
            }
            //把返回值输出到客户端
            ServletOutputStream out = response.getOutputStream();
            out.write(content);
            out.flush();
        }

    }

    @Override
    public void init(FilterConfig arg0)
            throws ServletException {

    }

    @Override
    public void destroy() {

    }

}