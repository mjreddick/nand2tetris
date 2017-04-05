package hack.assembler;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Parser {

	private BufferedReader reader;
	private String line = null;

	public Parser(Path path) throws IOException {
		reader = Files.newBufferedReader(path);
	}
	
	public String next() throws IOException {
		while ((line = reader.readLine()) != null) {
	    	int commentIndex = line.indexOf("//");
	    	if (commentIndex >= 0) {
	    		line = line.substring(0, commentIndex);
	    	}
	    	line = line.trim();
	    	if (line.isEmpty()) {
	    		continue;
	    	}
	        return line;
	    }
		return null;
	}
	
	public CommandType getCommandType() {
		if (line.startsWith("(")) return CommandType.Label;
		if (line.startsWith("@")) return CommandType.A_Command;
		return CommandType.C_Command;
	
	}
	
	public String label() {
//		Assumes the line is already correctly formatted as (myLabel)
		return line.substring(1, line.length() - 1).trim();
	}
	
	public String aCommandValuel() {
		return line.substring(1, line.length()).trim();
	}
}
