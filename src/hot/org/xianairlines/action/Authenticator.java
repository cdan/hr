package org.xianairlines.action;

import javax.ejb.Local;

@Local
public interface Authenticator {

    boolean authenticate();

}
