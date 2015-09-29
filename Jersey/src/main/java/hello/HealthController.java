package hello;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Component
@Path("/health")
public class HealthController {

	@GET
	@Produces("application/json")
	public String health() {
		return "Jersey: Up and Running!";
	}

}
