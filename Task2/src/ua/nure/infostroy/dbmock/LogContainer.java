package ua.nure.infostroy.dbmock;

import java.util.ArrayList;
import java.util.List;

import ua.nure.infostroy.entity.Log;

public class LogContainer {
private static final List<Log> LOGS = new ArrayList<>();
	
	
	public static List<Log> getLogs() {
		return LOGS;
	}
}
