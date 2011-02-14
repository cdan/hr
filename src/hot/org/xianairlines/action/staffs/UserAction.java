package org.xianairlines.action.staffs;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.faces.FacesMessages;
import org.xianairlines.model.User;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cdan
 * Date: 11-2-12
 * Time: 下午5:06
 * To change this template use File | Settings | File Templates.
 */

@Name("userAction")
public class UserAction {

    @In
	private EntityManager em;

    @In(create=true)
    @Out
    User user ;

    @In
    FacesMessages facesMessages;

    String passwordVerify = null;
    public void setPasswordVerify(String password) {
        this.passwordVerify = password;
    }
    public String getPasswordVerify() {
        return passwordVerify;
    }

    public boolean isValidNamePassword() {
        boolean ok = true;
        if (!isUniqueName()) {
            facesMessages.add("userName", "This name is already in use");
            ok = false;
        }
        if (!isPasswordsMatch()) {
            facesMessages.add("passwordVerify", "Must match password field");
            ok = false;
        }
        return ok;
    }

    @SuppressWarnings("unchecked")
    private boolean isUniqueName() {
        String name = user.getName();
        if (name == null) return true;

        List<User> results = em.createQuery("select c from User c where c.name = :name")
            .setParameter("name", name)
            .getResultList();
        return results.size() == 0;
    }

    private boolean isPasswordsMatch() {
        String customerpass = user.getPassword();
        return (passwordVerify != null)
            && (customerpass != null)
            && (customerpass.equals(passwordVerify));

    }

    public java.lang.String save() {
        if (!isValidNamePassword()) {
           facesMessages.add("User name #{customer.userName} is not unique");
           return null;
        }
        try {
           em.persist(user);
            facesMessages.addFromResourceBundle("createCustomerSuccess");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
