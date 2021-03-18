package cn.sepiggy.chapter03flow.config;

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

@Configuration
@EnableBatchProcessing
public class FlowDemo {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /**
     * 根据Flow对象创建Job对象
     */
    @Bean
    public Job flowDemoJob() {
        return jobBuilderFactory.get("flowDemoJob").start(flowDemoFlow()).next(flowDemoStep3()).end().build();
    }

    /**
     * 使用FlowBuilder创建Flow对象
     * 创建Flow对象, 指明Flow对象包含哪些Step(Step1, Step2)
     */
    @Bean
    public Flow flowDemoFlow() {
        return new FlowBuilder<Flow>("flowDemoFlow").start(flowDemoStep1()).next(flowDemoStep2()).build();
    }


    /**
     * 创建Step三个对象
     */
    @Bean
    public Step flowDemoStep1() {
        return stepBuilderFactory.get("flowDemoStep1").tasklet((contribution, chunkContext) -> {
            System.out.println("flowDemoStep1");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step flowDemoStep2() {
        return stepBuilderFactory.get("flowDemoStep2").tasklet((contribution, chunkContext) -> {
            System.out.println("flowDemoStep2");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Step flowDemoStep3() {
        return stepBuilderFactory.get("flowDemoStep3").tasklet((contribution, chunkContext) -> {
            System.out.println("flowDemoStep3");
            return RepeatStatus.FINISHED;
        }).build();
    }
}
