package Parser;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Util.hCode;
import Util.hFile;

public class Method extends Statement {
	
	public Method() {
		
	}
	
	public Method(String methodText) {
		StatementText=methodText;	
		setName();
		setNemberOfLines();
		ParsMethod();
	}

 	public void setNemberOfLines() {
		
		String[] lines = StatementText.split("\r\n|\r|\n");
		NumberOfLines=lines.length;
	}
	public String Signature;
	public String Name;
	public void setName() {
		int spaceCount=0;
		try {
		String name=StatementText.substring(StatementText.indexOf(" ")+1,StatementText.indexOf("("));
		
		for(char c : name.toCharArray()) {
			
			if(c==' ') {
				spaceCount++;
			}
		}
		switch(spaceCount) {
		case 0: Name=name;
			return;
		case 1: Name=name.substring(name.indexOf(" ")+1);
			return;
			
		case 2: String name22=name.substring(name.indexOf(" ")+1);
				Name=name22.substring(name22.indexOf(" ")+1);
			return;
			
		case 3: String name32=name.substring(name.indexOf(" ")+1);
				String name33=name32.substring(name32.indexOf(" ")+1);
				Name=name33.substring(name33.indexOf(" ")+1);
				return;
			
		default:
			Name=name;
		}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
	public String Modifier;
	public String ReturnType;
	
	public void ParsMethod() {
		
		
		//find variables declarations
		/*List<Declaration> vars= Declaration.findDeclaration(StatementText);
		Statements.addAll(vars);
				
		//find for loops
		List<ForLoop> fors = ForLoop.findForLoop(StatementText);
		Statements.addAll(fors);
		
		//find switch case
		List<SwitchCase> scs = SwitchCase.findSwitchCase(StatementText);
		Statements.addAll(scs);	*/
	}
	
	public static List<Method> findMethods(String code) {
		
		String code1 = hCode.removeComments(code);
		
	    Matcher m = Pattern.compile("(?!.*(=)).*((public|internal|private) (static|)(.*)[(].*[)]())").matcher(code1);
	    
	    Stack<Character> stack=new Stack<Character>();
	    char c='1';
	    int p=1 , n=0;
	    
	    List<String> methods=new ArrayList<String>();
	    
	    while (m.find()) {
	    	methods.add(m.group());

	    }
	    
	    for(String item: methods) {
	    	methods.set(methods.indexOf(item),code1.substring(code1.indexOf(item)));
	
	    }
	    try {
	    for(String item: methods)
	    {
	    	
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
	    }catch(Exception e) {
    		//System.out.println("Error");
    	}
	    		
	    	
	    
	    
	    List<Method> methodList=new ArrayList<Method>();
	    for(String str : methods) {
	    	Method m1=new Method(str.trim());
	    	methodList.add(m1);
	    }
	    return methodList;

}
	
	public static List<Method> findMethods(List<String> codes){
		

		List<Method> methods=new ArrayList<Method>();
		List<Method> allMethods=new ArrayList<Method>();
		
		for(String code : codes) {
			methods=findMethods(code);
			for(Method floop :methods) {
				allMethods.add(floop);
			}
		}
		
		return allMethods;
	}
	
	public String toString() {
		return StatementText;
	}
}
