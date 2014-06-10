/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package im.dadoo.blog.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import im.dadoo.blog.interceptor.SessionInterceptor;
import im.dadoo.logger.client.LoggerClient;
import im.dadoo.logger.client.impl.DefaultLoggerClient;
import javax.sql.DataSource;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author codekitten
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan("im.dadoo.blog")
public class Context extends WebMvcConfigurerAdapter {
  
  @Bean
  public LoggerClient loggerClient() {
    return new DefaultLoggerClient("http://log.dadoo.im/logger");
  }
  
  @Bean
  public PropertiesConfiguration config() throws ConfigurationException {
    return new PropertiesConfiguration("blog.properties");
  }
  
  @Bean(initMethod = "init", destroyMethod = "close")
  public DataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl("jdbc:mysql://202.114.18.242:33066/dadooblog?characterEncoding=utf8&autoReconnect=true");
    dataSource.setUsername("root");
    dataSource.setPassword("dadoo2012dadoo");
    return dataSource;
  }
  
  @Bean
  public NamedParameterJdbcTemplate jdbcTemplate() {
    NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(this.dataSource());
    return jdbcTemplate;
  }
  
  @Bean
  public SessionInterceptor sessionInterceptor() {
    return new SessionInterceptor();
  }
  
  @Bean
  public InternalResourceViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("/WEB-INF/views/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }
  
  @Bean
  public CommonsMultipartResolver multipartResolver() {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize(1024*1024*2);
    return multipartResolver;
  }
  
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
    registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
  }
  
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(sessionInterceptor()).addPathPatterns("/**");
  }
}
