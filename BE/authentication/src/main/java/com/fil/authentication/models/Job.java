package com.fil.authentication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fil.authentication.config.auditor.Auditor;
import com.fil.authentication.enums.JOB_STATUS;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jobs")
@Data
@AllArgsConstructor

public class Job extends Auditor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String jobName;
    @Column(name = "code")
    private String jobCode;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private JOB_STATUS status;
    @Column(name = "total")
    private int total;
    @Column(name = "process")
    private int process;
    @Column(name="process_num")
    private int numOfProcess;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Job jobParent;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private List<Job> childs = new ArrayList<>();

    public Job() {
    }

    public Job(Long id, String jobName, String jobCode, JOB_STATUS status, int total, int process) {
        this.id = id;
        this.jobName = jobName;
        this.jobCode = jobCode;
        this.status = status;
        this.total = total;
        this.process = process;
    }

    public Job(Long id, String jobName, String jobCode, JOB_STATUS status, int total, int process, int numOfProcess) {
        this.id = id;
        this.jobName = jobName;
        this.jobCode = jobCode;
        this.status = status;
        this.total = total;
        this.process = process;
        this.numOfProcess = numOfProcess;
    }
}
