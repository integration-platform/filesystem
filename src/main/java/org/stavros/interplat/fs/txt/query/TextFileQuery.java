package org.stavros.interplat.fs.txt.query;

import java.util.List;

import org.stavros.interplat.source.GenericQuery;

public interface TextFileQuery extends GenericQuery {
	
	List<Long> getLineNumbers();
	
	List<String> getRegularExpressions();

}
