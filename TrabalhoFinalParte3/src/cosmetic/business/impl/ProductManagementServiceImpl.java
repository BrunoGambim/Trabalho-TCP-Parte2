package cosmetic.business.impl;

import java.util.Collection;
import java.util.List;

import cosmetic.business.ProductManagementService;
import cosmetic.business.domain.BusinessException;
import cosmetic.business.domain.EvaluationCommittee;
import cosmetic.business.domain.Product;
import cosmetic.business.domain.User;
import cosmetic.database.Database;

public class ProductManagementServiceImpl implements ProductManagementService{

	Database database;
	
	public ProductManagementServiceImpl(Database database) {
		this.database = database;
	}

	@Override
	public Collection<Product> getAllProdutcs() throws BusinessException {
		Collection<Product> products = this.database.getAllProducts();
		if(!products.isEmpty()) {
			return products;
		}else {
			throw new BusinessException("exception.noRegisteredProduct");
		}
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
	public EvaluationCommittee allocateProducts(String committeeName, Integer numberOfEvaluators) throws BusinessException {
		EvaluationCommittee evaluationCommittee = getEvaluationCommitteeByName(committeeName);
		evaluationCommittee.allocateProducts(numberOfEvaluators);
		return evaluationCommittee;
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
		User evaluator = getUserById(evaluatorId);
		evaluator.evaluate(choosenProduct, rating);
	}
	
	private User getUserById(Long id) throws BusinessException {
		User user = this.database.getUserById(id);
		if(user != null) {
			return user;
		}else {
			throw new BusinessException("exception.invalid.evaluator");
		}
		
	}

	@Override
	public List<Product> getUnacceptableProducts(String committeeName) throws BusinessException {
		EvaluationCommittee evaluationCommittee = getEvaluationCommitteeByName(committeeName);
		return evaluationCommittee.getUnacceptableProducts();
	}

	@Override
	public List<Product> getAcceptableProducts(String committeeName) throws BusinessException {
		EvaluationCommittee evaluationCommittee = getEvaluationCommitteeByName(committeeName);
		return evaluationCommittee.getAcceptableProducts();
	}

}
