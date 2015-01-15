package tcika;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Lenu {
	
	boolean active;
	Tcika_by by;
	int hour;
	int minute;
	int day;//work with calendars
	int month;
	int year;
	String message;
	private final String default_message = "Time!";
	
	public Lenu(Tcika_by by){
		this.active = true;
		this.by = by;
		this.hour = by.time()[0]+1;
		this.minute = 0;
		this.day = by.day();
		this.month = by.month();
		this.year = by.year();
		this.message = default_message;
	}
	
	public Lenu(Tcika_by by, int[] time){
		this(by);
		if(time.length>0)this.minute = time[0];
		if(time.length>1)this.hour = time[1];
		if(time.length>2)this.day = time[2];
		if(time.length>3)this.month = time[3];
		if(time.length>4)this.year = time[4];
	}
	
	public Lenu(Tcika_by by, int[] time, String message){
		this(by,time);
		this.message = message;
	}
	
	public int[] getTime(){
		return new int[]{hour,minute,day,month,year};
	}
	
	public String getTimeAsString(){
		return hour + ":" + minute + ", " + day + "/" + month + "/" + year;
	}
	
	public String toString(){
		return this.getTimeAsString() + "\n" + message;
	}
	
	public boolean test(int[] time){//time should always have five elements: minute, hour, day, month, year
		if(!active)return false;
		//System.out.println("time: "+time[0]+" "+time[1]+" "+time[2]+" "+time[3]+" "+time[4]+"   "+this.getTimeAsString());
		if(time[4]>year){active = false; return true;}
		if(time[4]==year&&time[3]>month){active = false; return true;}
		if(time[4]==year&&time[3]==month&&time[2]>day){active = false; return true;}
		if(time[4]==year&&time[3]==month&&time[2]==day&&time[1]>hour){active = false; return true;}
		if(time[4]==year&&time[3]==month&&time[2]==day&&time[1]==hour&&time[0]>=minute){active = false; return true;}
		return false;
	}

	public void sendMessage() {
		by.tk.beep();
		JOptionPane.showMessageDialog(new JFrame(),
			    "It is: " + getTimeAsString() + "\n" + message,
			    "Time!", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	
	
}
