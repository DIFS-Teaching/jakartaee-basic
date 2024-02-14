package cz.vut.fit.pis.api.dto;

import cz.vut.fit.pis.data.Car;

public class CarDTO
{
    private String reg;
	private String prod;
	private String type;
	private PersonDTO owner;
	
	public CarDTO()
	{
	}
	
	public CarDTO(Car car)
	{
        this.reg = car.getReg();
        this.prod = car.getProd();
        this.type = car.getType();
        this.owner = new PersonDTO(car.getOwner());
	}
	
    public String getProd()
    {
        return prod;
    }
    
    public void setProd(String prod)
    {
        this.prod = prod;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getReg()
    {
        return reg;
    }
    
    public void setReg(String reg)
    {
        this.reg = reg;
    }

    public PersonDTO getOwner()
    {
        return owner;
    }

    public void setOwner(PersonDTO owner)
    {
        this.owner = owner;
    }
    
}
