package org.stavros.interplat.fs.txt;

import java.io.File;

import org.stavros.interplat.fs.FileSource;
import org.stavros.interplat.fs.txt.query.TextFileQuery;
import org.stavros.interplat.model.Model;
import org.stavros.interplat.source.GenericException;
import org.stavros.interplat.source.GenericQuery;

public class TextFile extends FileSource {
	
	public TextFile(File f) {
		super(f);
	}
	
	public TextFile(String fileName) {
		super(fileName);
	}

	public void connect() {
		setFile(new File(getFileName()));
	}

	public Model filter(GenericQuery query) throws GenericException {
		TextFileQuery textFileQuery = (TextFileQuery)query;
		
		Engine engine = new Engine();
		Model model = null;
		try {
			model = engine.process(getFile(), textFileQuery);
		}
		catch(Exception e) {
			throw new GenericException(e);
		}
		
		return model;
	}
}
