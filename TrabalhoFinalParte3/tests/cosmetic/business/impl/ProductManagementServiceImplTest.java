package cosmetic.business.impl;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cosmetic.business.ProductManagementService;
import cosmetic.business.domain.BusinessException;
import cosmetic.business.domain.DatabaseForTests;
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
	public void testGetAllProdutcs() throws BusinessException {
		Product lorialDDCream = database.getProductById(1L);
		Product laRoche = database.getProductById(7L);
		Product naturaSPF20 = database.getProductById(11L);
		Collection<Product> allProducts = productManagementService.getAllProdutcs();
		assertTrue(allProducts.contains(lorialDDCream));
		assertTrue(allProducts.contains(laRoche));
		assertTrue(allProducts.contains(naturaSPF20));
	}
	
	@Test
	public void testGetProductById() throws BusinessException {
		Product expectedLorialDDCream = database.getProductById(1L);
		Product expectedLaRoche = database.getProductById(7L);
		Product lorialDDCream = productManagementService.getProductById(1L);
		Product laRoche = productManagementService.getProductById(7L);
		assertEquals(expectedLorialDDCream, lorialDDCream);
		assertEquals(expectedLaRoche, laRoche);
	}
	
	@Test(expected = BusinessException.class)
	public void testGetInvalidProductById() throws BusinessException {
		Product nonexistentProduct = productManagementService.getProductById(13L);
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
		Product revolutionPowder = database.getProductById(3L);
		Product maybelline = database.getProductById(4L);
		List<Product> products =  productManagementService.getAcceptableProducts("SPF B");
		assertTrue(products.contains(revolutionPowder));
		assertTrue(products.contains(maybelline));
	}

	@Test
	public void testGetUnacceptableProducts() throws BusinessException {
		Product avonCC = database.getProductById(2L);
		Product niveaMatte = database.getProductById(6L);
		List<Product> products =  productManagementService.getUnacceptableProducts("SPF B");
		assertTrue(products.contains(niveaMatte));
		assertFalse(products.contains(avonCC));
	}

}
