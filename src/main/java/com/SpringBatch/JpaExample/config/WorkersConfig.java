package com.SpringBatch.JpaExample.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.SpringBatch.JpaExample.batch.DBWriter;
import com.SpringBatch.JpaExample.batch.WorkerProcessor;
import com.SpringBatch.JpaExample.model.Workers;

@Configuration
@EnableBatchProcessing
public class WorkersConfig {

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, 
			StepBuilderFactory stepBuilderFactory, 
			ItemReader<Workers> itemReader,
			ItemProcessor<Workers, Workers> itemProcessor,
			ItemWriter<Workers> itemWriter
			) {
		
		Step step = stepBuilderFactory.get("Csv-To-Database-Step")
				.<Workers, Workers>chunk(500)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
		
		return jobBuilderFactory.get("Csv-To-Database-job")
		.incrementer(new RunIdIncrementer())
		.start(step)
		.build();
	}
	
	@Bean
	public FlatFileItemReader<Workers> itemReader(){
		FlatFileItemReader<Workers> itemReader = new FlatFileItemReader<>();
		itemReader.setResource(new ClassPathResource("Worker.csv"));
		itemReader.setName("CSV-Reader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		return itemReader;

	}

	@Bean
	public LineMapper<Workers> lineMapper() {
		DefaultLineMapper<Workers> defaultLineMapper = new DefaultLineMapper<>();

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[] {"id", "name", "address"});

		BeanWrapperFieldSetMapper<Workers> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		beanWrapperFieldSetMapper.setTargetType(Workers.class);

		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

		return defaultLineMapper;
	}
	
	@Bean
	public ItemProcessor<Workers, Workers> itemProcessor() {
		return new WorkerProcessor();
	}


	@Bean
	public ItemWriter<Workers> itemWriter(){
		return new DBWriter();
	}
}
