package Parser;


public class WhileLoop extends Loop {
	
	public WhileLoop() {
		
	}
	
	public WhileLoop(String variable, String condition, String step, String body) {
		Variable=variable;
		Condition=condition;
		Step=step;
		Body=body;
	}
	
	public WhileLoop(String whileText) {
		StatementText=whileText;
		setCondition();
	}
	
	
	public String toString() {
		String defineVar=Variable +";\n";
		String whileStatement = "while("+Condition+")\n";
		String bodyCode = Body.substring(0,Body.length()-1)+"\n"+Step+";\n}";
		return defineVar + whileStatement + bodyCode;
	}
	
	public static String whileBuilder(String variable, String condition, String step, String body) {
		
		String defineVar=variable +";\n";
		String whileStatement = "while("+condition+")\n";
		String bodyCode = body.substring(0,body.length()-1)+"\n"+step+";\n}";
		return defineVar + whileStatement + bodyCode;
	}
	
	public void setVariable() {
		String variable=StatementText.substring(StatementText.indexOf("(")+1);
		Variable = variable.substring(0, variable.indexOf(";"));
	}
	public void setCondition() {
		Condition = StatementText.substring(StatementText.indexOf("(")+1,StatementText.indexOf(")"));
	}
	
	public void setStep() {
		String step = StatementText.substring(StatementText.indexOf(";")+1);
		step = step.substring(step.indexOf(";")+1);
		Step = step.substring(0,step.indexOf(")"));
	}
	
	public void setBody() {
		Body= StatementText.substring(StatementText.indexOf("{"));
	}
}
