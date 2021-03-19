package cn.sepiggy.chapter05decider.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class DeciderDemoConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    private int count;

    /**
     * 创建任务
     */
    @Bean
    public Job deciderDemoJob() {
        return jobBuilderFactory.get("deciderDemoJob")
                .start(deciderDemoStep1())
                .next(myDecider())
                .from(myDecider()).on("even").to(deciderDemoStep2())
                .from(myDecider()).on("odd").to(deciderDemoStep3())
                .from(deciderDemoStep3()).on("*").to(myDecider()) // "*"表示无论返回什么都匹配
                .end().build();
    }

    /**
     * 创建决策器
     */
    @Bean
    public JobExecutionDecider myDecider() {
        return (jobExecution, stepExecution) -> {
            count++;
            if (count % 2 == 0) {
                return new FlowExecutionStatus("even");
            } else {
                return new FlowExecutionStatus("odd");
            }
        };
    }

    /**
     * 创建3个Step
     */
    @Bean
    public Step deciderDemoStep1() {
        return stepBuilderFactory.get("deciderDemoStep1")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("deciderDemoStep1");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step deciderDemoStep2() {
        return stepBuilderFactory.get("deciderDemoStep2")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("even");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step deciderDemoStep3() {
        return stepBuilderFactory.get("deciderDemoStep3")
                .tasklet((stepContribution, chunkContext) -> {
                    System.out.println("odd");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
