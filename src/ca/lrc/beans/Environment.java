package ca.lrc.beans;

import java.io.Serializable;

public class Environment implements Serializable {
	private static final long serialVersionUID = -298772722594044516L;
	private String selection;

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public Environment() {
	}

	public Environment(String selection) {
		this.selection = selection;
	}

}
