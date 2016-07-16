package org.stavros.interplat.fs.txt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
import org.stavros.interplat.fs.txt.query.TextFileLineNumbers;
import org.stavros.interplat.model.Model;

public class TestSearch {
	
	@Test
	public void test() {
		File f = new File("test.txt");
		try (FileWriter fw = new FileWriter(f);
				BufferedWriter bos = new BufferedWriter(fw);) {
			bos.write("test11, test12, test13\n");
			bos.write("test21, test22, test23\n");
			bos.write("test31, test32, test33\n");
			bos.write("test41, test42, test43\n");
		}
		catch(IOException ioe) {
			assertNull("Exception while creating file", ioe);
		}
		
		TextFile tf = new TextFile(f);
		TextFileLineNumbers tfln = new TextFileLineNumbers();
		tfln.getLineNumbers().add(2L);
		tfln.getLineNumbers().add(4L);
		try {
			Model model = tf.getData(tfln);
			
			assertEquals(model.size(), 2);
			assertNull(model.get(0).get("0"));
			assertNull(model.get(0).get("1"));
			assertEquals(model.get(0).get("2"), "test21, test22, test23");
			assertNull(model.get(0).get("3"));
			assertEquals(model.get(1).get("4"), "test41, test42, test43");
		}
		catch(FileNotFoundException fnfe) {
			assertNull("Exception while reading file", fnfe);
		}
	}

}
