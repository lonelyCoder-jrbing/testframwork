package com.springdemo.ioctest.beanpostprocessorTest.configerations;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public  class MyListener implements ServletContextListener {

    private ServletContext context = null;

    /* 这个方法在Web应用服务被移除，没有能力再接受请求的时候被调用。
     */
    @Override
    public void contextDestroyed(ServletContextEvent event){
        //Output a simple message to the server's console
        System.out.println("The Simple Web App. Has Been Removed");
        this.context = null;

    }

    // 这个方法在Web应用服务做好接受请求的时候被调用。
    @Override
    public void contextInitialized(ServletContextEvent event){
        this.context = event.getServletContext();
//        ContextPathUtil.setContext(this.context);
        context.setAttribute("basePath","/public");

         System.out.println("The Simple Web App. Is Ready");
//        org.apache.ibatis.logging.LogFactory.useSlf4jLogging();

    }
}
