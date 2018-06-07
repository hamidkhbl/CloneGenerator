package Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Statement {

	public int NumberOfLines;
	public int EndLine;
	public String StatementText;
	public Location location;
	
	public List<Statement> Statements;
	
	public Statement() {
		
	}
	
	public Statement(String statementText) {
		StatementText=statementText;
	}
	
	
	
}
