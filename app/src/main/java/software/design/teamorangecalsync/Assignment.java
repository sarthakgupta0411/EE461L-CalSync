package software.design.teamorangecalsync;

import java.util.Calendar;

public class Assignment extends Event {

	protected Calendar Due_Date;
	protected String Description;

	public Assignment(String Name, Calendar Due_Date, String Des){
		this.Name = Name;
		this.Due_Date = Due_Date;
		this.Description = Des;
	}
	public Calendar getDue_Date() {
		return Due_Date;
	}
	public void setDue_Date(Calendar due_Date) {
		Due_Date = due_Date;
	}
	

}
