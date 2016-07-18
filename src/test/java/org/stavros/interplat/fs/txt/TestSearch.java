package org.stavros.interplat.fs.txt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.stavros.interplat.fs.txt.query.TextFileLineNumbers;
import org.stavros.interplat.model.Model;
import org.stavros.interplat.source.GenericException;

public class TestSearch {
	
	private String FILE_NAME = "test.txt";
	
	@Before
	public void setUp() {
		File simpleFile = new File(FILE_NAME);
		try (FileWriter fw = new FileWriter(simpleFile);
				BufferedWriter bos = new BufferedWriter(fw);) {
			bos.write("test11, test12, test13\n");
			bos.write("test21, test22, test23\n");
			bos.write("test31, test32, test33\n");
			bos.write("test41, test42, test43\n");
		}
		catch(IOException ioe) {
			assertNull("Exception while creating file", ioe);
		}
	}
	
	@Test
	public void testNumbers() {
		TextFile tf = new TextFile(FILE_NAME);
		TextFileLineNumbers tfln = new TextFileLineNumbers();
		tfln.getLineNumbers().add(2L);
		tfln.getLineNumbers().add(4L);
		try {
			Model model = tf.getData(tfln);
			
			assertEquals(2, model.size());
			
			assertEquals(model.get(0).get("lineNumber"), 2L);
			assertEquals(model.get(0).get("lineText"), "test21, test22, test23");
			assertEquals(model.get(0).get("position"), 0L);
			
			assertEquals(model.get(1).get("lineNumber"), 4L);
			assertEquals(model.get(1).get("lineText"), "test41, test42, test43");
			assertEquals(model.get(1).get("position"), 0L);
		}
		catch(GenericException fnfe) {
			assertNull("Exception while reading file", fnfe);
		}
	}
	
	@Test
	public void testSamples() {
		TextFile tf = new TextFile(FILE_NAME);
		TextFileLineNumbers tfln = new TextFileLineNumbers();
		tfln.getSamples().add("43");
		
		try {
			Model model = tf.getData(tfln);
			
			assertEquals(1, model.size());
			assertEquals(model.get(0).get("lineNumber"), 4L);
			assertEquals(model.get(0).get("lineText"), "test41, test42, test43");
			assertEquals(model.get(0).get("position"), 20L);
		}
		catch(GenericException fnfe) {
			assertNull("Exception while reading file", fnfe);
		}
	}
	
	@Test
	public void testRegExps() {
		TextFile tf = new TextFile(FILE_NAME);
		TextFileLineNumbers tfln = new TextFileLineNumbers();
		tfln.getRegularExpressions().add("(.*)43(.*)");
		
		try {
			Model model = tf.getData(tfln);
			
			assertEquals(1, model.size());
			assertEquals(model.get(0).get("lineNumber"), 4L);
			assertEquals(model.get(0).get("lineText"), "test41, test42, test43");
			assertEquals(model.get(0).get("position"), 0L);
		}
		catch(GenericException fnfe) {
			assertNull("Exception while reading file", fnfe);
		}
	}

}
