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
public class ChildJob2 {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /**
     * 创建子Job2
     */
    @Bean
    public Job childJobTwo() {
        return jobBuilderFactory.get("childJob2")
                .start(childJob2Step1())
                .next(childJob2Step2())
                .build();
    }

    @Bean
    public Step childJob2Step1() {
        return stepBuilderFactory.get("childJob2Step1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("childJob2Step1");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step childJob2Step2() {
        return stepBuilderFactory.get("childJob2Step2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("childJob2Step2");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
