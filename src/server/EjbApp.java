package server;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import model.User;
import server.serverService.LoginBean;
import server.serverService.LoginBeanRemote;
import server.serverService.Service;

@ApplicationPath("a")
public class EjbApp extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> cls = new HashSet<>();
        cls.add(Service.class);
        cls.add(LoginBean.class);
        return cls;
    }



    public static void main(String[] args) {

    }



}
