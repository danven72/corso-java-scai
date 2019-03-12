package it.scai.corsojava.es1.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.scai.corsojava.es1.MyCompareDirectories;

public class MyDirectoryComparatorTest {

	private final static String PATH_TEST = "C:\\Users\\danilo.ventura\\formazione\\"
			+ "Corso Java9 - SCAI\\workspace\\Es1-j7-directoryComparator\\src\\test\\filesfortest";
	
    private Path path1;
    private Path path2;
    
    private MyCompareDirectories myCompareDirectories;
    
    @Before
    public void createAll() throws Exception {
    	myCompareDirectories = new MyCompareDirectories();
    	
    	path1 = Paths.get(PATH_TEST).resolve("path1");
    	path1.toFile().mkdirs();
    	
    	path2 = Paths.get(PATH_TEST).resolve("path2");
    	path2.toFile().mkdirs();
    	
    }
 
    @After
	public void deleteFiles() {
    	cleanAndRemovePath(path1);
    	cleanAndRemovePath(path2);
	}

	private void cleanAndRemovePath(Path aPath) {
		String[] filesInPath = aPath.toFile().list();
		for (String f: filesInPath) {
			aPath.resolve(f).toFile().delete();
		}
		aPath.toFile().delete();
	}
	
	
	@Test
    public void testCompareDirectories() throws IOException {
		path1.resolve("file1.txt").toFile().createNewFile();
		path1.resolve("file2.txt").toFile().createNewFile();
		
		path2.resolve("file1.txt").toFile().createNewFile();
		path2.resolve("file3.txt").toFile().createNewFile();
		
		List<Path>[] result = myCompareDirectories.compareDirectories(path1, path2);
		assertEquals(2, result.length);
		assertEquals(1, result[0].size());
		assertTrue(result[0].contains(path1.resolve("file2.txt").getFileName()));
		
		assertEquals(1, result[1].size());
		assertTrue(result[1].contains(path2.resolve("file3.txt").getFileName()));
    }	
	
	@Test
    public void testCompareDirectoriesMoreComplex() throws IOException {
		path1.resolve("file1.txt").toFile().createNewFile();
		path1.resolve("file2.txt").toFile().createNewFile();
		path1.resolve("file4.txt").toFile().createNewFile();
		path1.resolve("file5.txt").toFile().createNewFile();
		
		path2.resolve("file1.txt").toFile().createNewFile();
		path2.resolve("file3.txt").toFile().createNewFile();
		path2.resolve("file5.txt").toFile().createNewFile();
		path2.resolve("file7.txt").toFile().createNewFile();
		path2.resolve("file2.txt").toFile().createNewFile();
		
		List<Path>[] result = myCompareDirectories.compareDirectories(path1, path2);
		assertEquals(2, result.length);
		assertEquals(1, result[0].size());
		assertTrue(result[0].contains(path1.resolve("file4.txt").getFileName()));
		
		assertEquals(2, result[1].size());
		assertTrue(result[1].contains(path2.resolve("file3.txt").getFileName()));
		assertTrue(result[1].contains(path2.resolve("file7.txt").getFileName()));
    }

	
	

	@Test
    public void testCompareDirectoriesWhenSameFiles() throws IOException {
		path1.resolve("file1.txt").toFile().createNewFile();
		path1.resolve("file2.txt").toFile().createNewFile();
		
		path2.resolve("file1.txt").toFile().createNewFile();
		path2.resolve("file2.txt").toFile().createNewFile();
		
		List<Path>[] result = myCompareDirectories.compareDirectories(path1, path2);
		assertEquals(2, result.length);
		assertEquals(0, result[0].size());
		
		assertEquals(0, result[1].size());
    }


}
