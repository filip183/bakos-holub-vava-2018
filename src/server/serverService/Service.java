package server.serverService;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


@Stateless
@LocalBean
@Path("/sayHello")
public class Service {

    public Service() {
    }

    @GET
    public String sayHello(@QueryParam("param1") String who) {
        return "hello " + who;
    }

}
