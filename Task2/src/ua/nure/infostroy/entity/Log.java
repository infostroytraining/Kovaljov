package ua.nure.infostroy.entity;

public class Log {
	private long logId;
	private String logText;

	public long getLogId() {
		return logId;
	}

	public void setLogId(long logId) {
		this.logId = logId;
	}

	public String getLogText() {
		return logText;
	}

	public void setLogText(String logText) {
		this.logText = logText;
	}

	public Log(long logId, String logText) {
		this.logId = logId;
		this.logText = logText;
	}

	public Log() {
	}

}
