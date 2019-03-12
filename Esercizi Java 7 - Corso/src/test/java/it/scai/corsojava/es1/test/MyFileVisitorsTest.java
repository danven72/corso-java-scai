package it.scai.corsojava.es1.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Test;

import it.scai.corsojava.es1.MyFileVisitors;

public class MyFileVisitorsTest {
	
	private final static String PATH_TEST = "C:\\Users\\danilo.ventura\\formazione\\"
			+ "Corso Java9 - SCAI\\workspace\\Es1-j7-directoryComparator\\src\\test\\filesfortest";
	
	@Test
	public void testVisitFile() throws IOException {
		MyFileVisitors fileVisitors = new MyFileVisitors();
		Path path1 = Paths.get(PATH_TEST).resolve("path1");
		path1.toFile().mkdirs();
		
		for (int i=0; i<4; i++) {
			path1.resolve("file_"+i+".test").toFile().createNewFile();
		}
		
		Files.walkFileTree(path1, fileVisitors);
		assertEquals(4,fileVisitors.getFiles().size());
	}
	
	@After
	public void deleteFiles() {
		String[] filesInPath = Paths.get(PATH_TEST).resolve("path1").toFile().list();
		for (String f: filesInPath) {
			Paths.get(PATH_TEST).resolve("path1").resolve(f).toFile().delete();
		}
		Paths.get(PATH_TEST).resolve("path1").toFile().delete();
		//System.out.println("All file deleted: "+res);
	}
}
