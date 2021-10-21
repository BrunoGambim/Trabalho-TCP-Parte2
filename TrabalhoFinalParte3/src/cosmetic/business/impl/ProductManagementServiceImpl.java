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
	public Product getProductById(Long id) throws BusinessException {
		Product product = this.database.getProductById(id);
		if(product != null) {
			return product;
		}else {
			throw new BusinessException("exception.invalid.product");
		}
	}
	
	@Override
	public EvaluationCommittee getEvaluationCommitteeByName(String committeeName) throws BusinessException{
		EvaluationCommittee evaluationCommittee = this.database.getEvaluationCommitteeByName(committeeName);
		if(evaluationCommittee != null) {
			return evaluationCommittee;
		}else {
			throw new BusinessException("exception.invalid.evaluationCommittee");
		}
	}

	@Override
	public void evaluateProduct(Product choosenProduct, Long evaluatorId, Float rating) throws BusinessException {
		User evaluator = this.database.getUserById(evaluatorId);
		evaluator.evaluate(choosenProduct, rating);
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
