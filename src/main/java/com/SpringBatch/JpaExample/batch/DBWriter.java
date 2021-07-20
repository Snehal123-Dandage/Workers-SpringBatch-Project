package com.SpringBatch.JpaExample.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SpringBatch.JpaExample.model.Workers;
import com.SpringBatch.JpaExample.repository.WorkersRepo;

@Component
public class DBWriter implements ItemWriter<Workers>{
	
	@Autowired
	private WorkersRepo workersRepo;
	
	@Override
	public void write(List<? extends Workers> items) throws Exception {
		System.out.println("Data Saved for workers" + items);
		workersRepo.saveAll(items);
	}

}
