package com.cattsoft.eomsteam.iot.sync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@ComponentScan(basePackages = {"com.cattsoft.eomsteam.iot.sync.*"})
public class SyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SyncApplication.class, args);
	}
	
	
	 /**
   * 注册一个dispatcherServlet,解决增加ws之后https接口访问不了问题

  @Bean
  public ServletRegistrationBean restServlet(){
    //注解扫描上下文
    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
    //base package
    applicationContext.scan("com.cattsoft.eomsteam.iot.sync");
    //通过构造函数指定dispatcherServlet的上下文
    DispatcherServlet rest_dispatcherServlet = new DispatcherServlet(applicationContext);

    //用ServletRegistrationBean包装servlet
    ServletRegistrationBean registrationBean = new ServletRegistrationBean(rest_dispatcherServlet);
    registrationBean.setLoadOnStartup(1);
    //指定urlmapping
    registrationBean.addUrlMappings("/*");
    //指定name，如果不指定默认为dispatcherServlet
    registrationBean.setName("rest");
    return registrationBean;
  }   */

}
