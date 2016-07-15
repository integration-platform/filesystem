package org.stavros.interplat.fs.txt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.stavros.interplat.fs.txt.query.TextFileQuery;
import org.stavros.interplat.model.Model;
import org.stavros.interplat.model.Record;

public class Engine {
	
	public Model process(File f, TextFileQuery textFileQuery) throws FileNotFoundException {
		Model model = new Model();
		
		long lineNumber = 0;
		try (Scanner scanner = new Scanner(f);) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				lineNumber++;
				
				if (textFileQuery.getLineNumbers().contains(lineNumber)) {
					Record record = new Record();
					record.put(String.valueOf(lineNumber), line);
					model.add(record);
				}
				else if (textFileQuery.getRegularExpressions().size() > 0) {
					for (String regex: textFileQuery.getRegularExpressions()) {
						if (line.matches(regex)) {
							Record record = new Record();
							record.put(String.valueOf(lineNumber), line);
							model.add(record);
						}
					}
				}
			}
		}
		
		return model;
	}

}
