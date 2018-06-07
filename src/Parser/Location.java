package Parser;

public class Location {
	
	public Location() {
		
	}
	
	public Location(String filePath, String methodName, int startLineNumber, int endLineNumber) {
		FilePath=filePath;
		StartLineNumber=startLineNumber;
		EndLineNumber=endLineNumber;
		MethodName=methodName;
	}
	
	public String FilePath;
	public String MethodName;
	public int StartLineNumber;
	public int EndLineNumber;
	
	public void swapLocation(Location l1, Location l2) {
		
	}
}
