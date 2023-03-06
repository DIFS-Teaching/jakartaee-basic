/**
 * 
 */
package cz.vut.fit.pis.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import cz.vut.fit.pis.data.Person;


/**
 * Person manager EJB
 * @author burgetr
 */
@ApplicationScoped
public class PersonManager 
{
    @PersistenceContext
    private EntityManager em;

    public PersonManager() 
    {
    }
    
    @Transactional
    public Person save(Person p)
    {
    	return em.merge(p);
    }
	
    @Transactional
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
    	return em.createNamedQuery("Person.findAll", Person.class).getResultList();
    }

}
