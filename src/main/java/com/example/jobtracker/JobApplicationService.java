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
        applications.sort(Comparator.comparing(JobApplication::getId));
        return applications;
    }



    public Map<String,Integer> countByStatus(){
        Map<String,Integer> counts = new HashMap<>();
        for(JobApplication app: repository.findAll()){
            counts.put(app.getStatus(),counts.getOrDefault(app.getStatus(),0)+1);
        }
        return counts;
    }

    public JobApplication findById(int id){
        return repository.findById(id);
    }

    public List<JobApplication> findByStatus(String status){
        List<JobApplication> matches =new ArrayList<>();
        for(JobApplication application : repository.findAll()){
            if(application.getStatus().equals(status)){
                matches.add(application);
            }
        }
        return matches;
    }

    public JobApplication findByCompany(String company){
        for(JobApplication application : repository.findAll()){
            if(application.getCompany().equals(company)){
                return application;
            }
        }
        return null;
    }

    public JobApplication create(JobApplicationRequest request){
        JobApplication application = new JobApplication(repository.newId(),request.company(),request.role(),request.status());
        return repository.save(application);
    }


    public JobApplication update(int id, JobApplicationRequest request){
        if(repository.findById(id)==null){
            return null;
        }
        JobApplication updated = new JobApplication(id,request.company(),request.role(),request.status());

        return repository.save(updated);
    }


    public JobApplication deleteById(int id){
        if(repository.findById(id)==null){
            return null;
        }
        return repository.deleteById(id);
    }




}
