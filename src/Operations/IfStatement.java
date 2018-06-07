package Operations;

import java.util.ArrayList;
import java.util.List;

import Parser.SwitchCase;

public class IfStatement {
	
	public static Parser.IfStatement SwitchToIf(SwitchCase sc)
	{
		
		SwitchCase sc1=new SwitchCase(sc.SwitchCaseText);
		
		List<String> conditions= new ArrayList<String>();
		List<String> expersions=new ArrayList<String>();
		
		
		for(int i=0 ; i< sc1.CaseExpertions.size(); i++) {
			if(sc1.CaseExpertions.get(i)!=null)
			{
				String caseExp= sc1.Expression+"=="+sc1.CaseExpertions.get(i);
				conditions.add(caseExp);
				expersions.add(sc1.CaseValues.get(i));
			}
		}
		
		Parser.IfStatement ifs=new Parser.IfStatement(conditions,expersions, sc1.DefaultValue);
		return ifs;
	}
}
