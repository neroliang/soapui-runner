package ca.lrc.beans;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Result implements Serializable {
	private static final long serialVersionUID = 835075094502273267L;
	private String name;
	private boolean successFlag;

	@Embedded
	private Log log;

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

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public Result() {
	}

	public Result(String name, boolean successFlag, Log log) {
		this.name = name;
		this.successFlag = successFlag;
		this.log = log;
	}

}
