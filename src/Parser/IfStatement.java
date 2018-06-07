package Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IfStatement extends Statement{
	
	public IfStatement() {
		
	}
	
	public IfStatement(List<String> conditions, List<String> codeBlocks, String elseBlock) {
		Conditions=conditions;
		CodeBlocks=codeBlocks;
		ElseBlock=elseBlock;
	}
	
	public List<String> Conditions;
	public void setCondition() {
	}
	
	public List<String> CodeBlocks;
	public void setCodeBlock() {
		//CodeBlock=StatementText.substring(StatementText.indexOf("{")+1,StatementText.indexOf("}"));
	}
	
	public String ElseBlock;
	
	
	public IfStatement(String condition, String codeBlock ) {
		//Condition=condition;
		//CodeBlock=codeBlock;
	}
	
	public IfStatement(String ifText) {
		StatementText=ifText;
		setCondition();
		setCodeBlock();
	}
	
	public static List<IfStatement> findIf(String code) {
		
		Matcher m = Pattern.compile("if[(].*[)]").matcher(code);
	    List<String> ifs=new ArrayList<String>();
	    
	    while (m.find()) {
	    	ifs.add(m.group());

	    }
	    
	    for(String item: ifs) {
	    	
	    	ifs.set(ifs.indexOf(item),code.substring(code.indexOf(item)));	
	    }
	    for(String item: ifs)
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
		    
		    ifs.set(ifs.indexOf(item),item.substring(0, n+1));
	}
	    List<IfStatement> ifList=new ArrayList<IfStatement>();
	    for(String str : ifs) {
	    	IfStatement fl=new IfStatement(str);
	    	ifList.add(fl);
	    }
	    	
	    return ifList;
	}
	
	public String toString() {
		
		String ifs="";
		
		for(int i=0 ; i<=Conditions.size();i++)
		{
			if(i==0) {
				ifs="if("+Conditions.get(i)+"){\n"+CodeBlocks.get(i)+"\n}";
			}
			else if(i<Conditions.size()) {
				ifs+="\nelse if("+Conditions.get(i)+"){\n"+CodeBlocks.get(i)+"\n}";
			}
			else {
				ifs+="\nelse {\n"+ElseBlock+"\n}";
			}
		}
		
		
		return ifs;
	}
}
