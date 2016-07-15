package org.stavros.interplat.fs.txt;

import java.io.File;

import org.stavros.interplat.fs.FileSource;
import org.stavros.interplat.fs.txt.query.TextFileQuery;
import org.stavros.interplat.model.Model;
import org.stavros.interplat.source.GenericQuery;

public class TextFile extends FileSource {
	
	public TextFile(File f) {
		super(f);
	}

	public void connect() {
		// TODO Auto-generated method stub
		
	}

	public Model getData(GenericQuery query) {
		TextFileQuery textFileQuery = (TextFileQuery)query;
		return null;
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

	

}
