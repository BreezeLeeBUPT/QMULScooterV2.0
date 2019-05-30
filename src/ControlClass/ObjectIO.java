package ControlClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectIO {
	/**
	 * 
	 * writeObjectToFile: serialize a object into byte stream, then save it into a local file
	 *  
	 * @author Lingfeng Li   
	 * @param obj  
	 * @return 
	 * @return 
	 * @return 
	 * @since JDK 1.6
	 */
	public static int writeObjectToFile(Object obj)
    {
        File file =new File("data.dat");
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut=new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
            objOut.close();
           System.out.println("write object success!");
        } catch (IOException e) {
        	
            System.out.println("write object failed");
            e.printStackTrace();
        }
       
    return 1 ;
}
	/**
	 * 
	 * readObjectFromFile:read a object from a local file 
	 *  
	 * @author Lingfeng Li   
	 * @param object 
	 * @return a general object which needed to be converted to specific object
	 * @since JDK 1.6
	 */
	public static Object readObjectFromFile()
    {
        Object temp=null;
        File file =new File("data.dat");
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            temp=objIn.readObject();
            objIn.close();
            System.out.println("read object success!");
        } catch (IOException e) {
            System.out.println("read object failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }
	
	
	/**
	 * 
	 * ImplicitReadObjectFromFile:The only difference of this method is not printing "read object success" on screen
	 *  Cause it's sometimes annoying
	 *  
	 * @author Lingfeng Li   
	 * @return  
	 * @since JDK 1.6
	 */
	public static Object ImplicitReadObjectFromFile()   
    {
        Object temp=null;
        File file =new File("data.dat");
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            temp=objIn.readObject();
            objIn.close();
        } catch (IOException e) {
            System.out.println("read object failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
