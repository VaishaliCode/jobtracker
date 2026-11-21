package com.example.jobtracker;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class JobApplicationService {
    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository){
        this.repository=repository;
    }

    public Page<JobApplication> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Map<String,Integer> countByStatus(){
        Map<String,Integer> counts = new HashMap<>();
        for(JobApplication app: repository.findAll()){
            counts.put(app.getStatus(),counts.getOrDefault(app.getStatus(),0)+1);
        }
        return counts;
    }

    public JobApplication findById(int id){
        return repository.findById(id).orElse(null);
    }

    public List<JobApplication> findByUserId(int userId){
        return repository.findByUserId(userId);
    }

    public Page<JobApplication> findByStatus(String status, Pageable pageable){
        return repository.findByStatus(status,pageable);
    }

    public List<JobApplication> findByCompany(String company){
        return repository.findByCompany(company);
    }

    public JobApplication create(JobApplicationRequest request){
        JobApplication application = new JobApplication(null,request.company(),request.role(),request.status());
        return repository.save(application);
    }


    @Transactional
    public JobApplication update(int id, JobApplicationRequest request){
        JobApplication application = repository.findById(id).orElse(null);
        if(application == null){
            return null;
        }
        application.change(request.company(),request.role(),request.status());
        return application;
    }

    @Transactional
    public JobApplication updateStatus(int id, StatusRequest statusRequest){
        JobApplication application = repository.findById(id).orElse(null);
        if (application == null){
            return null;
        }
        application.changeStatus(statusRequest.status());
        return application;
    }

    @Transactional
    public List<ApplicationView> findAllWithOwner(){
        List<ApplicationView> views = new ArrayList<>();
        for(JobApplication application : repository.findAllWithOwner()){
            views.add(new ApplicationView(
                    application.getId(),
                    application.getCompany(),
                    application.getRole(),
                    application.getStatus(),
                    application.ownerName()));
        }
        return views;
    }

    @Transactional
    public List<ApplicationView> findByStatusWithOwner(String status){
        List<ApplicationView> views = new ArrayList<>();
        for(JobApplication application : repository.findAllOwnerWithStatus(status)){
            views.add(new ApplicationView(
                    application.getId(),
                    application.getCompany(),
                    application.getRole(),
                    application.getStatus(),
                    application.ownerName()
            ));
        }
        return views;

    }


    public JobApplication deleteById(int id){
        JobApplication application = findById(id);
        if(application == null){
            return null;
        }
        repository.deleteById(id);
        return application;

    }




}
