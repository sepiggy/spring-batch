package cn.sepiggy.chapter06nestedjob;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class Chapter06NestedJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chapter06NestedJobApplication.class, args);
    }
}
