package it.scai.corsojava.es1;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class MyFileVisitors  extends SimpleFileVisitor<Path>{
    
	private final List<Path> files = new ArrayList<>();

    
    public MyFileVisitors() {
    }
    
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
        //System.out.println("visit file called");
        files.add(file.getFileName());
    	return FileVisitResult.CONTINUE;
    }

    public List<Path> getFiles() {
    	return files;
    }
}
