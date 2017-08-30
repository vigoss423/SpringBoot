package com.blueocean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RestController;

import com.blueocean.common.filter.HTTPBasicAuthorizeAttribute;
import com.blueocean.common.filter.HTTPBearerAuthorizeAttribute;

/**
 * 
 * @className com.Application.java
 * @author wangxinyu
 * @time 2017年3月1日 下午6:03:20
 * @todo @SpringBootApplication申明让spring boot自动给程序进行必要的配置，等
 *       价于以默认属性使用@Configuration，@EnableAutoConfiguration和@ComponentScan
 * @RestController返回json字符串的数据，直接可以编写RESTFul的接口； 启动应用就可以访问：http://127.0.0.1:8080/druid2/index.html输入账号和密码：admin2/123456
 *                                               就可 启动方式 spring-boot:run
 * @Configuration 和BEAN使用代替XML注解
 * @EnableAutoConfiguration 并对其及下属的包进行扫描
 * @ComponentScan 扫描类 @ServletComponentScan, 设定过滤器拦截器扫描
 */
@RestController
@SpringBootApplication
@EnableConfigurationProperties // 注入配置文件属性扫描
@ImportResource(locations = { "classpath:config/default-conf.xml" })
public class Application {

	@Bean
	public FilterRegistrationBean basicFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		HTTPBasicAuthorizeAttribute httpBasicFilter = new HTTPBasicAuthorizeAttribute();
		registrationBean.setFilter(httpBasicFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/user/getuser");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean jwtFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		HTTPBearerAuthorizeAttribute httpBearerFilter = new HTTPBearerAuthorizeAttribute();
		registrationBean.setFilter(httpBearerFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/hello/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
		System.out.println(123);
	}

}
