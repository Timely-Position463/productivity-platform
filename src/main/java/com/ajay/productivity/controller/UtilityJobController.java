package com.ajay.productivity.controller;

import com.ajay.productivity.dto.CreateJobRequest;
import com.ajay.productivity.dto.JobResponse;
import com.ajay.productivity.dto.JobStatusRequest;
import com.ajay.productivity.model.Status;
import com.ajay.productivity.service.UtilityJobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Utility Jobs",
        description = "Endpoints for tracking document processing jobs."
)
@RestController
@RequestMapping("api/v1/utilities/jobs")
@RequiredArgsConstructor
public class UtilityJobController {

    private final UtilityJobService utilityJobService;
    @Operation(
            summary = "Create Utility Job",
            description = "Creates a job for each utility."
    )
    @PostMapping("/user/{userId}")
    public JobResponse createJob(@PathVariable Long userId, @Valid @RequestBody CreateJobRequest jobRequest){
        return utilityJobService.createJob(userId,jobRequest);
    }

    @Operation(
            summary = "Retrieves Job of particular ID",
            description = "Retrieves jobs of the authenticated user."
    )
    @GetMapping("/{id}")
    public JobResponse findJobById(@PathVariable Long id){
        return utilityJobService.findJobById(id);
    }

    @Operation(
            summary = "Updates Job",
            description = "Updates a specific job of the authenticated user."
    )
    @PatchMapping("/{id}/status")
    public JobResponse updateJobStatus(@PathVariable Long id,@RequestBody @Valid JobStatusRequest status){
        return utilityJobService.updateJobStatus(id,status.status());
    }
    @Operation(
            summary = "Deletes Job",
            description = "Deletes a specific job using its id."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<JobResponse> deleteJob(@PathVariable Long id){
        var jobResponse=utilityJobService.findJobById(id);
        utilityJobService.deleteJob(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @Operation(
            summary = "Retrieves Jobs with specific status",
            description = "Shows the jobs with selected status."
    )
    @GetMapping("/status/{status}")
    public List<JobResponse> findJobByStatus(@PathVariable Status status){
        return utilityJobService.findJobByStatus(status);
    }

    @Operation(
            summary = "Retrieves paginated Jobs",
            description = "Shows jobs of the authenticated user in a page."
    )
    @GetMapping
    public Page<JobResponse> findAllJobsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5")int size,
            @RequestParam(defaultValue = "id")String sortBy,
            @RequestParam(defaultValue = "true")boolean ascending
    ){
        Sort sort=ascending?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page,size,sort);
        return utilityJobService.findAllJobs(pageable);

    }
}
