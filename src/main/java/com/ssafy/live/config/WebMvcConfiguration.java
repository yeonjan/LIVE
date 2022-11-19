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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.live.interceptor.JwtInterceptor;
import com.ssafy.live.interceptor.LoginInterceptor;

@Configuration
@EnableAspectJAutoProxy // auto-proxy설정
@MapperScan(basePackages = { "com.ssafy.**.mapper" })
public class WebMvcConfiguration implements WebMvcConfigurer {

	private final List<String> patterns = Arrays.asList("/board/*", "/admin", "/user/list");

	private final String uploadFilePath;

	public WebMvcConfiguration(@Value("${file.path.upload-files}") String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}

	// cors 설정
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
				// API에 접근 가능한 URL
				.addMapping("/**").allowedOrigins("*")
				// 허용할 메소드 설정 
				.allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
						HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(),
						HttpMethod.PATCH.name())
				.allowedHeaders("*")
				// preflight 요청 결과의 캐시를 저장할 수 있는 시간
				.maxAge(1800);
		
	}

	// 인터셉터 주소 설정
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/boards");
//	}

//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/upload/file/**").addResourceLocations("file:///" + uploadFilePath + "/")
//                .setCachePeriod(3600).resourceChain(true).addResolver(new PathResourceResolver());
//    }

}