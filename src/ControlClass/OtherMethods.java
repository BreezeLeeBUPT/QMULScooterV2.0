package ControlClass;
import java.util.*;
import BoundaryClass.StudentGUI;

public class OtherMethods {
	/**
	 * 
	 * checkEmail:check the validity of email
	 *  
	 * @author Lingfeng Li   
	 * @param s
	 * @return  
	 * @since JDK 1.6
	 */
	static int f = 0;
	public static boolean checkEmail(String s) {
		char[] ch = s.toCharArray();
		for(int i=0;i<ch.length;i++) {
			//only contain '@', '.', letter or digit				
			if(!Character.isLetterOrDigit(ch[i]) && ch[i]!='.' && ch[i]!='@')
				return false;
		}
		for(int i=0;i<ch.length;i++) {  
			//must contain'@', after '@°Æ there must be a '.'
			if(ch[i]=='@') {
				for(int j=i;j<ch.length;j++) {
					if (ch[j]=='.') return true;
				}
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * lightBlink:simulate the process that a LED blink after a user swipe card.
	 *  
	 * @author Lingfeng Li   
	 * @param n  
	 * @since JDK 1.6
	 */
	public static void lightBlink(int n) {
		TimerTask task=new TimerTask(){
			   boolean flag = false;
			   Date date = new Date();
			   long startTime = date.getTime();
			   @Override
			   public void run() {
				  if(flag) {
					  StudentGUI.lightLabels[n].setVisible(false);
					  flag = !flag;
					  f = 1;
				  }
				  else {
					  StudentGUI.lightLabels[n].setVisible(true);
					  flag = !flag;
					  f = 2;
				  }
				  Date newDate = new Date();
				  if(newDate.getTime()-startTime>500) {
					  this.cancel();
					  System.gc();
					  f = 3;
				  }
			   }
			         
		};
			        Timer timer=new Timer();
			        timer.schedule(task, 0,500);  //0±Í ∂“™—”≥Ÿµƒ ±º‰£¨5000÷∏∫¡√Î
			        f = 4;
	}
	
	public static void main(String args[]) {
		System.out.println(checkEmail("635310038@qq.com"));
		System.out.println(checkEmail("434890@qq"));
		System.out.println(checkEmail("635310038qq.com"));
	}
	
	public static int verLightBlink(int n)
    {
            try {
            	lightBlink(n);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            return f;
    }
	
}
