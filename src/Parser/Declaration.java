package Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Declaration extends Statement {
	
	public String Type;
	public String Name;
	
	public Declaration(String code) {
		
		StatementText=code;
	}

	public static List<Declaration> findDeclaration(String code) {
		
		
	    Matcher m = Pattern.compile("(.*) .*=.*;").matcher(code);
	    
	    List<Declaration> declerations=new ArrayList<Declaration>();
	    
	    while (m.find()) {
	    	declerations.add(new Declaration(m.group()));
	    }
		return declerations;

	}
	
	public String toString() {
		return StatementText;
	}
}
