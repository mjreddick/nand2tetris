package hack.assembler;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Assembler {
	public static void main(String[] args) {
		Path path = Paths.get(args[0]);
		try (BufferedReader reader = Files.newBufferedReader(path)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
    }
}
