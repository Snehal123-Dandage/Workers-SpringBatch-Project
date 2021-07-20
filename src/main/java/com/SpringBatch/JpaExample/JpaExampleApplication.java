package com.SpringBatch.JpaExample;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
public class JpaExampleApplication {
	
	@Autowired
    JobLauncher jobLauncher;
	
    @Autowired
    Job job;
    
	public static void main(String[] args) {
		SpringApplication.run(JpaExampleApplication.class, args);
	}
	@Scheduled(cron = "0 * * ? * *")
	public void run1() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException, NoSuchJobExecutionException, NoSuchJobException{
	    Map<String, JobParameter> confMap = new HashMap<>();
	    confMap.put("time", new JobParameter(System.currentTimeMillis()));
	    JobParameters jobParameters = new JobParameters(confMap);
	        jobLauncher.run(job, jobParameters);    
	}
	
	
}
