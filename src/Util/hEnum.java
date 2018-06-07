package Util;

public class hEnum {
	
	public enum Loops{
		FOR, WHILE, DO_WHILE, FOREACH}
		
		public Loops[] forConvertMap() {
			
			Loops[] l={ Loops.WHILE, Loops.FOREACH, Loops.DO_WHILE};
			return l;
		}
			
	}


