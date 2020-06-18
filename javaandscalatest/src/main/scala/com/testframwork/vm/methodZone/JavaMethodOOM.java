package com.testframwork.vm.methodZone;

import com.google.common.collect.Lists;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JavaMethodOOM {

    public static void main(String[] args) {
        while (true) {
            final JavaMethodOOM oomObject = new JavaMethodOOM();
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(oomObject.getClass());
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//                    return methodProxy.invokeSuper(objects, args);
                    return method.invoke(oomObject, objects);
                }
            });
            JavaMethodOOM o = (JavaMethodOOM) enhancer.create();
//            o.oom();
        }

    }

    int i = 0;

    public void oom() {
        System.out.println("running...");
        List<Object> objects = Lists.newArrayList();
        objects.add(i++);
//            i=i++;
    }


    static class OOMObject {
        int i = 0;

        public void oom() {
            System.out.println("running...");
            List<Object> objects = Lists.newArrayList();
            objects.add(i++);
//            i=i++;
        }

    }

}
