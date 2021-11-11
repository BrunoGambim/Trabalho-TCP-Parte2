package cosmetic.business.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cosmetic.business.ProductManagementService;
import cosmetic.business.domain.BusinessException;
import cosmetic.business.domain.DatabaseForTests;
import cosmetic.business.domain.EvaluationCommittee;
import cosmetic.business.domain.Product;
import cosmetic.database.Database;

public class ProductManagementServiceImplTest {
	ProductManagementService productManagementService;
	Database database;

	@Before
	public void setUp() throws Exception {
		database = new DatabaseForTests(); 
		productManagementService = new ProductManagementServiceImpl(database);
	}

	@Test
	public void testAllocateProducts() throws BusinessException {
		Product laRoche = database.getProductById(7L);
		assertFalse(laRoche.isAllocated(2));
		productManagementService.allocateProducts("SPF A", 2); 
		assertTrue(laRoche.isAllocated(2));
	}
	
	@Test
	public void testEvaluateProduct() throws BusinessException {
		Product avonCC  = database.getProductById(2L);
		assertEquals((Float)2.5F,avonCC.getEvalutionsMean());
		productManagementService.evaluateProduct(avonCC, 7L, 3F);
		assertEquals((Float)3F,avonCC.getEvalutionsMean());
	}
	
	@Test
	public void testGetAcceptableProducts() throws BusinessException {
		EvaluationCommittee SPFB = database.getEvaluationCommitteeByName("SPF B");
		Product avonCC = database.getProductById(2L);
		Product revolutionPowder = database.getProductById(3L);
		Product maybelline = database.getProductById(4L);
		Product revlonFoundation = database.getProductById(5L);
		Product niveaMatte = database.getProductById(6L);
		List<Product> products =  productManagementService.getAcceptableProducts(SPFB);
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
		List<Product> products =  productManagementService.getUnacceptableProducts(SPFB);
		assertTrue(products.contains(revlonFoundation));
		assertTrue(products.contains(niveaMatte));
		assertFalse(products.contains(avonCC));
		assertFalse(products.contains(revolutionPowder));
		assertFalse(products.contains(maybelline));
	}

}
