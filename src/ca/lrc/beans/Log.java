package ca.lrc.beans;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Log implements Serializable {
	private static final long serialVersionUID = -1340717811563692970L;
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Log() {
	}

	public Log(String content) {
		this.content = content;
	}
}
