package com.alan.props;

import com.alan.props.constants.Configs;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

  @Value("${app.sample.text}")
  private String text;

  @Autowired
  private Configs config;

  @PostConstruct
  private void init() {
    System.out.println("Text = "+text);
    System.out.println("config.toString() "+config.toString());
  }

  public static void main(String[] args) {
    SpringApplication.run(App.class);
  }

}
