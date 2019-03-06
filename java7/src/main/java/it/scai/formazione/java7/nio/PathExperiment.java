package it.scai.formazione.java7.nio;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class PathExperiment {
	
	public static void main(String[] args) throws IOException {
		FileSystem fileSystem= FileSystems.getDefault();
		System.out.println("Separator: "+fileSystem.getSeparator());
		
		Path p1 = fileSystem.getPath("C:\\Users\\danilo.ventura\\formazione");
		System.out.println("p1.getNameCount: " + p1.getNameCount());
		System.out.println("p1.toURI: " + p1.toUri());
		System.out.println("p1.getRoot: " + p1.getRoot().toString());
		for (int i =0; i<p1.getNameCount();i++) {
			System.out.println("p1.getName("+i+"): " + p1.getName(i));
		}
		
		// Creare e scrive un file in una directory
		if (p1.toFile().exists() && p1.toFile().canWrite()) {
			Path provaFile = p1.resolve("provaFile.txt"); 
			System.out.println("***************** "+provaFile.getFileName().toString());
			//provaFile.toFile().createNewFile();
			//System.out.println(provaFile.toString()+": created correctly");
			byte[] data = "Hello World Java NIO".getBytes();
			try(
					OutputStream out  = new BufferedOutputStream(Files.newOutputStream(provaFile,StandardOpenOption.CREATE, StandardOpenOption.APPEND))
				) {
				out.write(data, 0, data.length);
			}
		}
		
		Path p2 = Paths.get(fileSystem.getSeparator()+"src");
		System.out.println("p2: " + p2.toString());
	}
}
