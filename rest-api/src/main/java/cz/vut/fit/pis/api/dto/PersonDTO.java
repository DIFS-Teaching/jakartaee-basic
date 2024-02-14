package cz.vut.fit.pis.api.dto;

import java.util.Date;

import cz.vut.fit.pis.data.Person;


public class PersonDTO
{
    private long id;
	private String name;
    private String surname;
    private Date born;

    public PersonDTO()
    {
    }

    public PersonDTO(Person person)
    {
        this.id = person.getId();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.born = person.getBorn();
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

    public Date getBorn()
    {
        return born;
    }

    public void setBorn(Date born)
    {
        this.born = born;
    }
    
}
