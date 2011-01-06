package org.xianairlines.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "staffs")
public class Staffs implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "staffs_id")
	private Long id;

	@Column(name = "staffs_name", length = 100, nullable = false)
	private String name;

	@Column(name = "staffs_gender", length = 100, nullable = false)
	private String gender;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "staffs_birthdate", nullable = false)
	private Date birthdate;

	@Column(name = "staffs_nativePlace", length = 100, nullable = false)
	private String nativePlace;

	@Column(name = "staffs_nation", length = 100, nullable = false)
	private String nation;

	@Column(name = "staffs_maritalStatus", length = 100, nullable = false)
	private String maritalStatus;

	@Column(name = "staffs_politicsStatus", length = 100, nullable = false)
	private String politicsStatus;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "staffs_party_date", nullable = false)
	private Date partyDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "staffs_work_date", nullable = false)
	private Date workDate;

	@Column(name = "staffs_education_background", length = 100, nullable = false)
	private String educationBackground;

	@Column(name = "staffs_degree", length = 100, nullable = false)
	private String degree;

	@Column(name = "staffs_graduate_school", length = 100, nullable = false)
	private String graduateSchool;

	@Column(name = "staffs_professional_title", length = 100, nullable = false)
	private String professionalTitle;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "staffs_professional_title_date", nullable = false)
	private Date professionalTitleDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "staffs_start_date", nullable = false)
	private Date startDate;
	
	@Column(name = "staffs_others", length = 100)
	private String others;

	@Column(name = "staffs_specialty", length = 100, nullable = false)
	private String specialty;

	@Column(name = "staffs_identityNo", length = 100, nullable = false)
	private String identityNo;

	@Column(name = "staffs_tel", length = 100, nullable = false)
	private String tel;

	@Column(name = "staffs_mobile", length = 100, nullable = false)
	private String mobile;

	@Column(name = "staffs_driving_license_level", length = 100, nullable = false)
	private String drivingLicenseLevel;

	@Column(name = "staffs_home_address", length = 100, nullable = false)
	private String homeAddress;

	@Column(name = "staffs_native_address", length = 100, nullable = false)
	private String nativeAddress;

	@Column(name = "staffs_email", length = 100, nullable = false)
	private String email;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "staffs_id", nullable = false)
    @org.hibernate.annotations.IndexColumn(name = "work_experience_position")
    @org.hibernate.annotations.BatchSize(size = 10)
	private List<WorkExperience> workExperiences = new ArrayList<WorkExperience>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "staffs_id", nullable = false)
    @org.hibernate.annotations.IndexColumn(name = "relatives_position")
    @org.hibernate.annotations.BatchSize(size = 10)
	private List<Relatives> relatives = new ArrayList<Relatives>();

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "staffs_id", nullable = false)
    @org.hibernate.annotations.IndexColumn(name = "edu_experience_position")
    @org.hibernate.annotations.BatchSize(size = 10)
	private List<EduExperience> eduExperiences = new ArrayList<EduExperience>();

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "staffs_id", nullable = false)
    @org.hibernate.annotations.BatchSize(size = 10)
	private Set<Spouse> spouses = new HashSet<Spouse>();
	
	public Staffs(){
	}

	public Staffs(String name, String gender, Date birthdate,
			String nativePlace, String nation, String maritalStatus,
			String politicsStatus, Date partyDate, Date workDate,
			String educationBackground, String degree, String graduateSchool,
			String professionalTitle, Date professionalTitleDate,
			String specialty, String identityNo, String tel, String mobile,
			String drivingLicenseLevel, String homeAddress,
			String nativeAddress, String email, Date startDate,
			List<WorkExperience> workExperiences, List<Relatives> relatives,
			List<EduExperience> eduExperiences, Set<Spouse> spouses) {
		super();
		this.name = name;
		this.gender = gender;
		this.birthdate = birthdate;
		this.nativePlace = nativePlace;
		this.nation = nation;
		this.maritalStatus = maritalStatus;
		this.politicsStatus = politicsStatus;
		this.partyDate = partyDate;
		this.workDate = workDate;
		this.educationBackground = educationBackground;
		this.degree = degree;
		this.graduateSchool = graduateSchool;
		this.professionalTitle = professionalTitle;
		this.professionalTitleDate = professionalTitleDate;
		this.specialty = specialty;
		this.identityNo = identityNo;
		this.tel = tel;
		this.mobile = mobile;
		this.drivingLicenseLevel = drivingLicenseLevel;
		this.homeAddress = homeAddress;
		this.nativeAddress = nativeAddress;
		this.email = email;
		this.workExperiences = workExperiences;
		this.relatives = relatives;
		this.eduExperiences = eduExperiences;
		this.spouses = spouses;
		this.startDate = startDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getPoliticsStatus() {
		return politicsStatus;
	}

	public void setPoliticsStatus(String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}

	public Date getPartyDate() {
		return partyDate;
	}

	public void setPartyDate(Date partyDate) {
		this.partyDate = partyDate;
	}

	public Date getWorkDate() {
		return workDate;
	}

	public void setWorkDate(Date workDate) {
		this.workDate = workDate;
	}

	public String getEducationBackground() {
		return educationBackground;
	}

	public void setEducationBackground(String educationBackground) {
		this.educationBackground = educationBackground;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public String getProfessionalTitle() {
		return professionalTitle;
	}

	public void setProfessionalTitle(String professionalTitle) {
		this.professionalTitle = professionalTitle;
	}

	public Date getProfessionalTitleDate() {
		return professionalTitleDate;
	}

	public void setProfessionalTitleDate(Date professionalTitleDate) {
		this.professionalTitleDate = professionalTitleDate;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDrivingLicenseLevel() {
		return drivingLicenseLevel;
	}

	public void setDrivingLicenseLevel(String drivingLicenseLevel) {
		this.drivingLicenseLevel = drivingLicenseLevel;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getNativeAddress() {
		return nativeAddress;
	}

	public void setNativeAddress(String nativeAddress) {
		this.nativeAddress = nativeAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<WorkExperience> getWorkExperiences() {
		return workExperiences;
	}

	public void setWorkExperiences(List<WorkExperience> workExperiences) {
		this.workExperiences = workExperiences;
	}

	public List<Relatives> getRelatives() {
		return relatives;
	}

	public void setRelatives(List<Relatives> relatives) {
		this.relatives = relatives;
	}

	public List<EduExperience> getEduExperiences() {
		return eduExperiences;
	}

	public void setEduExperiences(List<EduExperience> eduExperiences) {
		this.eduExperiences = eduExperiences;
	}

	public Set<Spouse> getSpouses() {
		return spouses;
	}

	public void setSpouses(Set<Spouse> spouses) {
		this.spouses = spouses;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getOthers() {
		return others;
	}

	
	
	//move spouse properties to staff
	
	@Column(name = "spouse_name", length = 100)
	private String spouseName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "spouse_birthdate")
	private Date spouseBirthdate;
	
	@Column(name = "spouse_politics_status", length = 100)
	private String spousePoliticsStatus;
	
	@Column(name = "spouse_native_place", length = 100)
	private String spouseNativePlace;
	
	@Column(name = "spouse_work_name", length = 100)
	private String spouseWorkName;
	
	@Column(name = "spouse_tel", length = 100)
	private String spouseTel;
	
	@Column(name = "spouse_work_unit", length = 100)
	private String spouseWorkUnit;
	



	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public Date getSpouseBirthdate() {
		return spouseBirthdate;
	}

	public void setSpouseBirthdate(Date spouseBirthdate) {
		this.spouseBirthdate = spouseBirthdate;
	}

	public String getSpousePoliticsStatus() {
		return spousePoliticsStatus;
	}

	public void setSpousePoliticsStatus(String spousePoliticsStatus) {
		this.spousePoliticsStatus = spousePoliticsStatus;
	}

	public String getSpouseNativePlace() {
		return spouseNativePlace;
	}

	public void setSpouseNativePlace(String spouseNativePlace) {
		this.spouseNativePlace = spouseNativePlace;
	}

	public String getSpouseWorkName() {
		return spouseWorkName;
	}

	public void setSpouseWorkName(String spouseWorkName) {
		this.spouseWorkName = spouseWorkName;
	}

	public String getSpouseTel() {
		return spouseTel;
	}

	public void setSpouseTel(String spouseTel) {
		this.spouseTel = spouseTel;
	}

	public String getSpouseWorkUnit() {
		return spouseWorkUnit;
	}

	public void setSpouseWorkUnit(String spouseWorkUnit) {
		this.spouseWorkUnit = spouseWorkUnit;
	}
}