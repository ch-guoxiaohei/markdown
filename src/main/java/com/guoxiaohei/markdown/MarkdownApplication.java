package com.guoxiaohei.markdown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MarkdownApplication {

  public static void main(String[] args) {
    SpringApplication.run(MarkdownApplication.class, args);
  }

}
