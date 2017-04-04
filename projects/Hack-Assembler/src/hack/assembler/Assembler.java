package hack.assembler;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Assembler {
	public static void main(String[] args) throws IOException {
		Path path = Paths.get(args[0]);
		Parser parser = new Parser(path);
		String line = null;
		int lineNum = 0;
		while ((line = parser.next()) != null) {
			System.out.println(lineNum + ": " + line);
			System.out.println(parser.getCommandType());
			lineNum++;
		}
    }
}
