package com.example.jobtracker;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.security.PublicKey;
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

    public JobApplication findById(int id){
        return repository.findById(id);
    }

    public List<JobApplication> findByStatus(String status){
        List<JobApplication> matches =new ArrayList<>();
        for(JobApplication application : repository.findAll()){
            if(application.status().equals(status)){
                matches.add(application);
            }
        }
        return matches;
    }

    public JobApplication findByCompany(String company){
        for(JobApplication application : repository.findAll()){
            if(application.company().equals(company)){
                return application;
            }
        }
        return null;
    }




}
