package server;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import server.serverService.Service;

@ApplicationPath("a")
public class EjbApp extends Application {

    /**
     * Registracia bean-y do REST aplikacie
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> cls = new HashSet<>();
        cls.add(Service.class);
        return cls;
    }

}
