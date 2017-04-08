package hack.assembler;

public class Code {
	static String aCommand(int value) {
		String command = Integer.toBinaryString(value);
		return ("0000000000000000" + command).substring(command.length());
	}
	
	static String comp(String c) {
		switch(c) {
		case "0": return "0101010";
		case "1": return "0111111";
		case "-1": return "0111010";
		case "D": return "0001100";
		case "A": return "0110000";
		case "!D": return "0001101";
		case "!A": return "0110001";
		case "-D": return "0001111";
		case "-A": return "0110011";
		case "D+1": return "0011111";
		case "1+D": return "0011111";
		case "A+1": return "0110111";
		case "1+A": return "0110111";
		case "D-1": return "0001110";
		case "A-1": return "0110010";
		case "D+A": return "0000010";
		case "A+D": return "0000010";
		case "D-A": return "0010011";
		case "A-D": return "0000111";
		case "D&A": return "0000000";
		case "A&D": return "0000000";
		case "D|A": return "0010101";
		case "A|D": return "0010101";
		case "M": return "1110000";
		case "!M": return "1110001";
		case "-M": return "1110011";
		case "M+1": return "1110111";
		case "1+M": return "1110111";
		case "M-1": return "1110010";
		case "D+M": return "1000010";
		case "M+D": return "1000010";
		case "D-M": return "1010011";
		case "M-D": return "1000111";
		case "D&M": return "1000000";
		case "M&D": return "1000000";
		case "D|M": return "1010101";
		case "M|D": return "1010101";
		default: return "";
		}
	}
	
	static String dest(String d) {
		String A = d.indexOf("A") < 0 ? "0" : "1";
		String D = d.indexOf("D") < 0 ? "0" : "1";
		String M = d.indexOf("M") < 0 ? "0" : "1";
		
		return A + D + M;
	}
	
	static String jump(String j) {
		switch(j) {
		case "JGT": return "001";
		case "JEQ": return "010";
		case "JGE": return "011";
		case "JLT": return "100";
		case "JNE": return "101";
		case "JLE": return "110";
		case "JMP": return "111";
		default: return "000";
		}
	}
}
