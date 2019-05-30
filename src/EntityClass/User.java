package EntityClass;
import java.io.*;
/**
 * 
 * ClassName: User  
 * Function: TODO save the information of a single user. 
 * date: 2019ƒÍ5‘¬27»’ œ¬ŒÁ12:54:22 
 *  
 * @author Lingfeng Li  
 * @version   
 * @since JDK 1.6
 */
public class User implements Serializable{
	private static final long serialVersionUID = -3286564461647015343L; //Ensure the safety of Object Serializable
	private String email;
	private boolean forbiddened=false;
	private boolean riding=false;
	private long lastStartTime=0;
	private long monthUseTime=0;
	//private Timer timer ;
	public User(String email) {
		this.email=email;
	}
	public void setForbid(boolean status) {
		this.forbiddened=status;
	}
	public boolean isForbiddened() {
		return this.forbiddened;
	}
	public String getEmail() {
		return this.email;
	}
	public boolean isRiding() {
		return this.riding;
	}
	public void setRiding(boolean riding) {
		this.riding=riding;
	}
	public String toString() {
		return "User Information:"+'\n'	
				+"Email:"+this.getEmail()+'\n'
				+"isRiding:"+this.isRiding()+'\n'
				+"isForbiddened:"+this.isForbiddened()+'\n'
				+"MonthUseTime:"+this.monthUseTime+" s";
	}
	
	public long getLastStartTime() {
		return this.lastStartTime;
	}
	public void setLastStartTime(long t) {
		this.lastStartTime = t;
	}
	
	public long getMonthUseTime() {
		return this.monthUseTime;
	}
	public void setMonthUseTime(long t) {
		this.monthUseTime = t;
	}
	
//	public void startTimer(JLabel label) {
		
	   /**
		Date date = new Date();
        lastStartTime = date.getTime();
        TimerTask task = new TimerTask() {
        	public void run() {
        		long timeInSeconds = (date.getTime()-lastStartTime)/1000;
                label.setText("You have ride:" + String.valueOf(timeInSeconds) + "seconds.");  
        	}
        };
        long delay = 0;
        long intervalPeriod = 1000;
        timer = new Timer();
        timer.scheduleAtFixedRate(task, delay, intervalPeriod);
        */
		/*
		Date date = new Date();
        lastStartTime = date.getTime();
        //…Ë÷√Timer∂® ±∆˜£¨≤¢∆Ù∂Ø  
        timer = new Timer(1000, new ActionListener() {
        	public void actionPerformed(ActionEvent e) {    
                Date date = new Date();    
                long timeInSeconds = (date.getTime()-lastStartTime)/1000;  
                label.setText("You have ride:" + String.valueOf(timeInSeconds) + "seconds.");  
            }  
        });  
        timer.start();
        System.out.println(timer.hashCode());
        System.out.println(this.hashCode());
        
	}
	
	public void endTimer(JLabel label) {
		System.out.println(timer.hashCode());
		System.out.println(this.hashCode());
		label.setText("time will appear here");
	}
	*/
	
}
