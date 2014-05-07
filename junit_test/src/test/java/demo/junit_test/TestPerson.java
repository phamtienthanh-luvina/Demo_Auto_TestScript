package demo.junit_test;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestPerson {
	Person person;
	
	@Test
	public void testGo(){
		assertEquals(person.persionInfo(), "My name is A, i 27 years old !!!");
	}
	
	@Before
	public void prepare(){
		person = new Person("A", 27);
	}
}
