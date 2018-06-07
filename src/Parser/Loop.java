package Parser;

import javax.xml.bind.annotation.*;


@XmlRootElement
public class Loop extends Statement {

	
	@XmlElement
	public void setLoopText(String loopText) {
		this.StatementText=loopText;
	}
	
	public String getLoopText() {
		return StatementText;
	}
	
	public String Variable;
	public String Condition;
	public String Step;
	public String Body;
	
	@XmlElement
	public void setLocation(Location location) {
		this.location=location;
	}
	
	public Location getLocation() {
		return this.location;
	}
}
