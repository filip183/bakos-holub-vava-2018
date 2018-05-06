package server.serverService;


import model.User;

import javax.ejb.Remote;
import javax.ws.rs.QueryParam;

@Remote
public interface LoginBeanRemote {

    User getUser();
    User authentification(User user);
}
