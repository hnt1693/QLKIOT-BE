package com.fil.authentication.dao;

import com.fil.authentication.enums.JOB_STATUS;
import com.fil.authentication.models.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Repository
public class JobDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Job getById(final Long id) {
        return entityManager.find(Job.class, id);
    }

    @Transactional
    public Job save(Job job) {
        entityManager.persist(job);
        return job;
    }

    public Job getById(Connection connection, final Long id) {
        try {
            String sql = "Select * from jobs where id = ? ;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            List<Job> l = new ArrayList<>();
            while (resultSet.next()) {
                Long jobId = resultSet.getLong("id");
                String nameName = resultSet.getString("name");
                String jobCode = resultSet.getString("code");
                String jobStatus = resultSet.getString("status");
                int total = resultSet.getInt("total");
                int process = resultSet.getInt("process");
                int numProcess = resultSet.getInt("process_num");
                l.add(new Job(jobId, nameName, jobCode, JOB_STATUS.valueOf(jobStatus), total, process, numProcess));
            }
            return l.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Job save(Connection connection, Job job) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            connection.setAutoCommit(false);
            String sql = "insert into jobs(name,code,status,nguoitao,ngaytao,nguoisua,ngaysua,total,process) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, job.getJobName());
            statement.setString(2, job.getJobCode());
            statement.setString(3, job.getStatus().name());
            statement.setString(4, authentication.getName());
            statement.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            statement.setString(6, authentication.getName());
            statement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(8, job.getTotal());
            statement.setInt(9, job.getProcess());
            statement.executeUpdate();
            connection.commit();
            return getById(connection, job.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Job update(Connection connection, Job job) {
        try {
            connection.setAutoCommit(false);
            String sql = "update jobs set name=?,code=?,status=?,total=?,process=?\n" +
                    "where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, job.getJobName());
            statement.setString(2, job.getJobCode());
            statement.setString(3, job.getStatus().name());
            statement.setInt(4, job.getTotal());
            statement.setInt(5, job.getProcess());
            statement.setLong(6, job.getId());
            statement.executeUpdate();
            connection.commit();
            return getById(connection, job.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public synchronized void addOneProcess(Connection connection,Job job){
        try {
            connection.setAutoCommit(false);
            job = getById(connection,job.getId());
            if(job.getProcess()> job.getTotal()){
                job.setStatus(JOB_STATUS.FINISH);
            }else{
                job.setProcess(job.getProcess()+1);
            }
            update(connection,job);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

