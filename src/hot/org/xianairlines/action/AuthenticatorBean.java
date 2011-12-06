package org.xianairlines.action;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.security.Admin;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.xianairlines.model.User;

@Stateless
@Name("authenticator")
public class AuthenticatorBean implements Authenticator
{
    @Logger private Log log;

    @In Identity identity;
    @In Credentials credentials;
    @In private EntityManager entityManager;

    @Out(required=false, scope=ScopeType.SESSION) 
    User currentUser;
    
    public boolean authenticate()
    {
        log.info("authenticating {0}", credentials.getUsername());
        try {
            currentUser = (User)entityManager.createQuery("select u from User u where u.name = #{identity.username} and u.password = #{identity.password}").getSingleResult();
            identity.addRole(currentUser.getRole());
            Contexts.getSessionContext().set("operator", currentUser.getName());
        } catch (PersistenceException e) {
        	e.printStackTrace();
            return false;
        }
//
//        if (currentUser instanceof Admin) {
//            identity.addRole("admin");
//        }
      
        return true;
    }

}
