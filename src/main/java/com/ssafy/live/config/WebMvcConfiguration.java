package com.ssafy.live.config;
import java.util.Arrays;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;


@Configuration
@EnableAspectJAutoProxy    // auto-proxy설정
@MapperScan(basePackages = { "com.ssafy.**.mapper" })
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final List<String> patterns = Arrays.asList("/board/*", "/admin", "/user/list");



    private final String uploadFilePath;

    public WebMvcConfiguration(@Value("${file.path.upload-files}") String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")    // 누구든 접근 가능
//            .allowedOrigins("http://localhost:8080", "http://localhost:8081")  // 특정 uri만 접근 가능
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(),
                        HttpMethod.PATCH.name())
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")
                .maxAge(1800);
    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/file/**").addResourceLocations("file:///" + uploadFilePath + "/")
                .setCachePeriod(3600).resourceChain(true).addResolver(new PathResourceResolver());
    }

}