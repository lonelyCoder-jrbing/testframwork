package com.springdemo.buryingpoint.wrapper;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class MyResponseWrapper extends HttpServletResponseWrapper {
    private MyWriter myWriter;
    
    
    public MyResponseWrapper(HttpServletResponse response) {
        super(response);
    }
    
    @Override
    public PrintWriter getWriter() throws IOException {
        myWriter = new MyWriter(super.getWriter());
        return myWriter;
    }
    
    

    public MyWriter getMyWriter() {
        return myWriter;
    }

    
    
}
