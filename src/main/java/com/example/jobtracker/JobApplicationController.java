package com.example.jobtracker;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JobApplicationController {

    private  final JobApplicationService service;

    public JobApplicationController(JobApplicationService service){
        this.service=service;
    }

    @GetMapping("/api/applications")
    public List<JobApplication> findAll(){
        return service.findAll();
    }

    @GetMapping("api/applications/stats")
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
    public List<JobApplication> findByStatus(@PathVariable String status){
        return service.findByStatus(status);
    }

    @GetMapping("/api/applications/company/{company}")
    public ResponseEntity<JobApplication> findByCompany(@PathVariable  String company){
        JobApplication application = service.findByCompany(company);
        if(application == null ){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(application);
    }

}

