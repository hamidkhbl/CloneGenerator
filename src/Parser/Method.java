package Parser;

import java.util.List;
import java.util.Stack;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Util.hFile;

public class Method extends Statement {
	
	public String Modifier;
	public String ReturnType;
	
	public static List<String> findMethods(String filePath) throws IOException {
		
		String code=hFile.readFile(filePath);
	    Matcher m = Pattern.compile("(public|internal|private) (static|)(.*)[(].*[)]").matcher(code);
	    
	    List<String> methods=new ArrayList<String>();
	    
	    while (m.find()) {
	    	methods.add(m.group());

	    }
	    
	    for(String item: methods) {
	    	
	    	methods.set(methods.indexOf(item),hFile.findRestOfTheFile(item, "D:\\JavaExamples\\Fibo.java"));

	    	
	    }
	    for(String item: methods)
	    {
		    Stack<Character> stack=new Stack<Character>();
		    char c;
		    int p=1 , n=0;
		    for(int i=0;i<item.length();i++) {
		    	c=item.charAt(i);
		    	
		    	if(c=='{') {
		    		stack.push(c);
		    		p=0;
		    	}
		    	if(c=='}') {
		    		stack.pop();
		    	}
		    	
		    	if(stack.isEmpty()&&p==0) {
		    		n=i;
		    		p=1;
		    		break;
		    	}
		    }
		    
		    methods.set(methods.indexOf(item),item.substring(0, n+1));
	}
	    return methods;

}
	
}
