package ua.nure.infostroy.command;

public class LogCommand extends AbstractCommand{

	@Override
	public void excecute() {
		String logMessage = getHttpWrapper().getRequest().getParameter("logEvent");
		System.out.println(logMessage);
	}

}
