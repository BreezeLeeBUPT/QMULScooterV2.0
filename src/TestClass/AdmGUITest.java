package TestClass;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import BoundaryClass.AdmGUI;

class AdmGUITest {

	@Test
	void testAdmGUI() {
		AdmGUI a = new AdmGUI();
		assertEquals(2,a.initialize());
	}
	
}
