package Util;

public class logger {
	
	public void log(String log, String where) {
		switch (where){
		case "file":
			//write in the log file
			return;
		case "db":
			//write in the db
			return;
		case "console":
		System.out.println(log);
		return;
			
		}
	}

}
