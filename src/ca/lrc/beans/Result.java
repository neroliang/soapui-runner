package ca.lrc.beans;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Result implements Serializable {
	private static final long serialVersionUID = 835075094502273267L;
	private String testCaseName;
	private boolean successFlag;
	private String logName;

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String name) {
		this.testCaseName = name;
	}

	public boolean getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public Result() {
	}

	public Result(String name, boolean successFlag, String logName) {
		this.testCaseName = name;
		this.successFlag = successFlag;
		this.logName = logName;
	}

	
}
