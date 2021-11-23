package cosmetic.business.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import cosmetic.database.Database;

public class ProductTest {
	Database database;
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		this.database = new DatabaseForTests();
	}
	
	@Test
	public void testGetId() {
		EvaluationCommittee SPFA = database.getEvaluationCommitteeByName("SPF A");
		User requester = database.getUserById(1L);
		ProductCategory CCCream = new ProductCategory("CC Cream");
		Product productId1 = new Product(1L, "Named 1", requester, SPFA, CCCream);
		Product productId2 = new Product(2L, "Named 2", requester, SPFA, CCCream);
		assertEquals((Long) 1L, productId1.getId());
		assertEquals((Long) 2L, productId2.getId());
	}
	
	@Test
	public void testGetName() {
		EvaluationCommittee SPFA = database.getEvaluationCommitteeByName("SPF A");
		User requester = database.getUserById(1L);
		ProductCategory CCCream = new ProductCategory("CC Cream");
		Product productId1 = new Product(1L, "Named 1", requester, SPFA, CCCream);
		Product productId2 = new Product(2L, "Named 2", requester, SPFA, CCCream);
		assertEquals("Named 1", productId1.getName());
		assertEquals("Named 2", productId2.getName());
	}
	
	@Test
	public void testGetEvaluators() {
		Product lorialDDCream = database.getProductById(1L);
		User joao = database.getUserById(1L);
		User natasha = database.getUserById(8L);
		assertTrue(lorialDDCream.getEvaluators().contains(natasha));
		assertFalse(lorialDDCream.getEvaluators().contains(joao));
	}

	@Test
	public void testAddEvaluation() {
		User joao = database.getUserById(1L);
		Product laRoche = database.getProductById(7L);
		assertEquals((Integer) 0, laRoche.getNumberOfEvaluations());
		new Evaluation(laRoche, joao);
		assertEquals((Integer) 1, laRoche.getNumberOfEvaluations());
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
	public void testGetEvalutionsMean() throws BusinessException {
		Product avonCCCream = database.getProductById(2L);
		Product revlonFoundation = database.getProductById(5L);
		assertEquals((Float) 2.5F,avonCCCream.getEvalutionsMean());
		assertEquals((Float) (-3F),revlonFoundation.getEvalutionsMean());
	}
	
	@Test()
	public void testIncompleteProductEvaluations() throws BusinessException {
		exceptionRule.expect(BusinessException.class);
	    exceptionRule.expectMessage("exception.incompleteProductEvaluations");
		Product lorialDDCream = database.getProductById(1L);
		lorialDDCream.getEvalutionsMean();
	}
	
	@Test()
	public void testNotAllocatedProduct() throws BusinessException {
		exceptionRule.expect(BusinessException.class);
	    exceptionRule.expectMessage("exception.notAllocatedProduct");
		Product laRoche = database.getProductById(7L);
		laRoche.getEvalutionsMean();
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
