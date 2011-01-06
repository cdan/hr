package org.xianairlines.action.staffs;

import java.util.Set;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.xianairlines.model.Spouse;
import org.xianairlines.model.Staffs;

@Name("spouseAction")
@Scope(ScopeType.CONVERSATION)
public class SpouseAction {
	@In
	private Staffs staff;
	
	@In
	private EntityManager entityManager;
	
	private Spouse spouse;
	
	
	public Spouse getSpouse() {
		if(spouse==null){
			spouse = new Spouse();
		}
		return spouse;
	}

	public void setSpouse(Spouse spouse) {
		this.spouse = spouse;
	}
	
	public void spouseInit(){
		Set<Spouse> set = staff.getSpouses();
		if(set.size()>0){
			spouse = set.iterator().next();
		}
	}
	
	public String persist(){
		spouse.setStaffs(staff);
		staff.getSpouses().add(spouse);
		entityManager.persist(spouse);
		entityManager.flush();
		return "persist";
	}
	
	
	
}
