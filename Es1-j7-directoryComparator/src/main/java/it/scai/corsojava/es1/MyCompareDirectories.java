package it.scai.corsojava.es1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MyCompareDirectories {
	
	public List<Path>[] compareDirectories(Path path1, Path path2) throws IOException {
		MyFileVisitors fileVisitor1 = new MyFileVisitors();
		Files.walkFileTree(path1, fileVisitor1);
		List<Path> fileListInPath1 = fileVisitor1.getFiles();
		
		MyFileVisitors fileVisitor2 = new MyFileVisitors();
		Files.walkFileTree(path2, fileVisitor2);
		List<Path> fileListInPath2 = fileVisitor2.getFiles();
		
		List<Path> notInPath2 = removeSameFile(fileListInPath1, fileListInPath2);
		List<Path> notInPath1 = removeSameFile(fileListInPath2, fileListInPath1);
		
		return new List[] {notInPath2, notInPath1};
	}
	
	private List<Path> removeSameFile(List<Path> fileList1, List<Path> fileList2) {
		List<Path> result = new ArrayList<>(fileList1);
		for (Path p : fileList1) {
			if (fileList2.contains(p)) {
				result.remove(p);
			}
		}
		return result;
	}
}
