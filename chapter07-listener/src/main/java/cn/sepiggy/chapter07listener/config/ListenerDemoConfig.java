package cn.sepiggy.chapter07listener.config;

import cn.sepiggy.chapter07listener.listener.MyChunkListener;
import cn.sepiggy.chapter07listener.listener.MyJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class ListenerDemoConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    /**
     * 定义Job
     *
     * 为Job添加监听器MyJobListener
     */
    @Bean
    public Job listenerDemoJob() {
        return jobBuilderFactory.get("listenerDemoJob")
                .start(step1())
                .listener(new MyJobListener())
                .build();
    }

    /**
     * 定义Step
     *
     * 为Step添加监听器MyChunkListener
     */
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(2) // read, process, write
                .faultTolerant() // 容错
                .listener(new MyChunkListener())
                .reader(read())
                .writer(write())
                .build();
    }

    @Bean
    public ItemReader<String> read() {
        return new ListItemReader<>(Arrays.asList("java", "spring", "mybatis"));
    }

    @Bean
    public ItemWriter<String> write() {
        return items -> items.forEach(System.out::println);
    }

}
