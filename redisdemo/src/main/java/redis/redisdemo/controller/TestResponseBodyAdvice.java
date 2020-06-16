package redis.redisdemo.controller;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * create by sumerian on 2020/6/16
 * <p>
 * desc:  对controller方法层进行拦截
 **/
@ControllerAdvice
public class TestResponseBodyAdvice  implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    /****
     *
     * @param o  要拦截controller方法的返回值，可以对其进行修改并且返回，对关键字进行脱敏
     * @param methodParameter 方法的参数值
     * @param selectedContentType 返回值类型，可以进行修改
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        System.out.println("TestResponseBodyAdvice==>beforeBodyWrite:"  + ",selectedContentType:   "+selectedContentType +"methodParameter:   "+methodParameter+"  request:"+request+"   response:"+response);
        //对 o 进行脱敏，并且返回。
        return o;
    }
}
