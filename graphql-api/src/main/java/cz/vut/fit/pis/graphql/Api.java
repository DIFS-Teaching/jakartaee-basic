/**
 * 
 */
package cz.vut.fit.pis.graphql;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.GraphQLException;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

import cz.vut.fit.pis.data.Car;
import cz.vut.fit.pis.data.Person;
import cz.vut.fit.pis.service.CarManager;
import cz.vut.fit.pis.service.PersonManager;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

/**
 * GraphQL API definition.
 * See http://localhost:9080/graphql-api/graphql/schema.graphql
 * 
 * @author burgetr
 */
@GraphQLApi
@RequestScoped
public class Api
{
	@Inject
	private PersonManager personMgr; 
	@Inject
	private CarManager carMgr; 

	@Query
	@Description("Gets the complete list of people")
	public List<PersonDTO> getPeople()
	{
    	return personMgr.findAll().stream().map(p -> new PersonDTO(p)).toList();
	}
	
	@Query
	@Description("Gets the complete list of cars")
	public List<CarDTO> getCars()
	{
    	return carMgr.findAll().stream().map(car -> new CarDTO(car)).toList();
	}
	
	@Query
	@Description("Gets a person by their ID")
	public PersonDTO getPersonById(long id)
	{
    	Person p = personMgr.find(id);
    	return new PersonDTO(p);
	}
	
	@Description("List of cars for a person")
	public List<CarDTO> getCars(@Source PersonDTO src)
	{
		Person p = personMgr.find(src.getId());
		return p.getCars().stream().map(car -> new CarDTO(car)).toList();
	}
	
	@Description("The number of cars for a person")
	public Integer getCarCount(@Source PersonDTO src)
	{
		Person p = personMgr.find(src.getId());
		return p.getCars().size();
	}
	
	@Mutation
	@Description("Updates a person or creates a new one")
	public PersonDTO updatePerson(PersonDTO src) throws GraphQLException
	{
    	Person p = personMgr.find(src.getId());
    	if (p == null)
    	{
    		p = new Person();
    		p.setId(src.getId());
    	}
    	p.setName(src.getName());
		p.setSurname(src.getSurname());
        p.setBorn(Date.from(src.getBorn().atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()));
        personMgr.save(p);
		return new PersonDTO(p);
	}

	@Mutation
	@Description("Deletes a person")
	public PersonDTO deletePerson(long id) throws GraphQLException
	{
		Person p = personMgr.find(id);
		if (p != null)
			personMgr.remove(p);
		else
			throw new GraphQLException("No such person");
		return new PersonDTO(p);
	}

	@Mutation
	@Description("Adds a car to a person")
	public PersonDTO addCar(long personId, CarDTO car) throws GraphQLException
	{
		Person p = personMgr.find(personId);
		if (p != null)
		{
    	    Car newCar = new Car();
    	    newCar.setReg(car.getReg());
    	    newCar.setProd(car.getProd());
    	    newCar.setType(car.getType());
			personMgr.addCar(p, newCar);
		}
		else
			throw new GraphQLException("No such person");
		return new PersonDTO(p);
	}

}
