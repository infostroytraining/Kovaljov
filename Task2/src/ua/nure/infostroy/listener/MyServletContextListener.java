package ua.nure.infostroy.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import ua.nure.infostroy.dao.implimentation.DAOFactory;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    public MyServletContextListener() {
    }
    private static final String STORAGE_INIT_PARAMETER = "storage";
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	ServletContext context = arg0.getServletContext();
		String storageMode = context.getInitParameter(STORAGE_INIT_PARAMETER);
		DAOFactory factory= DAOFactory.getDAOFactory(storageMode);
		context.setAttribute("factory", factory);
    }
	
}
