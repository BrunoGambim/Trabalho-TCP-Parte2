package cosmetic.business.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import cosmetic.database.Database;

public class UserTest {
	Database database;
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		this.database = new DatabaseForTests();
	}
	
	@Test
	public void testGetId() {
		State RS = new State("RS");
		User userId1 = new User(1L,"Named 1",RS);
		User userId2 = new User(2L,"Named 2",RS);
		assertEquals((Long) 1L, userId1.getId());
		assertEquals((Long) 2L, userId2.getId());
	}
	
	@Test
	public void testGetName() {
		State RS = new State("RS");
		User userId1 = new User(1L,"Named 1",RS);
		User userId2 = new User(2L,"Named 2",RS);
		assertEquals("Named 1", userId1.getName());
		assertEquals("Named 2", userId2.getName());
	}

	@Test
	public void testAddEvaluation() {
		User joao = database.getUserById(1L);
		Product laRoche = database.getProductById(7L);
		assertEquals((Integer) 1, joao.getNumberOfEvaluations());
		new Evaluation(laRoche, joao);
		assertEquals((Integer) 2, joao.getNumberOfEvaluations());
	}
	
	@Test
	public void testAddProductCategory() {
		User joao = database.getUserById(1L);
		ProductCategory productCategory = new ProductCategory("new category");
		assertFalse(joao.isInterestedIn(productCategory));
		joao.addProductCategory(productCategory);
		assertTrue(joao.isInterestedIn(productCategory));
	}
	
	@Test
	public void testGetNumberOfEvaluations() {
		User joao = database.getUserById(1L);
		User pedro = database.getUserById(9L);
		assertEquals((Integer) 1, joao.getNumberOfEvaluations());
		assertEquals((Integer) 0, pedro.getNumberOfEvaluations());
	}
	
	@Test
	public void testIsInTheSameStateAs() {
		User joao = database.getUserById(1L);
		User ana = database.getUserById(2L);
		User pedro = database.getUserById(9L);
		assertTrue(ana.isInTheSameStateAs(pedro));
		assertTrue(ana.isInTheSameStateAs(ana));
		assertFalse(ana.isInTheSameStateAs(joao));
	}

	@Test
	public void testIsInterestedIn() {
		User joao = database.getUserById(1L);
		ProductCategory CCCream = new ProductCategory("CC Cream");
		ProductCategory oilFree= new ProductCategory("Oil Free Matte SPF");
		assertTrue(joao.isInterestedIn(CCCream));
		assertFalse(joao.isInterestedIn(oilFree));
	}
	
	@Test
	public void testEquals() {
		User joao = database.getUserById(1L);
		User ana = database.getUserById(2L);
		assertTrue(joao.equals(joao));
		assertFalse(joao.equals(ana));
	}
	
	@Test
	public void testEvaluate() throws BusinessException {
		User carla = database.getUserById(10L);
		Product lorialDDCream = database.getProductById(1L);
		carla.evaluate(lorialDDCream, 3F);
		assertEquals((Float) 2.5F,lorialDDCream.getEvalutionsMean());
		carla.evaluate(lorialDDCream, 0F);
		assertEquals((Float) 1F,lorialDDCream.getEvalutionsMean());
	}
	
	@Test
	public void testEvaluateInvalidProduct() throws BusinessException {
		exceptionRule.expect(BusinessException.class);
	    exceptionRule.expectMessage("exception.notAllocatedForEvaluator");
		User carla = database.getUserById(10L);
		Product revolutionPowder = database.getProductById(3L);
		carla.evaluate(revolutionPowder, 3F);
	}
	
	@Test
	public void testEvaluateInvalidRate() throws BusinessException {
		exceptionRule.expect(BusinessException.class);
	    exceptionRule.expectMessage("exception.invalid.evaluation");
		User carla = database.getUserById(10L);
		Product lorialDDCream = database.getProductById(1L);
		carla.evaluate(lorialDDCream, 4F);
	}
	
}
