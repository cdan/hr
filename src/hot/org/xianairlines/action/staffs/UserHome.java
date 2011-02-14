package org.xianairlines.action.staffs;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.framework.EntityHome;
import org.xianairlines.model.User;

import java.util.List;

@Name("userHome")
public class UserHome extends EntityHome<User> {

    //@In @Out
    User user;

    public void setUserId(Integer id) {
		setId(id);
	}

	public Integer getUserId() {
		return (Integer) getId();
	}

	@Override
	protected User createInstance() {
        if (user == null)
		user = new User();
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



    @SuppressWarnings("unchecked")
    private boolean isUniqueName() {
        String name = instance.getName();
        if (name == null) return true;

        List<User> results = getEntityManager().createQuery("select c from User c where c.name = :name")
            .setParameter("name", name)
            .getResultList();
        return results.size() == 0;
    }


        String passwordVerify = null;
    public void setPasswordVerify(String password) {
        this.passwordVerify = password;
    }
    public String getPasswordVerify() {
        return passwordVerify;
    }
        private boolean isPasswordsMatch() {
        String customerpass = instance.getPassword();
        return (passwordVerify != null)
            && (customerpass != null)
            && (customerpass.equals(passwordVerify));

    }

    @In
    FacesMessages facesMessages;


    public java.lang.String update() {
        if (!isPasswordsMatch()) {
           facesMessages.add("密码不一致");
           return null;
        }
        try {
            super.update();
            //getEntityManager().persist(instance);
            facesMessages.addFromResourceBundle("更新成功");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public java.lang.String save() {
        if (!isValidNamePassword()) {
           facesMessages.add("用户名 #{customer.userName} 已经存在");
           return null;
        }
        try {
            getEntityManager().persist(instance);
            facesMessages.addFromResourceBundle("生成成功");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

        public boolean isValidNamePassword() {
        boolean ok = true;
        if (!isUniqueName()) {
            facesMessages.add( "用户名已存在");
            ok = false;
        }
        if (!isPasswordsMatch()) {
            facesMessages.add( "密码不一致");
            ok = false;
        }
        return ok;
    }

}
