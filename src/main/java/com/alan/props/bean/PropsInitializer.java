package com.alan.props.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class PropsInitializer implements BeanPostProcessor, InitializingBean, EnvironmentAware {

  private JdbcTemplate jdbcTemplate;
  private ConfigurableEnvironment environment;

  private final String propertySourceName = "propertiesInsideDatabase";

  public PropsInitializer(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("Coming here - 1");
    if (null != environment) {
      System.out.println("Coming here - 2");
      Map<String, Object> systemConfigMap = new HashMap<>();
      String sql = "SELECT key, value from props";
      List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
      for (Map<String, Object> map : maps) {
        String key = String.valueOf(map.get("key"));
        Object value = map.get("value");
        systemConfigMap.put(key, value);
        System.out.println(String.format("key=%s, value=%s ", key, value));
      }
      environment.getPropertySources().addFirst(new MapPropertySource(propertySourceName, systemConfigMap));
    }
  }

  @Override
  public void setEnvironment(Environment environment) {
    if(environment instanceof ConfigurableEnvironment) {
      this.environment = (ConfigurableEnvironment) environment;
    }
  }
}
