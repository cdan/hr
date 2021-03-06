package org.xianairlines.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.xianairlines.action.staffs.AuditedEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@EntityListeners(AuditedEntityListener.class)
@Table(name = "relatives")
public class Relatives implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "relatives_id")
	private Long id;
	
	@Column(name = "relatives_name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "relatives_relative", length = 100 )
	private String relative;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "relatives_birthdate" )
	private Date birthdate;
	
	@Column(name = "relatives_work_unit", length = 100 )
	private String workUnit;
	
	@Column(name = "relatives_tel", length = 100 )
	private String tel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staffs_id", nullable = true,insertable=false,updatable=false)
	@org.hibernate.annotations.ForeignKey(name = "fk_relatives_staffs")
	private Staffs staffs;
	
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
	public String getRelative() {
		return relative;
	}
	public void setRelative(String relative) {
		this.relative = relative;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getWorkUnit() {
		return workUnit;
	}
	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
    @JsonIgnore
	public Staffs getStaffs() {
		return staffs;
	}
	public void setStaffs(Staffs staffs) {
		this.staffs = staffs;
	}
	

	
}

