package cz.vut.fit.pis.api;

import java.net.URI;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import cz.vut.fit.pis.api.dto.CarDTO;
import cz.vut.fit.pis.api.dto.ErrorDTO;
import cz.vut.fit.pis.api.dto.PersonDTO;
import cz.vut.fit.pis.data.Car;
import cz.vut.fit.pis.data.Person;
import cz.vut.fit.pis.service.PersonManager;

/*
 * TEST URL:
 * http://localhost:8080/jsf-basic/rest/people/list
 */
@Path("/people")
public class People 
{
	@Inject
	private PersonManager personMgr; 
    @Context
    private UriInfo context;

    public People() 
    {
    }

    @PostConstruct
    public void init()
    {
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PersonDTO> getPeople() 
    {
    	return personMgr.findAll().stream().map(p -> new PersonDTO(p)).toList();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonSingle(@PathParam("id") Long id) 
    {
    	Person p = personMgr.find(id);
    	if (p != null)
    		return Response.ok(new PersonDTO(p)).build();
    	else
    		return Response.status(Status.NOT_FOUND).entity(new ErrorDTO("not found")).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePeople(List<Person> content) 
    {
    	return Response.status(Response.Status.NOT_IMPLEMENTED).entity(new ErrorDTO("Not implemented")).build();
    }
    
    /**
     * Updates a person.
     * @param id
     * @param src
     * @return
     */
    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePersonSingle(@PathParam("id") Long id, PersonDTO src) 
    {
    	Person p = personMgr.find(id);
    	if (p != null)
    	{
    		p.setName(src.getName());
    		p.setSurname(src.getSurname());
            p.setBorn(Date.from(src.getBorn().atStartOfDay()
                    .atZone(ZoneId.systemDefault())
                    .toInstant()));
    		return Response.ok(p).build();
    	}
    	else
    		return Response.status(Status.NOT_FOUND).entity(new ErrorDTO("not found")).build();
    }
    
    /**
     * Adds a new person.
     * @param person The person to add.
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(PersonDTO person)
    {
    	Person existing = personMgr.find(person.getId());
    	if (existing == null)
    	{
    	    Person p = new Person();
    	    p.setId(person.getId());
    	    p.setName(person.getName());
    	    p.setSurname(person.getSurname());
    	    p.setBorn(Date.from(person.getBorn().atStartOfDay()
    	            .atZone(ZoneId.systemDefault())
    	            .toInstant()));
    	    
	    	Person savedPerson = personMgr.save(p);
	    	final URI uri = UriBuilder.fromPath("/people/{resourceServerId}").build(savedPerson.getId());
	    	return Response.created(uri).entity(savedPerson).build();
    	}
    	else
    	{
    		return Response.status(Status.CONFLICT).entity(new ErrorDTO("duplicate id")).build();
    	}
    }
    
    /**
     * Deletes a person.
     * @param id
     * @return
     */
    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id") Long id) 
    {
    	Person p = personMgr.find(id);
    	if (p != null)
    	{
    		personMgr.remove(p);
    		return Response.ok().build();
    	}
    	else
    		return Response.status(Status.NOT_FOUND).entity(new ErrorDTO("not found")).build();
    }
    
    @Path("/{id}/cars")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCarsForPerson(@PathParam("id") Long id) 
    {
    	Person p = personMgr.find(id);
    	if (p != null)
    		return Response.ok(p.getCars()).build();
    	else
    		return Response.status(Status.NOT_FOUND).entity(new ErrorDTO("not found")).build();
    }
   
    @Path("/{id}/cars")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCarToPerson(@PathParam("id") Long personId, CarDTO car) 
    {
    	Person p = personMgr.find(personId);
    	if (p != null)
    	{
    	    Car newCar = new Car();
    	    newCar.setReg(car.getReg());
    	    newCar.setProd(car.getProd());
    	    newCar.setType(car.getType());
  	        	    
    		personMgr.addCar(p, newCar);
    		// return the list of all cars for the person
    		List<CarDTO> allCars = p.getCars().stream().map(c -> new CarDTO(c)).toList();
    		return Response.ok(allCars).build();
    	}
    	else
    		return Response.status(Status.NOT_FOUND).entity(new ErrorDTO("not found")).build();
    }
   

}