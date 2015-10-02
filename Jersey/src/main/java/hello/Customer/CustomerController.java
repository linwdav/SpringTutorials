package hello.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Component
@Path("/customer")
@Produces("application/json")
public class CustomerController {

	@Context
	private UriInfo uriInfo;

	@Autowired
	private CustomerRepository customerRepository;

	@GET
	// curl --user david:123 http://localhost:8080/customer
	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}

	@GET
	@Path("{id}")
	// curl --user david:123 http://localhost:8080/customer/0
	public Customer findOne(@PathParam("id") Long id) {
		return customerRepository.findOne(id);
	}

	@POST
	// curl -i --user david:123 -X POST -H 'Content-Type:application/json' -d '{"firstname":"David","lastname":"Lin","email":"david@davidlin.org"}' http://localhost:8080/customer
	public Response save(Customer customer) {
		customer = customerRepository.save(customer);
		URI location = uriInfo.getAbsolutePathBuilder()
				.path("{id}")
				.resolveTemplate("id", customer.getId())
				.build();
		return Response.created(location).build();
	}

	@DELETE
	@Path("{id}")
	// curl --user david:123 -X "DELETE" http://localhost:8080/customer/0
	public Response delete(@PathParam("id") Long id) {
		customerRepository.delete(id);
		return Response.accepted().build();
	}

}