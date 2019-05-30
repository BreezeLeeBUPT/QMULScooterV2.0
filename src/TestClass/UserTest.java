package TestClass;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import EntityClass.User;

class UserTest {

	@Test
	void testSetForbid() {
		User d = new User(null);
		boolean status=false;
		boolean forbiddened=d.isForbiddened();
		assertEquals(status,forbiddened);
		
	}
	
	@Test
	void testIsForbiddened() {
		User d = new User(null);
		boolean forbiddened=false;
		assertEquals(forbiddened,d.isForbiddened());
		
	}

	@Test
	void testGetEmail() {
		String email = "123@";
		User d = new User(email);
		assertEquals(email,d.getEmail());
	}
	
	@Test
	void testIsRiding() {
		User d = new User(null);
		boolean riding=false;
		assertEquals(riding,d.isRiding());
	}
	
	@Test
	void testSetRiding() {
		User d = new User(null);
		boolean riding=false;
		boolean status=d.isRiding();
		assertEquals(status,riding);
		
	}
	
	@Test
	void testGetLastStartTime() {
		User d = new User(null);
		long lastStartTime=0;
		assertEquals(lastStartTime,d.getLastStartTime());
	}
	
	@Test
	void testSetLastStartTime() {
		User d = new User(null);
		long lastStartTime=0;
		long t=d.getLastStartTime();
		assertEquals(t,lastStartTime);
		
	}
	void testGetMonthUseTime() {
		User d = new User(null);
		long monthUseTime=0;
		assertEquals(monthUseTime,d.getMonthUseTime());
	}
	
	@Test
	void testSetMonthUseTime() {
		User d = new User(null);
		long monthUseTime=0;
		long t=d.getMonthUseTime();
		assertEquals(t,monthUseTime);
		
	}
	
	@Test
	void testtoString() {
		String email = "123@";
		User d = new User(email);
		String info = "User Information:"+'\n'	
				+"Email:"+d.getEmail()+'\n'
				+"isRiding:"+d.isRiding()+'\n'
				+"isForbiddened:"+d.isForbiddened()+'\n'
				+"MonthUseTime:"+d.getMonthUseTime()+" s";
		assertEquals(info,d.toString());
	}
}
