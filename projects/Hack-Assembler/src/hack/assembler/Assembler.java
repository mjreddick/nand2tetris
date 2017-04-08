package hack.assembler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Assembler {
	public static void main(String[] args) throws IOException {
		Path path = Paths.get(args[0]);
		Path writePath = Paths.get(args[0].substring(0, args[0].lastIndexOf(".")) + ".hack");
		Parser parser1 = new Parser(path);
		Parser parser2 = new Parser(path);
		SymbolTable table = new SymbolTable();
		int lineNum = 0;
		BufferedWriter writer = Files.newBufferedWriter(writePath, StandardCharsets.UTF_8);

//		Run through the file once and handle all labels
		while ((parser1.next()) != null) {

			if (parser1.getCommandType() == CommandType.Label) {
				table.put(parser1.label(), lineNum);
			}
			else {
				lineNum++;
			}
		}
		while ((parser2.next()) != null) {
			if (parser2.getCommandType() == CommandType.A_Command) {
				int value;
				String aValue = parser2.aCommandValue();

				if (isPositiveInteger(aValue)) {
					value = Integer.parseInt(aValue);
				} else {
					value = table.get(aValue);
				}
				String aCommand = Code.aCommand(value);
				writer.write(aCommand, 0, aCommand.length());
				writer.newLine();
			}
			
			if (parser2.getCommandType() == CommandType.C_Command) {
				String cCommand = "111";

				String comp = parser2.comp();
				String dest = parser2.dest();
				String jump = parser2.jump();

				String compCode = Code.comp(comp);
				String destCode = Code.dest(dest);
				String jumpCode = Code.jump(jump);
				
				cCommand += compCode + destCode + jumpCode;
				writer.write(cCommand, 0, cCommand.length());
				writer.newLine();
				
				
			}
		}
		
		writer.flush();
		writer.close();
		
		
    }
	
	public static boolean isPositiveInteger(String s) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(Character.digit(s.charAt(i), 10) < 0) return false;
	    }
	    return true;
	}
}
