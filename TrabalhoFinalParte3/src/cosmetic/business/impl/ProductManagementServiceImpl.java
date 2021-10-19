package cosmetic.business.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cosmetic.business.ProductManagementService;
import cosmetic.business.domain.BusinessException;
import cosmetic.business.domain.Evaluation;
import cosmetic.business.domain.EvaluationCommittee;
import cosmetic.business.domain.Product;
import cosmetic.business.domain.User;
import cosmetic.database.Database;

public class ProductManagementServiceImpl implements ProductManagementService{

	Database database;
	private final static int LOWEST_ACCEPTABLE_MEAN = 0;
	
	public ProductManagementServiceImpl(Database database) {
		this.database = database;
	}

	@Override
	public Collection<Product> getAllProdutcs() {
		return this.database.getAllProducts();
	}

	@Override
	public Product getProductById(Long id) {
		return this.database.getProductById(id);
	}
	
	@Override
	public EvaluationCommittee getEvaluationCommitteeByName(String committeeName) {
		return this.database.getEvaluationCommitteeByName(committeeName);
	}

	@Override
	public void evaluateProduct(Product choosenProduct, Long evaluatorId, Float nota) throws BusinessException {
		User evaluator = this.database.getUserById(evaluatorId);
		Evaluation evaluation = choosenProduct.getEvaluation(evaluator);
		evaluation.setRating(nota);
	}

	@Override
	public List<Product> getUnacceptableProducts(EvaluationCommittee evaluationCommittee) throws BusinessException {
		List<Product> unacceptableProducts = new ArrayList<>();
		for(Product product : evaluationCommittee.getSubmittedProducts()) {
			if(product.getEvalutionsMean() < LOWEST_ACCEPTABLE_MEAN) {
				unacceptableProducts.add(product);
			} 
		}
		return unacceptableProducts;
	}

	@Override
	public List<Product> getAcceptableProducts(EvaluationCommittee evaluationCommittee) throws BusinessException {
		List<Product> acceptableProducts = new ArrayList<>();
		for(Product product : evaluationCommittee.getSubmittedProducts()) {
			if(product.getEvalutionsMean() >= LOWEST_ACCEPTABLE_MEAN) {
				acceptableProducts.add(product);
			} 
		}
		return acceptableProducts;
	}

}
