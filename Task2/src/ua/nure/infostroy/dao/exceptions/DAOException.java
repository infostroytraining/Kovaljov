package ua.nure.infostroy.dao.exceptions;

public class DAOException extends Throwable{
	private static final long serialVersionUID = 1L;
	Throwable caught;
	String message;

	public DAOException() {
		super();
	}

	public DAOException(String mes) {
		super();
		message = mes;
	}

	public DAOException(String mes, Throwable caught) {
		super();
		message = mes;
		this.caught = caught;
	}

	public DAOException(Throwable caught) {
		super();
		this.caught = caught;
	}

	public Throwable getCaught() {
		return caught;
	}

	public String getMessage() {
		return message;
	}
} 
