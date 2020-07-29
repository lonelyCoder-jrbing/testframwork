package redis.redisdemo.controller.interfacecheck.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import redis.redisdemo.controller.interfacecheck.ApiIdempotentInterceptor;

/**
 * create by sumerian on 2020/7/29
 * <p>
 * desc:
 **/
@Configuration
@Slf4j
public class CheckConfig  extends WebMvcConfigurerAdapter {
    /**
     * 跨域
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        log.info("corsFilter.............");
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
         
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("registry............{}",registry);
        // 接口幂等性拦截器
        registry.addInterceptor(apiIdempotentInterceptor());
        super.addInterceptors(registry);
    }

    @Bean
    public ApiIdempotentInterceptor apiIdempotentInterceptor() {
        return new ApiIdempotentInterceptor();
    }




}
