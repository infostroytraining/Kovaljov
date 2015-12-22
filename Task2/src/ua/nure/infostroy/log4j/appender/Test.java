package ua.nure.infostroy.log4j.appender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {
	private static Logger log = LogManager.getLogger(Test.class);
	public static void main(String[] args) {
		log.info("MyBestMessage");
	}
}
