package TestClass;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import org.junit.jupiter.api.Test;

import ControlClass.CoreMethods;
import EntityClass.InformationSystem;
import EntityClass.User;

class CoreMethodsTest {

	@Test
	void testSwipeCard() {
		CoreMethods c = new CoreMethods();
		InformationSystem system = new InformationSystem();
		JTextArea txtrMessages = new JTextArea();
		JLabel lblUseTime = new JLabel();
		Timer timer = new Timer(0, null);
		JLabel[] photoLabels = new JLabel[8];
		JLabel[] lightLabels = new JLabel[8];
		int a = c.verswipeCard(system,"123@123.com",txtrMessages,lblUseTime,timer,photoLabels,lightLabels);
		assertEquals(2,a);
	}

	@Test
	void testPickup() {
		CoreMethods c = new CoreMethods();
		int a = c.verpickup(1, new InformationSystem(), new User("123@123.com"), 
				new JTextArea(), new JLabel(), new Timer(0, null), new JLabel[8], new JLabel[8]);
		assertEquals(2,a);
	}

}