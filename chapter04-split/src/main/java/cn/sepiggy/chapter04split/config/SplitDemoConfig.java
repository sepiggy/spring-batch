package cn.sepiggy.chapter04split.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class SplitDemoConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /**
     * 创建的任务
     */
    @Bean
    public Job splitDemoJob() {
        return jobBuilderFactory.get("splitDemoJob1")
                .start(splitDemoFlow1())
                .split(new SimpleAsyncTaskExecutor()).add(splitDemoFlow2())
                .end()
                .build();
    }

    /**
     * 创建2个Flow
     */
    @Bean
    public Flow splitDemoFlow1() {
        return new FlowBuilder<Flow>("splitDemoFlow1")
                .start(splitDemoStep1())
                .build();
    }

    @Bean
    public Flow splitDemoFlow2() {
        return new FlowBuilder<Flow>("splitDemoFlow2")
                .start(splitDemoStep2())
                .next(splitDemoStep3())
                .build();
    }

    /**
     * 创建3个Step
     */
    @Bean
    public Step splitDemoStep1() {
        return stepBuilderFactory.get("splitDemoStep1")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("splitDemoStep1");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step splitDemoStep2() {
        return stepBuilderFactory.get("splitDemoStep2")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("splitDemoStep2");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step splitDemoStep3() {
        return stepBuilderFactory.get("splitDemoStep3")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("splitDemoStep3");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
