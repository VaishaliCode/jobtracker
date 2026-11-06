package com.example.jobtracker;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class JobApplication{

    @Id
    private Integer id;
    private String company;
    private String role;
    private String status;

    protected JobApplication(){}

    public JobApplication(Integer id, String company, String role, String status) {
        this.id = id;
        this.company = company;
        this.role = role;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }
}