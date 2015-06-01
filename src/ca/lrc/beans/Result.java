package ca.lrc.beans;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Result implements Serializable {
	private static final long serialVersionUID = 835075094502273267L;
	private String name;
	private boolean successFlag;
	private String reasonForFailing;
	private String logName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}

	public String getReasonForFailing() {
		return reasonForFailing;
	}

	public void setReasonForFailing(String reasonForFailing) {
		this.reasonForFailing = reasonForFailing;
	}

	public String getLogName() {
		return logName;
	}

	public void setLogName(String logName) {
		this.logName = logName;
	}

	public Result() {
	}

	public Result(String name, boolean successFlag, String reasonForFailing,
			String logName) {
		this.name = name;
		this.successFlag = successFlag;
		this.reasonForFailing = reasonForFailing;
		this.logName = logName;
	}

}
