package cn.sepiggy.chapter06nestedjob.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.JobStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 案例：创建两个Job, 作为子Job, 再创建一个Job作为父Job.
 */
@Configuration
public class NestedJobDemoConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private Job childJobOne;

    @Autowired
    private Job childJobTwo;

    @Autowired
    private JobLauncher jobLauncher;

    @Bean
    public Job parentJod(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return jobBuilderFactory.get("parentJod")
                .start(childJob1(jobRepository, transactionManager))
                .next(childJob2(jobRepository, transactionManager))
                .build();
    }

    // 返回的是Job类型的Step, 是特殊的Step
    private Step childJob1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobStepBuilder(new StepBuilder("childJob1"))
                .job(childJobOne) // 指定job对象, 将其转化为step对象
                .launcher(jobLauncher) // 使用启动父Job的启动对象
                .repository(jobRepository) // 指明持久化对象
                .transactionManager(transactionManager) // 事务管理器
                .build();
    }

    private Step childJob2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobStepBuilder(new StepBuilder("childJob2"))
                .job(childJobTwo) // 指定job对象, 将其转化为step对象
                .launcher(jobLauncher) // 使用启动父Job的启动对象
                .repository(jobRepository) // 指明持久化对象
                .transactionManager(transactionManager) // 事务管理器
                .build();
    }

}
