/**
 * 
 */
package cz.vut.fit.pis.api;

import java.util.List;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import cz.vut.fit.pis.data.Car;
import cz.vut.fit.pis.service.CarManager;

@Path("/cars")
public class Cars 
{
	@Inject
	private CarManager carMgr;
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public Cars() 
    {
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getJson() throws NamingException 
    {
    	return carMgr.findAll();
    }

    /**
     * PUT method for updating or creating an instance of Car
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(List<Car> content) 
    {
    }


}
