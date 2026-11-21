package com.example.jobtracker;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication,Integer> {
    Page<JobApplication> findByStatus(String status,Pageable pageable);

    List<JobApplication> findByCompany(String company);

    List<JobApplication> findByUserId(int userId);

    @Query("select a from JobApplication a left join fetch a.user order by a.id")
    List<JobApplication> findAllWithOwner();

    @Query("select a from JobApplication a left join fetch a.user where a.status= :status order by a.id")
    List<JobApplication> findAllOwnerWithStatus(@Param("status") String status);


}