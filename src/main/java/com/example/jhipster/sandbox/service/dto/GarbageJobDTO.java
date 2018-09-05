package com.example.jhipster.sandbox.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the GarbageJob entity.
 */
public class GarbageJobDTO implements Serializable {

    private Long id;

    @NotNull
    private String departmentName;

    private String realJobName;

    private String realJobDescription;

    private Long realSalary;

    private Integer dismissAfterMaxMonths;

    private Boolean allowRenew;

    private Long jobId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRealJobName() {
        return realJobName;
    }

    public void setRealJobName(String realJobName) {
        this.realJobName = realJobName;
    }

    public String getRealJobDescription() {
        return realJobDescription;
    }

    public void setRealJobDescription(String realJobDescription) {
        this.realJobDescription = realJobDescription;
    }

    public Long getRealSalary() {
        return realSalary;
    }

    public void setRealSalary(Long realSalary) {
        this.realSalary = realSalary;
    }

    public Integer getDismissAfterMaxMonths() {
        return dismissAfterMaxMonths;
    }

    public void setDismissAfterMaxMonths(Integer dismissAfterMaxMonths) {
        this.dismissAfterMaxMonths = dismissAfterMaxMonths;
    }

    public Boolean isAllowRenew() {
        return allowRenew;
    }

    public void setAllowRenew(Boolean allowRenew) {
        this.allowRenew = allowRenew;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GarbageJobDTO garbageJobDTO = (GarbageJobDTO) o;
        if (garbageJobDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), garbageJobDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GarbageJobDTO{" +
            "id=" + getId() +
            ", departmentName='" + getDepartmentName() + "'" +
            ", realJobName='" + getRealJobName() + "'" +
            ", realJobDescription='" + getRealJobDescription() + "'" +
            ", realSalary=" + getRealSalary() +
            ", dismissAfterMaxMonths=" + getDismissAfterMaxMonths() +
            ", allowRenew='" + isAllowRenew() + "'" +
            ", job=" + getJobId() +
            "}";
    }
}
