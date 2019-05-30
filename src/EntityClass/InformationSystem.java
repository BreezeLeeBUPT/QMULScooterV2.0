package EntityClass;
import java.io.Serializable;
import java.util.*;
/**
 * 
 * ClassName: InformationSystem   
 * Function: TODO save the information of the entire system, including the information of all the users. 
 * date: 2019年5月27日 下午12:55:02  
 *  
 * @author Group 9  
 * @version 1.0
 * @since JDK 1.6
 */
public class InformationSystem implements Serializable{
	private static final long serialVersionUID = -3286564461647015344L; //Ensure the safety of Object Serializable
	private ArrayList<User> list=new ArrayList<User>();
	private boolean[][] freespace=new boolean[3][8]; // 3 locations to save cars, each loaction 8 free space
	private int SystemCurrentLoc;
	
	public void add(User user) {
		list.add(user);
	}
	
	public void remove(User user) {
		list.remove(user);
	}
	
	public ArrayList<User> getlist() {
		return this.list;
	}
	
	public void initlizeFreeSpace() {
		for(int i=0;i<freespace.length;i++)
			for(int j=0;j<freespace[0].length;j++)
				freespace[i][j]=true;
	}
	
	public boolean[][] getFreeSpace(){
		return freespace;
	}
	
	public int getCurrentLoc() {
		return this.SystemCurrentLoc;
	}
	
	public void setCurrentLoc(int loc) {
		this.SystemCurrentLoc = loc;
	}
}
