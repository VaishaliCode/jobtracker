package com.example.jobtracker;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JobApplicationRepository {
    private final Map<Integer, JobApplication> store = new LinkedHashMap<>();
    private int nextId = 1;

    public JobApplicationRepository(List<SampleData> sources) {
        for (SampleData source : sources) {
            for (JobApplication app : source.load()) {
                store.put(app.id(), app);
                if(app.id()>=nextId){
                    nextId=app.id()+1;
                }
            }
        }
    }

    public int newId(){
        int id = nextId;
        nextId = nextId + 1 ;
        return id;
    }


    public List<JobApplication> findAll() {
        return new ArrayList<>(store.values());
    }

    public JobApplication findById(int id){
        return store.get(id);
    }

    public JobApplication save(JobApplication application){
        store.put(application.id(),application);
        return application;
    }

    public JobApplication deleteById(int id){
        return store.remove(id);
    }

}
