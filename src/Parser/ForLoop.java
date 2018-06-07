package Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForLoop extends Loop {
	
	public ForLoop() {
		
	}
	
	public ForLoop(String variable, String condition, String step, String body) {
		Variable=variable;
		Condition=condition;
		Step=step;
		Body=body;
	}
	
	public ForLoop(String forText) {
		StatementText=forText;
		setVariable();
		setCondition();
		setStep();
		setBody();
		setLocation();
	}
	
	public void setLocation() {
		//TODO set location
	}
	
	public void setVariable() {
		String variable=StatementText.substring(StatementText.indexOf("(")+1);
		Variable = variable.substring(0, variable.indexOf(";"));
	}
	public void setCondition() {
		String condition = StatementText.substring(StatementText.indexOf(";")+1);
		Condition = condition.substring(0,condition.indexOf(";"));
	}
	
	public void setStep() {
		String step = StatementText.substring(StatementText.indexOf(";")+1);
		step = step.substring(step.indexOf(";")+1);
		Step = step.substring(0,step.indexOf(")"));
	}
	
	public void setBody() {
		Body= StatementText.substring(StatementText.indexOf("{"));
	}
	
	public String toString() {
		return "for("+Variable+";"+Condition+";"+Step+")\n"+Body;
	}
	
	public static List<ForLoop> findForLoop(String code) {
		
		code=code.replace("\n{", "{").replace(") {", "){").replace("for (", "for(");
	    Matcher m = Pattern.compile("for[(].*;.*;.*[)]").matcher(code);
	    List<String> fors=new ArrayList<String>();
	    
	    while (m.find()) {
	    	fors.add(m.group());

	    }
	    
	    for(String item: fors) {
	    	
	    	fors.set(fors.indexOf(item),code.substring(code.indexOf(item)));

	    	
	    }
	    for(String item: fors)
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
		    
		    fors.set(fors.indexOf(item),item.substring(0, n+1));
	}
	    List<ForLoop> forList=new ArrayList<ForLoop>();
	    for(String str : fors) {
	    	ForLoop fl=new ForLoop(str);
	    	forList.add(fl);
	    }
	    	
	    return forList;

	}

	public static List<ForLoop> findForLoop(List<String> codes) {
		
		List<ForLoop> fors=new ArrayList<ForLoop>();
		List<ForLoop> allFors=new ArrayList<ForLoop>();
		
		for(String code : codes) {
			fors=findForLoop(code);
			for(ForLoop floop :fors) {
				allFors.add(floop);
			}
		}
		
		return allFors;
	}
	


}
