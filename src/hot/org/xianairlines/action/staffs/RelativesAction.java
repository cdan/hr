package org.xianairlines.action.staffs;

import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.xianairlines.model.Relatives;
import org.xianairlines.model.Staffs;

@Name("relativesAction")
@Scope(ScopeType.CONVERSATION)
public class RelativesAction {
	private Relatives relative;

	private Long relativeId;
	
	
	@In
	private Staffs staff;
	
	@In
	private EntityManager entityManager;
	

	public Relatives getRelative() {
		if(relative == null){
			relative = new Relatives();
		}
		return relative;
	}

	public void setRelative(Relatives relative) {
		this.relative = relative;
	}

	public Long getRelativeId() {
		return relativeId;
	}

	public void setRelativeId(Long relativeId) {
		this.relativeId = relativeId;
	}

	public void editRelativesInit() {
		if (relativeId != null) {
			relative = entityManager.find(Relatives.class, relativeId);
		}
	}

	public String addRelative() {
		if (!staff.getRelatives().contains(relative)) {
			staff.getRelatives().add(relative);
			relative.setStaffs(staff);
			entityManager.persist(relative);
			entityManager.persist(staff);
			entityManager.flush();
			relativeId = relative.getId();
			return "add";
		} else {
			entityManager.merge(relative);
			entityManager.flush();
			return "modify";
		}

	}

	public void newRelative() {
		relative = new Relatives();
		relativeId = null;
	}

	public List<Relatives> getRelatives() {
		return this.staff.getRelatives();
	}

	public String revomeRelative() {
		if (relativeId != null) {
			staff.getRelatives().remove(relative);
			entityManager.remove(relative);
			entityManager.flush();
			newRelative();
		}
		return "";
	}
}
