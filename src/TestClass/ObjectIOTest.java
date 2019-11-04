package TestClass;

import static org.junit.Assert.assertEquals;
import java.io.*;
import org.junit.jupiter.api.Test;

import ControlClass.ObjectIO;



class ObjectIOTest {
	
	private ObjectInputStream objIn;
	@Test
	void testWriteObjectToFile() {
		//ObjectIO d = new ObjectIO();
		assertEquals(1,ObjectIO.writeObjectToFile(null));
	}
	
	@Test
	void testReadObjectFromFile() throws IOException, ClassNotFoundException {
		new ObjectIO();
		 File file =new File("data.dat");
		 FileInputStream in = new FileInputStream(file);
		 objIn = new ObjectInputStream(in);
		 Object temp= objIn.readObject();
		 objIn.close();
		assertEquals(temp,ObjectIO.readObjectFromFile());
	}
	
	@Test
	void testImplicitReadObjectFromFile() throws IOException, ClassNotFoundException {
		new ObjectIO();
		 File file =new File("data.dat");
		 FileInputStream in = new FileInputStream(file);
		 objIn = new ObjectInputStream(in);
		 Object temp=objIn.readObject();
		 objIn.close();
		assertEquals(temp,ObjectIO.ImplicitReadObjectFromFile());
	}
}
