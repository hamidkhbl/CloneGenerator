package Util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

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
	
	public static List<String> readFiles(List<String> fileNames){
		
		List<String> innerList=new ArrayList<String>();
		
		for(String str:fileNames)
			try {
				innerList.add(readFile(str));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return innerList;
	}
	
	public static void replaceInFile(String oldStr, String newStr, String filePath) throws  IOException {
		
	
		writeInNewFile(readFile(filePath).replace(oldStr, newStr),filePath);
		
	}
	
	public static void writeInNewFile(List<String> str, String path) throws IOException {
		
        PrintWriter writer = new PrintWriter(new FileWriter(path));
		for(String item : str ) {
			try (
			        BufferedReader reader = new BufferedReader(new StringReader(item));

			    ) {
			        reader.lines().forEach(line -> writer.println(line));
			    }
		}
		writer.close();
	
	}
	
	public static void writeInNewFile(String str, String path){
		
		
		try (
		        BufferedReader reader = new BufferedReader(new StringReader(str));
		        PrintWriter writer = new PrintWriter(new FileWriter(path));
		    ) {
		        reader.lines().forEach(line -> writer.println(line));
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static String findRestOfTheFile(String str, String fileName) throws IOException {
		String inner = readFile(fileName);
		inner = inner.substring(inner.indexOf(str));
		return inner;
		
	}

	
	

	public static List<String> getAllFiles(String path, String extention){
		
		List<String> innerList = new ArrayList<String>();
		
		Stack<File> stack=new Stack<File>();
		
		File fi=new File(path);
		stack.push(fi);
		
		while(!stack.isEmpty()) {
			File child=stack.pop();
			if(child.isDirectory()) {
				for(File f:child.listFiles()) stack.push(f);
				
			}
			else if (child.isFile()&&child.getPath().endsWith(extention)) {
				innerList.add(child.getPath());	
			}
		}
		
		
		return innerList;
		
	}
	
	
}
