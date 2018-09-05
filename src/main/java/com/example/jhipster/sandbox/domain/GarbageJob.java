package com.example.jhipster.sandbox.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A GarbageJob.
 */
@Entity
@Table(name = "garbage_job")
public class GarbageJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @Column(name = "real_job_name")
    private String realJobName;

    @Column(name = "real_job_description")
    private String realJobDescription;

    @Column(name = "real_salary")
    private Long realSalary;

    @Column(name = "dismiss_after_max_months")
    private Integer dismissAfterMaxMonths;

    @Column(name = "allow_renew")
    private Boolean allowRenew;

    @ManyToOne
    @JsonIgnoreProperties("garbageJobs")
    private Job job;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public GarbageJob departmentName(String departmentName) {
        this.departmentName = departmentName;
        return this;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRealJobName() {
        return realJobName;
    }

    public GarbageJob realJobName(String realJobName) {
        this.realJobName = realJobName;
        return this;
    }

    public void setRealJobName(String realJobName) {
        this.realJobName = realJobName;
    }

    public String getRealJobDescription() {
        return realJobDescription;
    }

    public GarbageJob realJobDescription(String realJobDescription) {
        this.realJobDescription = realJobDescription;
        return this;
    }

    public void setRealJobDescription(String realJobDescription) {
        this.realJobDescription = realJobDescription;
    }

    public Long getRealSalary() {
        return realSalary;
    }

    public GarbageJob realSalary(Long realSalary) {
        this.realSalary = realSalary;
        return this;
    }

    public void setRealSalary(Long realSalary) {
        this.realSalary = realSalary;
    }

    public Integer getDismissAfterMaxMonths() {
        return dismissAfterMaxMonths;
    }

    public GarbageJob dismissAfterMaxMonths(Integer dismissAfterMaxMonths) {
        this.dismissAfterMaxMonths = dismissAfterMaxMonths;
        return this;
    }

    public void setDismissAfterMaxMonths(Integer dismissAfterMaxMonths) {
        this.dismissAfterMaxMonths = dismissAfterMaxMonths;
    }

    public Boolean isAllowRenew() {
        return allowRenew;
    }

    public GarbageJob allowRenew(Boolean allowRenew) {
        this.allowRenew = allowRenew;
        return this;
    }

    public void setAllowRenew(Boolean allowRenew) {
        this.allowRenew = allowRenew;
    }

    public Job getJob() {
        return job;
    }

    public GarbageJob job(Job job) {
        this.job = job;
        return this;
    }

    public void setJob(Job job) {
        this.job = job;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GarbageJob garbageJob = (GarbageJob) o;
        if (garbageJob.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), garbageJob.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GarbageJob{" +
            "id=" + getId() +
            ", departmentName='" + getDepartmentName() + "'" +
            ", realJobName='" + getRealJobName() + "'" +
            ", realJobDescription='" + getRealJobDescription() + "'" +
            ", realSalary=" + getRealSalary() +
            ", dismissAfterMaxMonths=" + getDismissAfterMaxMonths() +
            ", allowRenew='" + isAllowRenew() + "'" +
            "}";
    }
}
