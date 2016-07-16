package org.stavros.interplat.fs.txt;

import java.io.File;
import java.io.FileNotFoundException;

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

	public Model getData(GenericQuery query) throws FileNotFoundException {
		TextFileQuery textFileQuery = (TextFileQuery)query;
		
		Engine engine = new Engine();
		Model model = engine.process(getFile(), textFileQuery);
		
		return model;
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

	

}
