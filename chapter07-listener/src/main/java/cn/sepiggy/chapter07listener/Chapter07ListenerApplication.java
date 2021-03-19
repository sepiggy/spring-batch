package cn.sepiggy.chapter07listener;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class Chapter07ListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter07ListenerApplication.class, args);
    }

}
