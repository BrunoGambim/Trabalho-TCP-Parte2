package cosmetic.business.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cosmetic.database.Database;

public class EvaluationTest {
	Database database;

	@Before
	public void setUp() throws Exception {
		this.database = new DatabaseForTests();
	}

	@Test
	public void testEvaluation() throws BusinessException {
		User joao = database.getUserById(1L);
		Product laRoche = database.getProductById(7L);
		assertEquals((Integer) 1, joao.getNumberOfEvaluations());
		assertEquals((Integer) 0, laRoche.getNumberOfEvaluations());
		Evaluation evaluation = new Evaluation(laRoche, joao, 2F);
		assertEquals((Integer) 2, joao.getNumberOfEvaluations());
		assertEquals((Integer) 1, laRoche.getNumberOfEvaluations());
		assertEquals((Float) 2F, evaluation.getRating());
	}
	
	@Test(expected = BusinessException.class)
	public void testHighestRatingException() throws BusinessException {
		User joao = database.getUserById(1L);
		Product laRoche = database.getProductById(7L);
		Evaluation evaluation = new Evaluation(laRoche, joao, 3.1F);
	}
	
	@Test(expected = BusinessException.class)
	public void testLowestRatingException() throws BusinessException {
		User joao = database.getUserById(1L);
		Product laRoche = database.getProductById(7L);
		Evaluation evaluation = new Evaluation(laRoche, joao, -3.1F);
	}
}
