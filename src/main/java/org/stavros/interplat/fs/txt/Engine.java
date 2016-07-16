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
		
		Long lineNumber = 0L;
		try (Scanner scanner = new Scanner(f);) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				lineNumber++;
				
				if (textFileQuery.getLineNumbers().contains(lineNumber)) {
					Record record = new Record();
					record.put("lineText", line);
					record.put("lineNumber", lineNumber);
					record.put("position", 0L);
					model.add(record);
				}
				else if (textFileQuery.getRegularExpressions().size() > 0) {
					for (String regex: textFileQuery.getRegularExpressions()) {
						if (line.matches(regex)) {
							Record record = new Record();
							record.put("lineText", line);
							record.put("lineNumber", lineNumber);
							record.put("position", 0L);
							model.add(record);
						}
					}
				}
				else if (textFileQuery.getSamples().size() > 0) {
					for (String sample: textFileQuery.getSamples()) {
						int index = line.indexOf(sample);
						if (index >= 0) {
							Record record = new Record();
							record.put("lineText", line);
							record.put("lineNumber", lineNumber);
							record.put("position", Long.valueOf(index));
							model.add(record);
						}
					}
				}
			}
		}
		
		return model;
	}

}
