package Operations;
import Util.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.regex.*;


public class Loop {

	public static String findLoops(String filePath) throws IOException {
		
		String code=hFile.readFile(filePath);
	    Matcher m = Pattern.compile("for[(][\\w\\W]*;[\\w\\W]*;[\\w\\W]*[)][{][\\w\\W]*[}]").matcher(code);
	    String floop="";
	    while (m.find()) {
	        floop+=m.group();

	    }
	    Stack<Character> stack=new Stack<Character>();
	    char c;
	    int p=1 , n=0;
	    for(int i=0;i<floop.length();i++) {
	    	c=floop.charAt(i);
	    	
	    	if(c=='{') {
	    		stack.push(c);
	    		//k++;
	    		p=0;
	    	}
	    	if(c=='}') {
	    		stack.pop();
	    		//p=1;
	    		
	    	}
	    	
	    	if(stack.isEmpty()&&p==0) {//p==0 && k==0) {
	    		n=i;
	    		p=1;
	    		break;
	    	}
	    	
	    }

	    return floop.substring(0, n+1);
	}
	
	public static String ConvertLoops(String input, String filePath) throws IOException {
		
			String inner=input;
			String Copy=input;

			String variable=inner.substring(inner.indexOf("(")+1);
			variable = variable.substring(0, variable.indexOf(";"));
			
			String condition = inner.substring(inner.indexOf(";")+1);
			condition = condition.substring(0,condition.indexOf(";"));
			
			String codeBlock= inner.substring(inner.indexOf("{"));
			//codeBlock=codeBlock.substring(0,codeBlock.indexOf("}")+1);
			
			String step = inner.substring(inner.indexOf(";")+1);
			step = step.substring(step.indexOf(";")+1);
			step = step.substring(0,step.indexOf(")"));
			
			codeBlock=codeBlock.substring(0,codeBlock.length()-1)+"\n"+step+";\n}";
			
			try {
				@SuppressWarnings("resource")
				PrintWriter writer=new PrintWriter("C:\\Clone\\Clone.txt");
				writer.println(variable +";");
				writer.println("while("+condition+")");
				writer.println(codeBlock);
				writer.close();
				
			}
			catch(IOException e) {
				
			}
	
			hFile.ReplaceInFile(Copy, variable +";\nwhile("+condition+")\n"+codeBlock, filePath);
			return variable +";\nwhile("+condition+")\n"+codeBlock;
		}
		
	
}



