package ca.lrc.beans;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Service implements Serializable {
	private static final long serialVersionUID = 835075094502273267L;
	private String name;
	private boolean isUp;

	public String getName() {
		return name;
	}

	public void setName(String serviceName) {
		this.name = serviceName;
	}

	public boolean getIsUp() {
		return isUp;
	}

	public void setIsUp(boolean isUp) {
		this.isUp = isUp;
	}

	public Service() {
	}

	public Service(String serviceName, boolean isUp) {
		this.name = serviceName;
		this.isUp = isUp;
	}

}
