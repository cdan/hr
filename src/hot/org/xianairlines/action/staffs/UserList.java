package org.xianairlines.action.staffs;

import org.xianairlines.model.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("userList")
public class UserList extends EntityQuery<User> {

	private static final String EJBQL = "select user from User user";

	private static final String[] RESTRICTIONS = {
			"lower(user.name) like lower(concat(#{userList.user.name},'%'))",
			//"lower(user.password) like lower(concat(#{userList.user.password},'%'))",
			"lower(user.role) like lower(concat(#{userList.user.role},'%'))", };

	private User user = new User();

	public UserList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public User getUser() {
		return user;
	}
}
