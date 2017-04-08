package hack.assembler;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;

public class Parser {

	private BufferedReader reader;
	private String line = null;

	public Parser(Path path) throws IOException {
		reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
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
	
	public String aCommandValue() {
		return line.substring(1, line.length()).trim();
	}
	
	public String comp() {
		int eqInd = line.indexOf("=");
		int semiInd = line.indexOf(";");
		
		eqInd = eqInd < 0 ? 0 : eqInd + 1;
		semiInd = semiInd < 0 ? line.length() : semiInd;
		
		return line.substring(eqInd, semiInd).replaceAll("\\s+","");
	}
	
	public String dest() {
		int eqInd = line.indexOf("=");
		
		eqInd = eqInd < 0 ? 0 : eqInd;
		
		return line.substring(0, eqInd).replaceAll("\\s+","");
	}
	
	public String jump() {
		int semiInd = line.indexOf(";");
		
		semiInd = semiInd < 0 ? line.length() : semiInd + 1;
		
		return line.substring(semiInd, line.length()).replaceAll("\\s+","");
		
	}
}
