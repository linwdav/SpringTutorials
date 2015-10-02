package hello;

import hello.Customer.CustomerController;
import hello.Health.HealthController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(HealthController.class);
		register(CustomerController.class);
	}

}
