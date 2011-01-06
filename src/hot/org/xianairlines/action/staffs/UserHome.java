package org.xianairlines.action.staffs;

import org.xianairlines.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("userHome")
public class UserHome extends EntityHome<User> {

	public void setUserId(Integer id) {
		setId(id);
	}

	public Integer getUserId() {
		return (Integer) getId();
	}

	@Override
	protected User createInstance() {
		User user = new User();
		return user;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public User getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
