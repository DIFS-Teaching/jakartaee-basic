package cz.vut.fit.pis.back;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("idConverter")
public class IdConverter implements Converter<Long> 
{

	@Override
	public Long getAsObject(FacesContext arg0, UIComponent component, String value)
	{
		if (value.length() == 11 && value.charAt(6) == '/')
		{
			try {
				long a = Long.parseLong(value.substring(0, 6));
				long b = Long.parseLong(value.substring(7));
				return a * 10000 + b;
			} catch (NumberFormatException e) {
				throw new ConverterException("Invalid ID format");
			}
		}
		else
			throw new ConverterException("Invalid ID format");
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent component, Long value)
	{
		return (value / 10000) + "/" + (value % 10000);
	}

}
