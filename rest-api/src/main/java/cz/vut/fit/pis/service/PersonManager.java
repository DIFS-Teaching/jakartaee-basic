/**
 * 
 */
package cz.vut.fit.pis.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
    public void save(Person p)
    {
    	em.merge(p);
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
    	return em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

}
