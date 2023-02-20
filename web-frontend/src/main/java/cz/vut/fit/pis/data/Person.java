package cz.vut.fit.pis.data;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name = "Person")
public class Person
{
    @Id
    private long id;
	private String name;
    private String surname;
    @Temporal(TemporalType.DATE)
    //@JsonbDateFormat("yyyy-MM-dd z")
    private Date born;
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "owner", orphanRemoval = true)
    @JsonbTransient
	private Collection<Car> cars;

    public Person()
    {
        cars = new Vector<Car>();
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
