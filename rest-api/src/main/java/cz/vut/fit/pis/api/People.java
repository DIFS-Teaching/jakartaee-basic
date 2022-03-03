package cz.vut.fit.pis.api;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import cz.vut.fit.pis.data.ErrorDTO;
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

    /**
     * Default constructor. 
     */
    public People() 
    {
    }

    @PostConstruct
    public void init()
    {
    }
    
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getJson() throws NamingException 
    {
    	return personMgr.findAll();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonSingle(@PathParam("id") String idString) throws NamingException 
    {
    	long id = Long.valueOf(idString);
    	Person p = personMgr.find(id);
    	if (p != null)
    		return Response.ok(p).build();
    	else
    		return Response.status(Status.NOT_FOUND).entity(new ErrorDTO("not found")).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response putJson(List<Person> content) 
    {
    	return Response.status(Response.Status.NOT_IMPLEMENTED).entity(new ErrorDTO("Not implemented")).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String postJson(Person person)
    {
    	personMgr.save(person);
    	return "ok";
    }

}