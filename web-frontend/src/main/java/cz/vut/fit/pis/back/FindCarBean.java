package cz.vut.fit.pis.back;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import cz.vut.fit.pis.data.*;
import cz.vut.fit.pis.service.CarManager;

@Named
@SessionScoped
public class FindCarBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Car car;
    private String find;
    private boolean found;
    @EJB
    private CarManager carMgr;

    public FindCarBean()
    {
        car = new Car();
        found = false;
    }
    
    public Car getCar()
    {
        return car;
    }

    public void setCar(Car car)
    {
        this.car = car;
    }
    
    public List<Car> autocomplete(Object event) 
    {
        String pref = event.toString();
        return carMgr.findBegining(pref);
    }

    public String getFind()
    {
        return find;
    }

    public void setFind(String find)
    {
        this.find = find;
    }

    public boolean isFound()
    {
        return found;
    }

    public void setFound(boolean found)
    {
        this.found = found;
    }

    public String findCar()
    {
        car = carMgr.find(find);
        found = true;
        return "ok";
    }
    
    public String clearSearch()
    {
        found = false;
        return "ok";
    }

}
