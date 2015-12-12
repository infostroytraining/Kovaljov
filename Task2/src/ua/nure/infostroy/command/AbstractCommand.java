package ua.nure.infostroy.command;

import ua.nure.infostroy.entity.HttpWrapper;

public abstract class AbstractCommand implements Command{
	
	private HttpWrapper httpWrapper;

	public HttpWrapper getHttpWrapper() {
		return httpWrapper;
	}

	public void setHttpWrapper(HttpWrapper httpWrapper) {
		this.httpWrapper = httpWrapper;
	}
	
	
}
