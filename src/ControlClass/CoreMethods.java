package ControlClass;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;

import BoundaryClass.AdmGUI;
import BoundaryClass.StudentGUI;
import EntityClass.InformationSystem;
import EntityClass.User;
import ControlClass.OtherMethods;

import java.util.Date;

public class CoreMethods {
	//if one ride time is grater than 30s, forbidden user to ride until fine is paid.
	private static int overTime = 30;  
	static int f = 0;
	/**
	 * 
	 * swipeCard: simulate the process that user swipe card on a senser.
	 * according to the current status of user(riding or not riding), determine to park scooter or pick up scooter
	 *  
	 * @author Group 9   
	 * @param system
	 * @param username
	 * @param timer  
	 * @since JDK 1.6
	 */
	public static void swipeCard(InformationSystem system, String userInput,
			JTextArea txtrMessages,JLabel lblUseTime,Timer timer,
			JLabel[] photoLabels,JLabel[] lightLabels){
			//loc is 0,1,2,3; while 0 stands for not chosen an location yet, 
			//loc-- is used to match the index of 2-dimensional array 	
			if(!OtherMethods.checkEmail(userInput)) { //input valid
				txtrMessages.setText("Message:" + '\n'+
						"Your input is NOT Valid!!" + '\n' +" Please input again!");
				f = 1;
				return;
			}
			if(system.getCurrentLoc()==0) {
				txtrMessages.setText("Please choose a location first!");
				f = 2;
				return;
			}
			ArrayList<User> list = system.getlist();
			for(User user:list) {
				if(user.getEmail().equals(userInput)) {
					//user name is found,check if the user is forbiddened
					if(user.isForbiddened()) {
						txtrMessages.setText("You have unpaid fine!" + '\n' 
									+"Please contact administrator!");
						f = 3;
						return;
					}
					//paid(valid user)
					else {  
						if(user.isRiding()==true) { //park
							park(system.getCurrentLoc()-1,system,user,txtrMessages,lblUseTime,timer,photoLabels,lightLabels);
							f = 4;
							return;
						}
						else { //pick up
							Date date = new Date();
							user.setLastStartTime(date.getTime());
							System.out.println(date.getTime());
							//TimerTest t = new TimerTest();
							//t.start(lblUseTime, user.getLastStartTime());
							
							pickup(system.getCurrentLoc()-1,system,user,txtrMessages,lblUseTime,timer,photoLabels,lightLabels);
							f = 5;
							return;
						}
					}
				}
			}
			//User Not Found
			txtrMessages.setText("User Not Found!");
			f = 6;
	}
	
	/**
	 * 
	 * pickup: check the freespaces and if success, pick up a car, restart timer and blink image
	 * @author Group 9
	 * @param loc
	 * @param system
	 * @param user
	 * @param timer  
	 * @since JDK 1.6
	 */
	public static void pickup(int loc,InformationSystem system,User user,JTextArea txtrMessages,JLabel lblUseTime,Timer timer,JLabel[] photoLabels,JLabel[] lightLabels) {
		system.setCurrentLoc(0); //clear cache
		boolean[][] freespace = system.getFreeSpace();
		for(int i=0;i<8;i++) {
			if(freespace[loc][i]) {
				freespace[loc][i]=!freespace[loc][i];
				StudentGUI.photoLabels[i].setVisible(false);   //scooter image disappear
				OtherMethods.lightBlink(i); //blink light
				StudentGUI.txtrMessages.setText("Pick up car success!");
				//Timer
				Date date = new Date();
				timer.restart();
				//set Flag
				user.setRiding(true); // why this is valid??
				//glistening
				ObjectIO.writeObjectToFile(system);
				return;
			}
		}
		StudentGUI.txtrMessages.setText("Pick up car failed, not enough cars");
	}
	
	/**
	 * 
	 * park:check the freespaces and if success, park a car, stop timer, record time and blink image 
	 *  
	 * @author Group 9   
	 * @param loc
	 * @param system
	 * @param user
	 * @param timer  
	 * @since JDK 1.6
	 */
	
	public static void park(int loc,InformationSystem system,User user,JTextArea txtrMessages,JLabel lblUseTime,Timer timer,JLabel[] photoLabels,JLabel[] lightLabels) {
		system.setCurrentLoc(0);  //clear cache
		boolean[][] freespace = system.getFreeSpace();
		for(int i=7;i>=0;i--) {
			if(!freespace[loc][i]) {
				freespace[loc][i]=!freespace[loc][i];
				StudentGUI.photoLabels[i].setVisible(true);    //scooter image appear
				OtherMethods.lightBlink(i);   //blink light
				user.setRiding(false);
				//Timer stop 
				timer.stop();
				Date date = new Date();
				long t = (date.getTime()-user.getLastStartTime())/1000;
				System.out.println(date.getTime());
				System.out.println(user.getLastStartTime());
				StudentGUI.lblUseTime.setText("Ride finish.Total time is: "+ t + " seconds");
				//If this day is the first day of the month, clear the monthUseTime
				Calendar cal = Calendar.getInstance();
				if(cal.get(Calendar.DAY_OF_MONTH)==1) 
				{
					user.setMonthUseTime(0);				
				}
				user.setMonthUseTime(user.getMonthUseTime()+t);
				if(t>=overTime) {
					StudentGUI.txtrMessages.setText("park car success! "
							+'\n'+ "However, since you ride more "+'\n'
									+ "than 30s, you are forbiddened now."+'\n'+
							"Please pay the fine.");
					user.setForbid(true);
				}
				else StudentGUI.txtrMessages.setText("park car success!");
				ObjectIO.writeObjectToFile(system);
				return;
			}
		}
		StudentGUI.txtrMessages.setText("park car failed, not enough freespaces available");
	}
	
	/**
	 * 
	 * viewFreeSpace:check the freespace available, print output both in text and image, 
	 *  
	 * @author Group 9   
	 * @param loc
	 * @param system  
	 * @since JDK 1.6
	 */
	public static void viewFreeSpace(int loc,InformationSystem system) {
		boolean[][] freespace = system.getFreeSpace();
		loc--;
		int freeAmount = 0;
		for(int i=0;i<=7;i++) {
			if(freespace[loc][i]) {
				freeAmount++;
				StudentGUI.photoLabels[i].setVisible(true);
			}
			else {
				StudentGUI.photoLabels[i].setVisible(false);
			}
		}
		if(loc==0) StudentGUI.txtrMessages.setText("Message:"+  '\n' +
				 "Location A has " + freeAmount + " free spaces," + '\n' +
				(8-freeAmount) + " already using.");
		else if(loc==1) StudentGUI.txtrMessages.setText("Message:"+  '\n' +
				 "Location B has " + freeAmount + " free spaces,"+'\n' +
		 (8-freeAmount) + " already using.");
		else if(loc==2) StudentGUI.txtrMessages.setText("Message:" + '\n'
				+ "Location C has " + freeAmount + " free spaces," +'\n' +
		(8-freeAmount) + " already using.");	
		else StudentGUI.txtrMessages.setText("Unexpected error"); //supposed not to executed here
	}
	
	/**
	 * 
	 * addUser:used by the administrator to add a new user
	 *  
	 * @author Group 9   
	 * @param username  
	 * @since JDK 1.6
	 */
	public static void addUser(String username) {
		InformationSystem system = (InformationSystem)ObjectIO.readObjectFromFile();
		if(OtherMethods.checkEmail(username)) {
			ArrayList<User> list = system.getlist();
			for(User user:list) {
				if(user.getEmail().equals(username)) {
					AdmGUI.txtrInformation.setText("User already exist!");
					return;
				}
			}			
			User user = new User(username);
			system.add(user);
			ObjectIO.writeObjectToFile(system);
			AdmGUI.txtrInformation.setText("Add user success!");
		}
		else {
			//invalid input
			AdmGUI.txtrInformation.setText("Invalid Email Address!Please Input again!");
		}
	}
	
	/**
	 * 
	 * removeUser:used by the administrator to remove a old user.
	 *  
	 * @author Group 9   
	 * @param username  
	 * @since JDK 1.6
	 */
	public static void removeUser(String username) {
		InformationSystem system = (InformationSystem)ObjectIO.readObjectFromFile();
		if(OtherMethods.checkEmail(username)) { 
			//valid input
			ArrayList<User> list = system.getlist();
			for(User user:list) {
				if(user.getEmail().equals(username)) {
					system.remove(user);
					ObjectIO.writeObjectToFile(system);
					AdmGUI.txtrInformation.setText("Remove user success!");
					return;
				}
			}
			//User Not Found
			AdmGUI.txtrInformation.setText("User Not Found!");
		}
		else {
			AdmGUI.txtrInformation.setText("Invalid Email Address!Please Input again!");
		}
	}
	
	/**
	 * 
	 * checkUser:used by administrator to check the current status of a specific user
	 *  
	 * @author Group 9   
	 * @param username  
	 * @since JDK 1.6
	 */
	public static void checkUser(String username) {
		InformationSystem system = (InformationSystem)ObjectIO.readObjectFromFile();
		if(OtherMethods.checkEmail(username)) { 
			//valid input
			ArrayList<User> list = system.getlist();
			for(User user:list) {
				if(user.getEmail().equals(username)) {
					AdmGUI.txtrInformation.setText(user.toString());
					return;
				}
			}
			//User Not Found
			AdmGUI.txtrInformation.setText("User Not Found!");
		}
		else {
			AdmGUI.txtrInformation.setText("Invalid Email Address!Please Input again!");
		}
	}
	
	/**
	 * 
	 * payfine: used by administrator to pay the fine for a specific user
	 *  
	 * @author Group 9  
	 * @param username  
	 * @since JDK 1.6
	 */
	public static void payfine(String username) {
		InformationSystem system = (InformationSystem)ObjectIO.readObjectFromFile();
		if(OtherMethods.checkEmail(username)) { 
			//valid input
			ArrayList<User> list = system.getlist();
			for(User user:list) {
				if(user.getEmail().equals(username)) {
					user.setForbid(false);
					ObjectIO.writeObjectToFile(system);
					AdmGUI.txtrInformation.setText("User:"+user.getEmail()+"\nhas successfully paid the fine!");
					return;
				}
			}
			//User Not Found
			AdmGUI.txtrInformation.setText("User Not Found!");
		}
		else {
			AdmGUI.txtrInformation.setText("Invalid Email Address!Please Input again!");
		}
	}
	
	/**
	 * 
	 * clearAllFines: used by the administrator to clear the fines of all users.  
	 *  
	 * @author Lingfeng Li     
	 * @since JDK 1.6
	 */
	public static void clearAllFines(JTextArea txtrInformation) {
		InformationSystem system = (InformationSystem)ObjectIO.readObjectFromFile();
		ArrayList<User> list = system.getlist();
		for(User user:list) {
			user.setForbid(false);
		}
		ObjectIO.writeObjectToFile(system);
		AdmGUI.txtrInformation.setText("All the fines have been cleared!");
	}
	
	/**
	 * 
	 * searchUnpaidUsers:used by the administrator to print the list of users who has not paid the fine.  
	 *  
	 * @author Group 9     
	 * @since JDK 1.6
	 */
	public static void searchUnpaidUsers(JTextArea txtrInformation) {
		InformationSystem system = (InformationSystem)ObjectIO.readObjectFromFile();
		ArrayList<User> list = system.getlist();
		String s = "Unpaied users' emails:" +'\n';
		for(User user:list) {
			if(user.isForbiddened()) {
				s = s + user.getEmail() +'\n';
			}
		}
		AdmGUI.txtrInformation.setText(s);
	}
	
	public int verpickup(int loc,InformationSystem system,User user,JTextArea txtrMessages,JLabel lblUseTime,Timer timer,JLabel[] photoLabels,JLabel[] lightLabels){
        try {
        	pickup(loc,system,user,txtrMessages,lblUseTime,timer,photoLabels,lightLabels);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return f;
}
	
public int verswipeCard(InformationSystem system, String userInput,
		JTextArea txtrMessages,JLabel lblUseTime,Timer timer,
		JLabel[] photoLabels,JLabel[] lightLabels){
        try {
        	swipeCard(system,userInput,txtrMessages,lblUseTime,timer,photoLabels,lightLabels);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return f;
}
	
}
