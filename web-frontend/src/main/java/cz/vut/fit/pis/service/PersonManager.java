/**
 * 
 */
package cz.vut.fit.pis.service;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import cz.vut.fit.pis.data.Person;


/**
 * Person manager EJB
 * @author burgetr
 */
@Stateless
public class PersonManager 
{
    @PersistenceContext
    private EntityManager em;

    public PersonManager() 
    {
    }
    
    public void save(Person p)
    {
    	em.merge(p);
    }
	
    public void remove(Person p)
    {
    	em.remove(em.merge(p));
    }
    
    public Person find(long id)
    {
    	return em.find(Person.class, id);
    }
    
    public List<Person> findAll()
    {
    	return em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

}
