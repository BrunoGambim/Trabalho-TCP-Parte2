package cosmetic.business.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StateTest {
	State RS;
	State SP;

	@Before
	public void setUp() throws Exception {
		RS = new State("RS");
		SP = new State("SP");
	}

	@Test
	public void testEquals() {
		assertTrue(RS.equals(RS));
		assertFalse(RS.equals(SP));
	}

}
