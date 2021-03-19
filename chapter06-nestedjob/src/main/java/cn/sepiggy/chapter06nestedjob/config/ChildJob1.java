package cn.sepiggy.chapter06nestedjob.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChildJob1 {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /**
     * 创建子Job1
     */
    @Bean
    public Job childJobOne() {
        return jobBuilderFactory.get("childJob1")
                .start(childJob1Step1())
                .build();
    }

    @Bean
    public Step childJob1Step1() {
        return stepBuilderFactory.get("childJob1Step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("childJob1Step1");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
