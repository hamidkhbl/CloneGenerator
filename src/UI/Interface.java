package UI;
import Operations.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Parser.*;
import Parser.IfStatement;
import Parser.Loop;
import Util.*;
import Operations.*;
import java.nio.file.Files;


public class Interface {
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("");
		System.out.println("***********************   Semantic Clone Generator   ***********************");
		System.out.println("");
		System.out.println("Enter path for Subjected system: ");
		Scanner scanner = new Scanner(System.in);
		String path ="D:\\JavaExamples\\LearnJava-master\\";//scanner.nextLine();   // 
		System.out.println("Enter a path to store the files:  ");
		String workStationPath="D:\\JavaExamples\\WorkStation\\";//scanner.nextLine();   //*/
		
		
		if(createDirectory(workStationPath)) {
			findMethods(path,workStationPath);
		}
		else
			System.out.println("Directory already exists, enter a new one.");
		
		
		/*Method m=new Method("    public static void main(String[] args) {\r\n" + 
				"        // TODO code application logic here\r\n" + 
				"        Random rnd = new Random();\r\n" + 
				"        Player p1 = new Player(\"Raiden\",GetRandomThrow(rnd.nextInt()));\r\n" + 
				"        Player p2 = new Player(\"Liukang\",GetRandomThrow(rnd.nextInt()));\r\n" + 
				"        Game g = new Game();\r\n" + 
				"        GameResult gr = null;\r\n" + 
				"        int p1wins = 0;\r\n" + 
				"        int p2wins = 0;\r\n" + 
				"        int ties = 0;\r\n" + 
				"        System.out.println(String.format(\"%s\\t%s\\t%s\\t%s\",p1.Name,p2.Name,\"Ties\",\"Result\"));\r\n" + 
				"        for (int i=0;i<100;i++) {\r\n" + 
				"            p1.Throw = Throw.ROCK;//GetRandomThrow(rnd.nextInt());\r\n" + 
				"            p2.Throw = GetRandomThrow(rnd.nextInt());\r\n" + 
				"            gr = g.Play(p1, p2);\r\n" + 
				"            if (gr.WinnerName == p1.Name) p1wins++;\r\n" + 
				"            if (gr.WinnerName == p2.Name) p2wins++;\r\n" + 
				"            if (gr.WinnerName == \"\") ties++;\r\n" + 
				"            System.out.println(String.format(\"%03d\\t%03d\\t%03d\\t%s\",p1wins,p2wins,ties,gr.ResultText));\r\n" + 
				"        }    \r\n" + 
				"    }");
		
		
		//System.out.println(m.ParsMethod());
		System.out.println(StatementsOrder.ChangeStatementOrder(m.toString()));*/
		

	}
	
	public static Boolean createDirectory(String path) {
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
				new File(path+"Methods").mkdirs();
				new File(path+"Clones").mkdirs();
				return true;
			}
			else {
				
				return false;
				}
	}
	
	/**
	 * @param projectPath
	 * @param workStationPath
	 */
	public static void findMethods(String projectPath, String workStationPath) {
		
		System.out.println("Looking for Methods . . . ");
		List<String> files=hFile.getAllFiles(projectPath,".java");
		String fileContent="";

		for(String f:files) {
			File tempFile = new File(f);
			String fileName=tempFile.getName().substring(0,tempFile.getName().indexOf(".") );
			//System.out.println(f);
			try {
				fileContent=hFile.readFile(f);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			List<Method> methods=Method.findMethods(fileContent);
			List<Method> allMethods=methods;
			List<Method> clonedMethods=new ArrayList<Method>();
			
			for(Method m :methods) {
				allMethods.set(allMethods.indexOf(m), new Method(m.StatementText));
			}
			
			for(Method m1 : allMethods) {
				//Convert For loop to While
				List<ForLoop> fors=ForLoop.findForLoop(m1.toString());
				List<SwitchCase> scs=SwitchCase.findSwitchCase(m1.toString());
				if(fors.size()>0 || scs.size()>0) {
					for(ForLoop f1:fors) {
						WhileLoop tempWhile=Operations.Loop.ForToWhile(f1);
						String mtext =m1.toString().replace("\n{", "{").replace(") {", "){").replace("for (", "for(");
						String ftext=f1.toString().replace("\n{", "{").replace(") {", "){").replace("for (", "for(");
						String replaced=mtext.replace(ftext,tempWhile.toString());
						Method method= new Method(replaced);
						clonedMethods.add(method);		
					}
				
				//Convert SwtichCase to If
					
					for(SwitchCase sc:scs) {
						IfStatement ifs=Operations.IfStatement.SwitchToIf(sc);
						String mtext =m1.toString().replace("\n{", "{").replace(") {", "){");
						String sctext=sc.toString().replace("\n{", "{").replace(") {", "){");
						String replaced=mtext.replace(sctext,ifs.toString());
						Method method= new Method(replaced);
						clonedMethods.add(method);		
					}	
					
				}
				
				
			}
			if(clonedMethods.size()>0) {
				for(Method m3 : clonedMethods) {
					hFile.writeInNewFile(m3.toString(), workStationPath+"Clones\\"+fileName+"-"+m3.Name+"Cloned.txt");
				}
			}
				
			for(Method m : allMethods) {
				try {
					hFile.writeInNewFile(m.toString(), workStationPath+"Methods\\"+fileName+"-"+m.Name+".txt");
				}
				catch(Exception e) {
					System.out.println("Error:" +f+" ErrorMessage:  "+e.getMessage());
				}
				
			}
		}
		File file=new File(workStationPath+"Methods");
		File cfile=new File(workStationPath+"Clones");
		System.out.println(file.listFiles().length+" Methods found.");
		System.out.println(cfile.listFiles().length+" Clones Created.");
		
	}
	
	public static void findForLoops(String workStationPath) {
		
		System.out.println("Looking for Loops . . . ");
		List<String> files=hFile.getAllFiles(workStationPath+"Methods",".txt");
		String fileContent="";
		
		for(String f:files) {
			File tempFile = new File(f);
			String fileName=tempFile.getName().substring(0,tempFile.getName().indexOf(".") );
			//System.out.println(f);
			try {
				fileContent=hFile.readFile(f);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			List<Method> methods=new ArrayList<Method>();
			methods.add(new Method(fileContent));
			List<Method> allMethods=methods;
			for(Method m :methods) {
				allMethods.set(allMethods.indexOf(m), new Method(m.StatementText));
			}
			
			for(Method m : allMethods) {
				
				List<ForLoop> forloops=new ArrayList<ForLoop>();
				forloops = ForLoop.findForLoop(m.toString());
				int count=0;
				for(ForLoop forloop:forloops) {
					count++;
				try {
					hFile.writeInNewFile(forloop.toString(), workStationPath+"Fors\\"+fileName+"-"+m.Name+count+".txt");
					//System.out.print(m.Name+",");
				}
				catch(Exception e) {
					System.out.println("Error:" +f+" ErrorMessage:  "+e.getMessage());
				}
				}
				
			}
		}
		File file=new File(workStationPath+"Fors");
		System.out.println(file.listFiles().length+" For loops found.");
	}
	
	public static void createClones(String workStationPath){
		
		System.out.println("Creating Clones ...");
		
		List<String> files=hFile.getAllFiles(workStationPath+"Methods",".txt");
		
		
		List<String> texts= new ArrayList<String>();
		for(String str : files) {
			try {
				texts.add(hFile.readFile(str));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//List<WhileLoop> whiles= new ArrayList<WhileLoop>();
		
		
		List<ForLoop> fors= ForLoop.findForLoop(texts);
		
		List<String> clones=new ArrayList<String>();
		int j=0;
		for(String f : texts) {
		
			clones.add(f.replace(fors.get(j).toString(), Operations.Loop.ForToWhile(fors.get(j)).toString()));
		}
		
		
		
		int i=1;
		for(String c : clones)
		{
			hFile.writeInNewFile(c, workStationPath+"Clones\\"+i+".txt");
			i++;
		}
		
		System.out.println("Clones Created!");
		
	}
	
}


