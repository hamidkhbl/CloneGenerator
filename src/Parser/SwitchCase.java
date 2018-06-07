package Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwitchCase extends Statement {
	
	public SwitchCase() {
		
	}
	
	public SwitchCase(String switchCaseText) {
		SwitchCaseText=switchCaseText;
		setExpersion();
		setCaseBlock();
		setCaseExpersions();
		setCaseValue();
		setDefaultValue();
	}
	
	public String SwitchCaseText;
	public String Expression;
	public void setExpersion() {
		Expression=SwitchCaseText.substring(SwitchCaseText.indexOf("(")+1, SwitchCaseText.indexOf(")"));
	}
	
	public String CaseBlock;
	public void setCaseBlock() {
		CaseBlock=SwitchCaseText.substring(SwitchCaseText.indexOf("{")+1, SwitchCaseText.indexOf("}"));
	}
	
	public List<String> CaseExpertions;
	public void setCaseExpersions() {
		List<String> innerList=new ArrayList<String>();
		String[] words = CaseBlock.split("case");
		for (int i = 0; i < words.length; i++) {
			
		    words[i] = words[i].trim().replace("\n", "");
		    String w=words[i];
			if(w.contains(":")&&w!=null) {
				innerList.add(w.substring(0, w.indexOf(":")));
			}
			
			CaseExpertions=innerList;
		}
	}
	
	public List<String> CaseValues;
	public void setCaseValue() {
		List<String> innerList=new ArrayList<String>();
		String[] words = CaseBlock.split("case");
		for (int i = 0; i < words.length; i++) {
			
		    words[i] = words[i].trim().replace("\n", "");
		    String w=words[i];
			if(w.contains(":")&&w!=null) {
				innerList.add(w.substring(w.indexOf(":")+1, w.indexOf("break;")).trim());
			}
			CaseValues=innerList;
		}
		
	}

	public String DefaultExpersion;
	public void setDefaultExpertion() {
		
	}
	
	public String DefaultValue;
	public void setDefaultValue() {
		List<String> innerList=new ArrayList<String>();
		String[] words = CaseBlock.split("default");
		for (int i = 0; i < words.length; i++) {
			
		    words[i] = words[i].trim().replace("\n", "");
		    String w=words[i];
			if(w.contains(":")&&w!=null) {
				innerList.add(w.substring(w.indexOf(":")+1, w.indexOf(";")).trim());
			}
		}
		DefaultValue=innerList.get(0);
	}

	public static List<SwitchCase> findSwitchCase(String code){
		
		Matcher m = Pattern.compile("switch[(].*[)]").matcher(code);
	    List<String> scs=new ArrayList<String>();
	    
	    while (m.find()) {
	    	scs.add(m.group());

	    }
	    
	    for(String item: scs) {
	    	
	    	scs.set(scs.indexOf(item),code.substring(code.indexOf(item)));	
	    }
	    for(String item: scs)
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
		    
		    scs.set(scs.indexOf(item),item.substring(0, n+1));
	}
	    List<SwitchCase> ifList=new ArrayList<SwitchCase>();
	    for(String str : scs) {
	    	SwitchCase fl=new SwitchCase(str);
	    	ifList.add(fl);
	    }
	    	
	    return ifList;
		
	}

	public static List<SwitchCase> findSwitchCase(List<String> code){
		
		List<SwitchCase> innerList=new ArrayList<SwitchCase>();
		List<SwitchCase> allSC=new ArrayList<SwitchCase>();
		
		for(String str:code) {
			innerList=findSwitchCase(str);
			for(SwitchCase sc:innerList) {
				allSC.add(sc);
			}
		}
		return allSC;
	}
	
	
	
	public String toString() {
		
		return "switch("+Expression+"){"+CaseBlock+"\n}";
	}
}
