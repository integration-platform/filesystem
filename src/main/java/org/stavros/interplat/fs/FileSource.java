package org.stavros.interplat.fs;

import java.io.File;

import org.stavros.interplat.source.GenericSource;

public abstract class FileSource implements GenericSource {
	
	protected FileSource(File file) {
		this.file=file;
	}
	
	private File file;
	public void setFile(File file) {
		this.file = file;
	}
	public File getFile() {
		return this.file;
	}

}
