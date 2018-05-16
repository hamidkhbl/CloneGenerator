package Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Declaration extends Statement {
	
	public String Type;
	public String Name;
	
	public static List<String> findDeclaration(String code) {
		
	    Matcher m = Pattern.compile("(int|String) .*=.*;").matcher(code);
	    
	    List<String> declerations=new ArrayList<String>();
	    
	    while (m.find()) {
	    	declerations.add(m.group());

	    }
		return declerations;

	}
	

}
