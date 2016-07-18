package org.stavros.interplat.fs;

import java.io.File;
import java.io.FileNotFoundException;

import org.stavros.interplat.model.Model;
import org.stavros.interplat.source.DefaultSource;
import org.stavros.interplat.source.GenericQuery;

public abstract class FileSource extends DefaultSource {
	
	protected FileSource(File file) {
		this.file = file;
	}
	
	protected FileSource(String fileName) {
		setFileName(fileName);
	}
	
	private String fileName;
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileName() {
		return this.fileName;
	}
	
	private File file;
	public void setFile(File file) {
		this.file = file;
	}
	public File getFile() {
		return this.file;
	}
	
	@Override
	public void connect() {
		setFile(new File(getFileName()));
	}
	
	@Override
	public void close() {
	}

}
