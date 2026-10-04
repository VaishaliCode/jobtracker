package com.example.jobtracker;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
    public class FullSampleData implements SampleData{
    @Override
    public List<JobApplication> load(){
        return List.of(
                new JobApplication(1, "Zerodha", "Backend Developer", "APPLIED"),
                new JobApplication(2, "Razorpay", "Java Developer", "INTERVIEW"),
                new JobApplication(33, "Swiggy", "SDE-1", "REJECTED"),
                new JobApplication(4, "CRED", "Backend Intern", "APPLIED"),
                new JobApplication(5, "Groww", "Java Developer", "INTERVIEW"));
    }

}
