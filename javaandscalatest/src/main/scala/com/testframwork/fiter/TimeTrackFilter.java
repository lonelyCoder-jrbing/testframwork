package com.testframwork.fiter;


import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

public class TimeTrackFilter implements Filter {

    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Date startTime, endTime;
        double totalTime;
        startTime = new Date();
        filterChain.doFilter(request, response);
        endTime = new Date();
        totalTime = endTime.getTime() - startTime.getTime();
        totalTime = totalTime / 1000; //Convert from milliseconds to seconds
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);
        writer.println();
        writer.println("===============");
        writer.println("Total elapsed time is: " + totalTime + " seconds.");
        writer.println("===============");
        // Log the resulting string
        writer.flush();
        filterConfig.getServletContext().
                log(sw.getBuffer().toString());
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
