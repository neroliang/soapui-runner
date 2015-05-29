package ca.lrc.beans;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class Upload implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7045952930381893985L;
	private String name;
	private MultipartFile file;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setContent(MultipartFile file) {
		this.file = file;
	}

	public Upload() {
	}

	public Upload(String name, MultipartFile file) {
		this.name = name;
		this.file = file;
	}

}
