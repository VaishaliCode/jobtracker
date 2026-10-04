package com.example.jobtracker;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JobApplicationRepository {
    private final Map<Integer, JobApplication> store = new LinkedHashMap<>();

    public JobApplicationRepository(List<SampleData> sources) {
        for (SampleData source : sources) {
            for (JobApplication app : source.load()) {
                store.put(app.id(), app);
            }
        }
    }

    public List<JobApplication> findAll() {
        return new ArrayList<>(store.values());
    }

}
