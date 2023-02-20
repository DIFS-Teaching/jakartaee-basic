package cz.vut.fit.pis.back;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;


@Named
@SessionScoped
public class AuthenticationBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private boolean authorized;
    private String login;
    private String password;
    
    public AuthenticationBean()
    {
        authorized = false;
    }

    public boolean isAuthorized()
    {
        return authorized;
    }

    public void setAuthorized(boolean authorized)
    {
        this.authorized = authorized;
    }
    
    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String actionLogout()
    {
        authorized = false;
        return "logout";
    }
    
    public String actionLogin()
    {
        if (login.equals("jan") && password.equals("novak"))
        {
            authorized = true;
            return "login";
        }
        else
        {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Invalid login"));
            return "failed";
        }
    }
}
