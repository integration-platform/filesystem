package org.stavros.interplat.fs.txt.query;

import java.util.ArrayList;
import java.util.List;

public class DefaultTextFileQuery implements TextFileQuery {

	private List<Long> lineNumbers;
	private List<String> regularExpressions;
	private List<String> samples;
	
	public DefaultTextFileQuery() {
		this.lineNumbers = new ArrayList<>();
		this.regularExpressions = new ArrayList<>();
		this.samples = new ArrayList<>();
	}
	
	public List<Long> getLineNumbers() {
		return this.lineNumbers;
	}

	public List<String> getRegularExpressions() {
		return this.regularExpressions;
	}

	@Override
	public List<String> getSamples() {
		return this.samples;
	}

}
