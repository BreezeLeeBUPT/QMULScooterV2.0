package TestClass;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ControlClass.OtherMethods;

class OtherMethodsTest {

	@Test
	void testCheckEmail() {
		OtherMethods d = new OtherMethods();
		String s = "12@";
		assertEquals(false,d.checkEmail(s));
		assertEquals(true,d.checkEmail("123@123.com"));
	}

	@Test
	void testLightBlink() {
		OtherMethods d = new OtherMethods();
		int n = 1;
		assertEquals(4,d.verLightBlink(n));
	}
}
