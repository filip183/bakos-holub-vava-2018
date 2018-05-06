package server.serverService;


import model.User;

import javax.ejb.Remote;
import javax.ws.rs.QueryParam;

public interface LoginBeanRemote {

    User getUser();
}
