package com.example.jobtracker;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Integer> {
    Page<JobApplication> findByStatus(String status,Pageable pageable);

    List<JobApplication> findByCompany(String company);

    List<JobApplication> findByUserId(int userId);

}