package org.stavros.interplat.fs.txt.query;

import java.util.ArrayList;
import java.util.List;

public class DefaultTextFileQuery implements TextFileQuery {

	private List<Long> lineNumbers;
	private List<String> regularExpressions;
	
	public DefaultTextFileQuery() {
		this.lineNumbers = new ArrayList<>();
		this.regularExpressions = new ArrayList<>();
	}
	
	public List<Long> getLineNumbers() {
		return this.lineNumbers;
	}

	public List<String> getRegularExpressions() {
		return this.regularExpressions;
	}

}
