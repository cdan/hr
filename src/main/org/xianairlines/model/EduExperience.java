package org.xianairlines.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "edu_experience")
public class EduExperience implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "edu_experience_id")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "edu_experience_start_date")
	private Date startDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "edu_experience_end_date")
	private Date endDate;
	
	@Column(name = "edu_experience_school", length = 100, nullable = false)
	private String school;
	
	@Column(name = "edu_experience_specialty", length = 100)
	private String specialty;
	
	@Column(name = "edu_experience_education_background", length = 100)
	private String educationBackground;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staffs_id", nullable = true,insertable=false,updatable=false)
	@org.hibernate.annotations.ForeignKey(name = "fk_edu_experience_staffs")
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
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getEducationBackground() {
		return educationBackground;
	}
	public void setEducationBackground(String educationBackground) {
		this.educationBackground = educationBackground;
	}
	public Staffs getStaffs() {
		return staffs;
	}
	public void setStaffs(Staffs staffs) {
		this.staffs = staffs;
	}
	
}

