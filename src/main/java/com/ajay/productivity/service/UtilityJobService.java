package com.ajay.productivity.service;

import com.ajay.productivity.dto.CreateJobRequest;
import com.ajay.productivity.dto.JobResponse;
import com.ajay.productivity.entity.User;
import com.ajay.productivity.entity.UtilityJob;
import com.ajay.productivity.exception.JobNotFoundException;
import com.ajay.productivity.exception.UserNotFoundException;
import com.ajay.productivity.util.EntityToDTOConverter;
import com.ajay.productivity.model.Status;
import com.ajay.productivity.repository.JobRepository;
import com.ajay.productivity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilityJobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final EntityToDTOConverter converter;


    public JobResponse createJob(Long userId, CreateJobRequest jobRequest){
        User user=userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));
        UtilityJob job=new UtilityJob();
        job.setFilename(jobRequest.filename());
        job.setStatus(Status.PENDING);
        job.setUser(user);
        jobRepository.save(job);
        return converter.jobToJobResponse(job);
    }

    public JobResponse findJobById(Long id){
        UtilityJob job= jobRepository.findById(id).orElseThrow(()->new JobNotFoundException(id));
        return converter.jobToJobResponse(job);
    }

    @Transactional
    public JobResponse updateJobStatus(Long id,Status status){
        var job=jobRepository.findById(id).orElseThrow(()->new JobNotFoundException(id));
        job.setStatus(status);
//        jobRepository.save(job);
        return converter.jobToJobResponse(job);
    }

    public void deleteJob(Long id){
        jobRepository.findById(id).orElseThrow(()->new JobNotFoundException(id));
        jobRepository.deleteById(id);
    }

    public List<JobResponse> findJobByStatus(Status status){
        List<UtilityJob> jobsWithStatus=jobRepository.findJobByStatus(status);
        return jobsWithStatus.stream()
                .map(converter::jobToJobResponse)
                .toList();
    }

    public Page<JobResponse> findAllJobs(Pageable pageable){
        return jobRepository.findAll(pageable)
                .map(converter::jobToJobResponse);
    }
}
