package ca.lrc.beans;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7045952930381893985L;
	private MultipartFile multiPartFile;

	public MultipartFile getMultiPartFile() {
		return multiPartFile;
	}

	public void setMultiPartFile(MultipartFile multiPartFile) {
		this.multiPartFile = multiPartFile;
	}

	public FileUpload() {
	}

	public FileUpload(MultipartFile multiPartFile) {
		this.multiPartFile = multiPartFile;
	}
}
