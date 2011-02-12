package org.xianairlines.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "work_experience")
public class WorkExperience implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "work_experience_id")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "work_experience_start_date")
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "work_experience_end_date")
	private Date endDate;
	
	@Column(name = "work_experience_work_unit", length = 100, nullable = false)
	private String workUnit;
	
	@Column(name = "work_experience_department")
	private String department;
	
	@Column(name = "work_experience_work_name")
	private String workName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staffs_id", nullable = true,insertable=false,updatable=false)
	@org.hibernate.annotations.ForeignKey(name = "fk_work_experience_staffs")
	private Staffs staffs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public Staffs getStaffs() {
		return staffs;
	}

	public void setStaffs(Staffs staffs) {
		this.staffs = staffs;
	}

}
