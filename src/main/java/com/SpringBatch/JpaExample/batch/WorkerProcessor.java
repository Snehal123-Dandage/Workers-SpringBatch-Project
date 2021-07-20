package com.SpringBatch.JpaExample.batch;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.SpringBatch.JpaExample.model.Workers;

@Component
public class WorkerProcessor implements ItemProcessor<Workers, Workers>{

	@Override
	public Workers process(Workers item) throws Exception {
		System.out.println("This is processor "+ item);
		return item;
	}

}
