import com.fasterxml.jackson.databind.ObjectMapper;
import hello.Application;
import hello.Health.HealthMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=9000")
public class HealthControllerIntegrationTest {

	private RestTemplate restTemplate = new TestRestTemplate("david", "123");

	@Test
	public void health() {
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:9000/health", String.class);
		Assert.assertTrue(entity.getStatusCode().is2xxSuccessful());

		HealthMessage msg = null;
		try {
			msg = new ObjectMapper().readValue(entity.getBody(), HealthMessage.class);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		Assert.assertNotNull(msg);
		Assert.assertEquals(msg.getMsg(), "Jersey: Up and Running!");
	}

}
