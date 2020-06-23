package com.cattsoft.eomsteam.iot.sync.config;

import com.cattsoft.eomsteam.iot.sync.service.SyncSheetService;
import com.cattsoft.eomsteam.iot.sync.service.impl.SyncSheetServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import javax.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

    @Bean
    public ServletRegistrationBean disServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new CXFServlet(), "/iot/services/*");
        //servletRegistrationBean.setName("webService");
        return servletRegistrationBean;
    }
    
    /**
     * 注册一个dispatcherServlet,解决增加ws之后http接口访问不了问题
 
    @Bean
    public ServletRegistrationBean restServlet(){
        //注解扫描上下文
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        //base package
        applicationContext.scan("com.cattsoft.eomsteam");
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
    }    */
    
    
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public SyncSheetService syncJsonService(){
        return new SyncSheetServiceImpl();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), syncJsonService());
        endpoint.publish("/SheetStateSync");
        //endpoint.getInInterceptors().add(new WsInterceptor()); //add webservice inteceptor
        return endpoint;
    }
}