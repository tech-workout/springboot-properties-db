package com.alan.props;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

  @Value("${app.sample.text}")
  private String text;

  @PostConstruct
  private void init() {
    System.out.println("Text = "+text);
  }

  public static void main(String[] args) {
    SpringApplication.run(App.class);
  }

}
