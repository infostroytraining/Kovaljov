package ua.nure.infostroy.command;

import java.io.IOException;

import org.apache.log4j.Logger;

public class LogoutCommand extends AbstractCommand implements Command{
	private Logger log = Logger.getLogger(LogoutCommand.class);
	@Override
	public void excecute() {
		getHttpWrapper().getRequest().getSession().invalidate();
		try {
			getHttpWrapper().getResponse().sendRedirect(getHttpWrapper().getRequest().getContextPath().replace("/", "") +"/login.jsp");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
	
}
