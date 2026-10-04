package com.example.jobtracker;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobApplicationService {
    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository){
        this.repository=repository;
    }

    public List<JobApplication> findAll(){
        List<JobApplication> applications = repository.findAll();
        applications.sort(Comparator.comparing(JobApplication::id));
        return applications;
    }

    public Map<String,Integer> countByStatus(){
        Map<String,Integer> counts = new HashMap<>();
        for(JobApplication app: repository.findAll()){
            counts.put(app.status(),counts.getOrDefault(app.status(),0)+1);
        }

        return counts;
    }
}
