package com.example.jobtracker;

import jakarta.persistence.*;


@Entity
public class JobApplication{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String company;
    private String role;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser user;

    protected JobApplication(){}

    public JobApplication(Integer id, String company, String role, String status) {
        this.id = id;
        this.company = company;
        this.role = role;
        this.status = status;
    }

    public void change(String company, String role, String status) {
        this.company = company;
        this.role = role;
        this.status = status;
    }

    public void changeStatus(String status){
        this.status=status;
    }

    public String OwnerName(){
        if (user == null){
            return null;
        }
        return user.getName();
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