package cn.sepiggy.chapter07listener.listener;

import lombok.val;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * 通过实现接口来创建监听器
 */
public class MyJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        val jobName = jobExecution.getJobInstance().getJobName();
        System.out.println(jobName + "before...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        val jobName = jobExecution.getJobInstance().getJobName();
        System.out.println(jobName + "after...");
    }
}
