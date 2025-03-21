package cz.vut.fit.pis.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.eclipse.microprofile.graphql.Description;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "Person")
@NamedQueries({
    @NamedQuery(name="Person.findAll", query="SELECT p FROM Person p"),
    @NamedQuery(name="Person.findByName",
                query="SELECT p FROM Person p WHERE p.name = :name")
})
@Description("Person data including cars")
public class Person
{
    @Id
    private long id;
	private String name;
    private String surname;
    @Temporal(TemporalType.DATE)
    private Date born;
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "owner", orphanRemoval = true)
	private Collection<Car> cars;

    public Person()
    {
        cars = new ArrayList<>();
    }
    
    public Collection<Car> getCars()
    {
        return cars;
    }

    public void setCars(Collection<Car> cars)
    {
        this.cars = cars;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * @return the surname
     */
    public String getSurname()
    {
        return surname;
    }
    
    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname)
    {
        this.surname = surname;
    }
    
    /**
     * @return the rc
     */
    public long getId()
    {
        return id;
    }
    
    /**
     * @param rc the rc to set
     */
    public void setId(long id)
    {
        this.id = id;
    }
    
    /**
     * @return the born
     */
    public Date getBorn()
    {
        return born;
    }
    
    /**
     * @param born the born to set
     */
    public void setBorn(Date born)
    {
        this.born = born;
    }
    
    @Override
    public String toString()
    {
        return "Person: " + name + " " + surname + "(" + cars.size() + " cars)";
    }
    
}
