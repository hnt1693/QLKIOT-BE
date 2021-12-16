package com.fil.authentication.repository;

import com.fil.authentication.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

    List<Job> getAllByJobCodeLike(String jobCode);

    List<Job> getAllByJobParentIsNull();
}
