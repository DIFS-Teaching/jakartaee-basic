package cz.vut.fit.pis.graphql;

import java.time.LocalDate;
import java.time.ZoneId;

import cz.vut.fit.pis.data.Person;


public class PersonDTO
{
    private long id;
	private String name;
    private String surname;
    private LocalDate born;

    public PersonDTO()
    {
    }

    public PersonDTO(Person person)
    {
        this.id = person.getId();
        this.name = person.getName();
        this.surname = person.getSurname();
        // date is converted to local date
        this.born = person.getBorn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public LocalDate getBorn()
    {
        return born;
    }

    public void setBorn(LocalDate born)
    {
        this.born = born;
    }
    
}
