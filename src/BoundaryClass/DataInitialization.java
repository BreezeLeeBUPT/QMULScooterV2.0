package BoundaryClass;
import ControlClass.ObjectIO;
import EntityClass.InformationSystem;

/**
 * 
 * ClassName: DataInitialization  
 * Function: TODO run this method first to create a fine to record all the information locally  
 * date: 2019/5/27 1:01:53   
 *  
 * @author Group 9  
 * @version   
 * @since JDK 1.6
 */
public class DataInitialization {
	public static void main(String args[]) {
		InformationSystem i = new InformationSystem();
		i.initlizeFreeSpace(); // set all the scooter fields to be free.
		ObjectIO.writeObjectToFile(i);
	}
}
