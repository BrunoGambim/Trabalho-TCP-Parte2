package cosmetic.business.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cosmetic.database.Database;

public class ProductTest {
	Database database;
	
	@Before
	public void setUp() throws Exception {
		this.database = new DatabaseForTests();
	}

	@Test
	public void testCanBeEvaluatedBy() {
		Product lorialDDCream = database.getProductById(1L);
		User validEvaluator = database.getUserById(2L);
		User requester = database.getUserById(1L);
		User sameState = database.getUserById(5L);
		User alreadyEvaluate = database.getUserById(8L);
		User notInterested = database.getUserById(4L);
		assertTrue(lorialDDCream.canBeEvaluatedBy(validEvaluator));
		assertFalse(lorialDDCream.canBeEvaluatedBy(requester));
		assertFalse(lorialDDCream.canBeEvaluatedBy(sameState));
		assertFalse(lorialDDCream.canBeEvaluatedBy(alreadyEvaluate));
		assertFalse(lorialDDCream.canBeEvaluatedBy(notInterested));
	}
	
	@Test
	public void testGetNumberOfEvaluations() {
		Product lorialDDCream = database.getProductById(1L);
		assertEquals((Integer) 2,lorialDDCream.getNumberOfEvaluations());
	}
	
	@Test
	public void testIsAllocated() {
		Product lorialDDCream = database.getProductById(1L);
		assertTrue(lorialDDCream.isAllocated(1));
		assertTrue(lorialDDCream.isAllocated(2));
		assertFalse(lorialDDCream.isAllocated(3));
	}
	
	@Test
	public void GetEvalutionsMean() throws BusinessException {
		Product avonCCCream = database.getProductById(2L);
		Product revlonFoundation = database.getProductById(5L);
		assertEquals((Float) 2.5F,avonCCCream.getEvalutionsMean());
		assertEquals((Float) (-3F),revlonFoundation.getEvalutionsMean());
	}
	
	@Test(expected = BusinessException.class)
	public void GetEvalutionsMeanException() throws BusinessException {
		Product lorialDDCream = database.getProductById(1L);
		lorialDDCream.getEvalutionsMean();
	}
	
	@Test
	public void testAllocate() throws BusinessException {
		Product laRoche = database.getProductById(2L);;
		assertFalse(laRoche.isAllocated(3));
		laRoche.allocate();
		assertTrue(laRoche.isAllocated(3));
	}

	@Test(expected = BusinessException.class)
	public void testAllocateException() throws BusinessException {
		Product lorialDDCream = database.getProductById(1L);;
		lorialDDCream.allocate();
	}
	
}
