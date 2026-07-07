package com.ajay.productivity.repository;

import com.ajay.productivity.entity.UtilityJob;
import com.ajay.productivity.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<UtilityJob,Long> {
    List<UtilityJob> findJobByStatus(Status status);


}
