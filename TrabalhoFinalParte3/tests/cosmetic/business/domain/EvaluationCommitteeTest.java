package cosmetic.business.domain;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cosmetic.database.Database;

public class EvaluationCommitteeTest {
	Database database;

	@Before
	public void setUp() throws Exception {
		this.database = new DatabaseForTests();
	}
	
	@Test
	public void testGetName() {
		EvaluationCommittee committeNamed1 = new EvaluationCommittee("Named 1");
		EvaluationCommittee committeNamed2 = new EvaluationCommittee("Named 2");
		assertEquals("Named 1", committeNamed1.getName());
		assertEquals("Named 2", committeNamed2.getName());
	}
	
	@Test
	public void testGetSubmittedProducts() {
		EvaluationCommittee SPFA = database.getEvaluationCommitteeByName("SPF A");
		Product lorialDDCream = database.getProductById(1L);
		Product laRoche = database.getProductById(7L);
		assertTrue(SPFA.getSubmittedProducts().contains(laRoche));
		assertFalse(SPFA.getSubmittedProducts().contains(lorialDDCream));
	}
	
	@Test
	public void testSubmitProducts() {
		EvaluationCommittee SPFA = database.getEvaluationCommitteeByName("SPF A");
		Product lorialDDCream = database.getProductById(1L);
		assertFalse(SPFA.getSubmittedProducts().contains(lorialDDCream));
		SPFA.submitProducts(lorialDDCream);
		assertTrue(SPFA.getSubmittedProducts().contains(lorialDDCream));
	}
	
	@Test
	public void addMember() throws BusinessException {
		EvaluationCommittee SPFA = database.getEvaluationCommitteeByName("SPF A");
		Product yvesRocher = database.getProductById(8L);
		User joana = database.getUserById(4L);
		User pedro = database.getUserById(9L);
		assertTrue(joana.equals(SPFA.getValidMember(yvesRocher)));
		SPFA.addMember(pedro);
		assertTrue(pedro.equals(SPFA.getValidMember(yvesRocher)));
	}

	@Test
	public void testGetValidMember() throws BusinessException {
		EvaluationCommittee SPFA = database.getEvaluationCommitteeByName("SPF A");
		Product laRoche = database.getProductById(7L);
		Product yvesRocher = database.getProductById(8L);
		User joao = database.getUserById(1L);
		User joana = database.getUserById(4L);
		assertTrue(joao.equals(SPFA.getValidMember(laRoche)));
		assertTrue(joana.equals(SPFA.getValidMember(yvesRocher)));
	}
	
	@Test(expected = BusinessException.class)
	public void testGetValidMemberException() throws BusinessException {
		EvaluationCommittee SPFC = database.getEvaluationCommitteeByName("SPF C");
		Product lorialDDCream = database.getProductById(1L);
		SPFC.getValidMember(lorialDDCream);
	}
	
	@Test
	public void testAllocateProducts() throws BusinessException {
		EvaluationCommittee SPFA = database.getEvaluationCommitteeByName("SPF A");
		EvaluationCommittee SPFC = database.getEvaluationCommitteeByName("SPF C");
		Product lorialDDCream = database.getProductById(1L);
		Product laRoche = database.getProductById(7L);
		Product yvesRocher = database.getProductById(8L);
		Product niveaBB = database.getProductById(9L);
		Product baseOBoticario = database.getProductById(10L);
		Product naturaSPF = database.getProductById(11L); 
		User joao = database.getUserById(1L);
		User ana = database.getUserById(2L);
		User manoela = database.getUserById(3L);
		User joana = database.getUserById(4L);
		User miguel = database.getUserById(5L);
		User beatriz = database.getUserById(6L);
		
		assertFalse(laRoche.isAllocated(2));
		assertFalse(yvesRocher.isAllocated(2));
		assertFalse(niveaBB.isAllocated(2));
		assertFalse(baseOBoticario.isAllocated(2));
		assertFalse(naturaSPF.isAllocated(2));
		
		SPFA.allocateProducts(2);
		
		assertTrue(laRoche.isAllocated(2));
		assertTrue(yvesRocher.isAllocated(2));
		assertTrue(niveaBB.isAllocated(2));
		assertTrue(baseOBoticario.isAllocated(2));
		assertTrue(naturaSPF.isAllocated(2));
		
		assertTrue(laRoche.getEvaluators().contains(joao));
		assertTrue(laRoche.getEvaluators().contains(ana));
		
		assertTrue(yvesRocher.getEvaluators().contains(joana));
		assertTrue(yvesRocher.getEvaluators().contains(beatriz));
		
		assertTrue(niveaBB.getEvaluators().contains(joao));
		assertTrue(niveaBB.getEvaluators().contains(manoela));
		
		assertTrue(baseOBoticario.getEvaluators().contains(miguel));
		assertTrue(baseOBoticario.getEvaluators().contains(joana));
		
		assertTrue(naturaSPF.getEvaluators().contains(manoela));
		assertTrue(naturaSPF.getEvaluators().contains(miguel));
		
		assertTrue(lorialDDCream.isAllocated(2));
		SPFC.allocateProducts(3);
		assertFalse(lorialDDCream.isAllocated(3));
	}

	@Test(expected = BusinessException.class)
	public void testTooManyEvaluatorsException() throws BusinessException {
		EvaluationCommittee SPFA = database.getEvaluationCommitteeByName("SPF A");
		SPFA.allocateProducts(6);
	}
	
	@Test(expected = BusinessException.class)
	public void testTooFewEvaluatorsException() throws BusinessException {
		EvaluationCommittee SPFA = database.getEvaluationCommitteeByName("SPF A");
		SPFA.allocateProducts(1);
	}
	
	@Test
	public void testGetAcceptableProducts() throws BusinessException {
		EvaluationCommittee SPFB = database.getEvaluationCommitteeByName("SPF B");
		Product avonCC = database.getProductById(2L);
		Product revolutionPowder = database.getProductById(3L);
		Product maybelline = database.getProductById(4L);
		Product revlonFoundation = database.getProductById(5L);
		Product niveaMatte = database.getProductById(6L);
		List<Product> products =  SPFB.getAcceptableProducts();
		assertTrue(products.contains(avonCC));
		assertTrue(products.contains(revolutionPowder));
		assertTrue(products.contains(maybelline));
		assertFalse(products.contains(revlonFoundation));
		assertFalse(products.contains(niveaMatte));
	}

	@Test
	public void testGetUnacceptableProducts() throws BusinessException {
		EvaluationCommittee SPFB = database.getEvaluationCommitteeByName("SPF B");
		Product avonCC = database.getProductById(2L);
		Product revolutionPowder = database.getProductById(3L);
		Product maybelline = database.getProductById(4L);
		Product revlonFoundation = database.getProductById(5L);
		Product niveaMatte = database.getProductById(6L);
		List<Product> products =  SPFB.getUnacceptableProducts();
		assertTrue(products.contains(revlonFoundation));
		assertTrue(products.contains(niveaMatte));
		assertFalse(products.contains(avonCC));
		assertFalse(products.contains(revolutionPowder));
		assertFalse(products.contains(maybelline));
	}
	
}
