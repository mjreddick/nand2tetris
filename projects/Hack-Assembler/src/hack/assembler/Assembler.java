package hack.assembler;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Assembler {
	public static void main(String[] args) throws IOException {
		Path path = Paths.get(args[0]);
		Parser parser1 = new Parser(path);
		Parser parser2 = new Parser(path);
		SymbolTable table = new SymbolTable();
		String line = null;
		int lineNum = 0;

//		Run through the file once and handle all labels
		while ((line = parser1.next()) != null) {
			System.out.println(lineNum + ": " + line);
			System.out.println(parser1.getCommandType());
			if (parser1.getCommandType() == CommandType.Label) {
				table.put(parser1.label(), lineNum);
				System.out.println("label = " + parser1.label() + " : " + lineNum);
			}
			else {
				lineNum++;
			}
		}
		lineNum = 0;
		while ((line = parser2.next()) != null) {
			System.out.println(lineNum + ": " + line);
			System.out.println(parser2.getCommandType());
			if (parser2.getCommandType() == CommandType.A_Command) {
				int value;
				String aValue = parser2.aCommandValuel();
				System.out.println("aValue = " + aValue);
				if (isPositiveInteger(aValue)) {
					value = Integer.parseInt(aValue);
				} else {
					value = table.get(aValue);
				}
//				translate to binary and write to the new file here
				System.out.println("value = " + value);
			}
			lineNum++;
		}
		
		
    }
	
	public static boolean isPositiveInteger(String s) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(Character.digit(s.charAt(i), 10) < 0) return false;
	    }
	    return true;
	}
}
