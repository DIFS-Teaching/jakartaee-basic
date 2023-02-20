/**
 * 
 */
package cz.vut.fit.pis.api;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

import cz.vut.fit.pis.data.Car;
import cz.vut.fit.pis.service.CarManager;

@Stateless
@Path("/cars")
public class Cars 
{
	@EJB
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
    public List<Car> getJson() 
    {
    	return carMgr.findAll();
    }

    /**
     * PUT method for updating or creating an instance of People
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(List<Car> content) 
    {
    }


}
