package UI;
import java.io.IOException;

import Parser.Declaration;
import Parser.Method;
import Util.hFile;



public class Interface {

	public static void main(String[] args) throws IOException {
		
		//System.out.println(Loop.ConvertLoops(Loop.findLoops("D:\\JavaExamples\\fibo.java"),"D:\\JavaExamples\\fibo.java"));
		
		System.out.println(Method.findMethods("D:\\JavaExamples\\Fibo.java"));
		//System.out.println(hFile.findRestOfTheFile("internal", "D:\\JavaExamples\\Fibo.java"));
		System.out.println(Declaration.findDeclaration("internal static void main(String[] args){	String s1 = \"Computer Science\";\r\n" + 
				"		int x = 307;\r\n" + 
				"		String s2= s1 + \" \" + x;\r\n" + 
				"		String s3 = s2.substring(10,17);\r\n" + 
				"		String s4 = \"is fun\";\r\n" + 
				"		String s5 = s2 + s4;\r\n" + 
				"		\r\n" + 
				"		System.out.println(\"s1: \" + s1);\r\n" + 
				"		System.out.println(\"s2: \" + s2);\r\n" + 
				"		System.out.println(\"s3: \" + s3);\r\n" + 
				"		System.out.println(\"s4: \" + s4);\r\n" + 
				"		System.out.println(\"s5: \" + s5);\r\n" + 
				"		\r\n" + 
				"		//showing effect of precedence\r\n" + 
				"		\r\n" + 
				"		x = 3;\r\n" + 
				"		int y = 5;\r\n" + 
				"		String s6 = x + y + \"total\";\r\n" + 
				"		String s7 = \"total \" + x + y;\r\n" + 
				"		String s8 = \" \" + x + y + \"total\";\r\n" + 
				"		System.out.println(\"s6: \" + s6);\r\n" + 
				"		System.out.println(\"s7: \" + s7);\r\n" + 
				"		System.out.println(\"s8: \" + s8);\r\n" + 
				"	}"));

	}

}
