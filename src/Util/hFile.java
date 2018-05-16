package Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class hFile {
	
	public static String readFile(String fileName) throws IOException {
		
		BufferedReader br=new BufferedReader(new FileReader(fileName));
		
		try {

			StringBuilder sb= new StringBuilder();
			String line = br.readLine();
			
			while(line != null) {
				sb.append(line);
				sb.append("\n");
				line=br.readLine();
			}
			return sb.toString();	
		}
		finally {
			br.close();
		}
	}
	
	public static void ReplaceInFile(String oldStr, String newStr, String filePath) throws  IOException {
		
	
		writeInNewFile(readFile(filePath).replace(oldStr, newStr),filePath);
		
	}
	
	public static void writeInNewFile(String str, String path) throws IOException {
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter(path+'1', true));
	    writer.append(' ');
	    writer.append(str);
	     
	    writer.close();
	}
	
	public static String findRestOfTheFile(String str, String fileName) throws IOException {
		String inner = readFile(fileName);
		inner = inner.substring(inner.indexOf(str));
		return inner;
		
	}
}
