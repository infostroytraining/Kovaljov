package ua.nure.infostroy.command;

import java.io.IOException;

public class LogoutCommand extends AbstractCommand implements Command{

	@Override
	public void excecute() {
		getHttpWrapper().getRequest().getSession().invalidate();
		try {
			getHttpWrapper().getResponse().sendRedirect(getHttpWrapper().getRequest().getContextPath().replace("/", "") +"/login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
