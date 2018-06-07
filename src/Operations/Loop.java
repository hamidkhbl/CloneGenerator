package Operations;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.regex.*;

import Parser.*;


public class Loop {
	
	
	public static WhileLoop ForToWhile(ForLoop input) {
		
			return new WhileLoop(input.Variable, input.Condition, input.Step, input.Body);
		}
	
	

}



