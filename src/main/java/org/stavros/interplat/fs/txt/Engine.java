package org.stavros.interplat.fs.txt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
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
				
				checkLineNumbers(model, line, lineNumber, textFileQuery.getLineNumbers());
				
				checkRegularExpressions(model, line, lineNumber, textFileQuery.getRegularExpressions());
				
				checkSamples(model, line, lineNumber, textFileQuery.getSamples());
			}
		}
		
		return model;
	}
	
	public boolean checkLineNumbers(Model model, String line, Long lineNumber, List<Long> lineNumbers) {
		boolean ret = false;
		if (lineNumbers.contains(lineNumber)) {
			Record record = new Record();
			record.put("lineText", line);
			record.put("lineNumber", lineNumber);
			record.put("position", 0L);
			model.add(record);
			ret = true;
		}
		return ret;
	}
	
	private boolean checkRegularExpressions(Model model, String line, Long lineNumber, List<String> regularExpressions) {
		boolean ret = false;
		if (regularExpressions.size() > 0) {
			for (String regex: regularExpressions) {
				if (line.matches(regex)) {
					Record record = new Record();
					record.put("lineText", line);
					record.put("lineNumber", lineNumber);
					record.put("position", 0L);
					model.add(record);
					ret = true;
					break;
				}
			}
		}
		return ret;
	}
	
	private boolean checkSamples(Model model, String line, Long lineNumber, List<String> samples) {
		boolean ret = false;
		if (samples.size() > 0) {
			for (String sample: samples) {
				int index = line.indexOf(sample);
				if (index >= 0) {
					Record record = new Record();
					record.put("lineText", line);
					record.put("lineNumber", lineNumber);
					record.put("position", Long.valueOf(index));
					model.add(record);
					ret = false;
					break;
				}
			}
		}
		return ret;
	}
}
