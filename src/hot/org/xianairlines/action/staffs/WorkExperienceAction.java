package org.xianairlines.action.staffs;

import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.xianairlines.model.Staffs;
import org.xianairlines.model.WorkExperience;

@Name("workExperienceAction")
@Scope(ScopeType.CONVERSATION)
public class WorkExperienceAction {
	
	@In
	private Staffs staff;
	
	private WorkExperience workExperience;
	
	private Long workExperienceId;
	
	@In
	private EntityManager entityManager;
	
	public WorkExperience getWorkExperience() {
		if(workExperience == null){
			workExperience = new WorkExperience();
		}
		return workExperience;
	}

	public void setWorkExperience(WorkExperience workExperience) {
		this.workExperience = workExperience;
	}

	public Long getWorkExperienceId() {
		return workExperienceId;
	}

	public void setWorkExperienceId(Long workExperienceId) {
		this.workExperienceId = workExperienceId;
	}
	

	
	public void editWorkExperienceInit(){
		if(workExperienceId !=null){
			workExperience = entityManager.find(WorkExperience.class, workExperienceId);
		}
	}
	
	public String addWorkExperience(){
		if(!staff.getWorkExperiences().contains(workExperience)){
			staff.getWorkExperiences().add(workExperience);
			workExperience.setStaffs(staff);
			entityManager.persist(workExperience);
			entityManager.persist(staff);
			entityManager.flush();
			workExperienceId = workExperience.getId();
			return "add";
		}else{
			entityManager.merge(workExperience);
			entityManager.flush();
			return "modify";
		}
		
	}
	
	public void newWorkExperience(){
		workExperience =  new WorkExperience();
		workExperienceId =null;
	}
	
	public List<WorkExperience> getWorkExperiences(){
		return this.staff.getWorkExperiences();
	}
	
	public String revomeWorkExperience(){
		if(workExperienceId!=null){
			staff.getWorkExperiences().remove(workExperience);
			entityManager.remove(workExperience);
			entityManager.flush();
			newWorkExperience();
		}
		return "";
	}
	
}
