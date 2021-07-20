package com.SpringBatch.JpaExample.repository;


import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.SpringBatch.JpaExample.model.Workers;

@Repository
public interface WorkersRepo extends JpaRepository<Workers, Long>{



}
