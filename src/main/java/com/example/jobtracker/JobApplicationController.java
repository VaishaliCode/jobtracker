package com.example.jobtracker;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class JobApplicationController {

    private  final JobApplicationService service;

    public JobApplicationController(JobApplicationService service){
        this.service=service;
    }

    @GetMapping("/api/applications")
    public PagedModel<JobApplication> findAll(@PageableDefault(size = 20, sort = "id") Pageable pageable){
        return new PagedModel<>(service.findAll(pageable));
    }

    @GetMapping("/api/applications/stats")
    public Map<String,Integer> stats(){
        return service.countByStatus();
    }

    @GetMapping("/api/applications/{id}")
    public ResponseEntity<JobApplication> findById(@PathVariable int id){
        JobApplication application = service.findById(id);
        if(application==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(application);
    }

    @GetMapping("/api/applications/status/{status}")
    public PagedModel<JobApplication> findByStatus(@PageableDefault(size = 20, sort = "id") Pageable pageable, @PathVariable String status){
        return new PagedModel<>(service.findByStatus(status,pageable));
    }

    @GetMapping("/api/applications/company/{company}")
    public List<JobApplication> findByCompany(@PathVariable  String company){
        return service.findByCompany(company);
    }

    @GetMapping("/api/applications/user/{userId}")
    public List<JobApplication> findByUserId(@PathVariable int userId){
        return service.findByUserId(userId);
    }


   @PostMapping("/api/applications")
    public ResponseEntity<JobApplication> create(@Valid  @RequestBody JobApplicationRequest request) {
        JobApplication saved = service.create(request);
        URI location = URI.create("/api/applications/" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/api/applications/{id}")
    public ResponseEntity<JobApplication> update( @PathVariable int id ,@Valid @RequestBody JobApplicationRequest request){
        JobApplication update = service.update(id,request);
        if(update == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(update);
    }

    @PatchMapping("/api/applications/{id}/status")
    public ResponseEntity<JobApplication> updateStatus(@PathVariable int id, @Valid @RequestBody StatusRequest statusRequest){
        JobApplication updateStatus = service.updateStatus(id, statusRequest);
        if(updateStatus == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updateStatus);
    }

    @DeleteMapping("/api/applications/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id){
        JobApplication delete = service.deleteById(id);
        if(delete==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}

