package com.mrtimi.mower.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;

@SpringBootApplication
@EnableBatchProcessing(dataSourceRef = "batchDataSource")
public class MowerBatchApplication {

    public static void main(String[] args) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        ApplicationContext context = SpringApplication.run(MowerBatchApplication.class, args);
        Job job = context.getBean(Job.class);
        JobLauncher jobLauncher = context.getBean(JobLauncher.class);
        JobParameters parameters = new JobParametersBuilder()
                .addLong("run.id", new Date().getTime())
                .toJobParameters();
        jobLauncher.run(job, parameters);
    }

}
