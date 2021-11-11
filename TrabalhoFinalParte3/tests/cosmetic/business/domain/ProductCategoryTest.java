package cosmetic.business.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProductCategoryTest {
	ProductCategory BBCream;
	ProductCategory CCCream;

	@Before
	public void setUp() throws Exception {
		BBCream = new ProductCategory("BBCream");
		CCCream = new ProductCategory("CCCream");
	}

	@Test
	public void testEquals() {
		assertTrue(BBCream.equals(BBCream));
		assertFalse(BBCream.equals(CCCream));
	}

}
