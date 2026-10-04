package com.example.jobtracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
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


}

