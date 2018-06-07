package Operations;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import Parser.*;

public class StatementsOrder {
	
	/*public static void main(String[] args) throws IOException {
		System.out.println(Swap("Hi nice to see you? I'm fine Nice! how are you some other text","nice to see you","how are you"));
	}*/
	
	public static Method ChangeStatementOrder(String code) {
		
		List<Declaration> innerList = Declaration.findDeclaration(code);
	    if(innerList.size()==2) {
	    	code=code.replace(innerList.get(1).toString(), innerList.get(0).toString());
	    	code=code.replaceFirst(Pattern.quote(innerList.get(0).toString()),innerList.get(1).toString());
	    }
	    else if(innerList.size()>2) {
	    	code=code.replace(innerList.get(1).toString(), innerList.get(0).toString());
	    	code=code.replaceFirst(Pattern.quote(innerList.get(0).toString()),innerList.get(1).toString());
	    	code=code.replace(innerList.get(2).toString(), innerList.get(1).toString());
	    	code=code.replaceFirst(Pattern.quote(innerList.get(1).toString()),innerList.get(2).toString());
	    }
		return new Method(code);
	}
	
	public static String Swap(String source, String str1, String str2) {
		
		source=source.replace(str1, str2);
		source=source.replaceFirst(str2, str1);
		
		return source;
	}

}
