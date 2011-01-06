package org.xianairlines.action.staffs;

import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.xianairlines.model.EduExperience;
import org.xianairlines.model.Staffs;

@Name("eduExperienceAction")
@Scope(ScopeType.CONVERSATION)
public class EduExperienceAction {
	@In
	private Staffs staff;

	private EduExperience eduExperience;
	
	private Long eduExperienceId;
	
	@In
	private EntityManager entityManager;
	
	
	public Long getEduExperienceId() {
		return eduExperienceId;
	}

	public void setEduExperienceId(Long eduExperienceId) {
		this.eduExperienceId = eduExperienceId;
	}

	
	public EduExperience getEduExperience() {
		if(eduExperience==null){
			eduExperience = new EduExperience();
		}
		return eduExperience;
	}

	public void setEduExperience(EduExperience eduExperience) {
		this.eduExperience = eduExperience;
	}
	

	public void editEduExperienceInit() {
		if (eduExperienceId != null) {
			eduExperience = entityManager.find(EduExperience.class,
					eduExperienceId);
		}
	}

	public String addEduExperience() {
		if (!staff.getEduExperiences().contains(eduExperience)) {
			staff.getEduExperiences().add(eduExperience);
			eduExperience.setStaffs(staff);
			entityManager.persist(eduExperience);
			entityManager.persist(staff);
			entityManager.flush();
			eduExperienceId = eduExperience.getId();
			return "add";
		} else {
			entityManager.merge(eduExperience);
			entityManager.flush();
			return "modify";
		}

	}

	public void newEduExperience() {
		eduExperience = new EduExperience();
		eduExperienceId = null;
	}

	public List<EduExperience> getEduExperiences() {
		return this.staff.getEduExperiences();
	}

	public String revomeEduExperience() {
		if (eduExperienceId != null) {
			staff.getEduExperiences().remove(eduExperience);
			entityManager.remove(eduExperience);
			entityManager.flush();
			newEduExperience();
		}
		return "";
	}
}
