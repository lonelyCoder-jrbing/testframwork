package com.testframwork.intercepter.impl;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserLoginInterceptorBySpring extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())) {
            //将请求保存下来
        }
        System.out.println("preHandle...");
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        System.out.println("requestUri" + requestUri);
        System.out.println("contextPath" + contextPath);
        System.out.println("url" + url);
        String username = (String) request.getSession().getAttribute("username");
        if (null == username) {
            // 跳转到登录页面
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void postHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
