package cosmetic.business;

import java.util.Collection;
import java.util.List;

import cosmetic.business.domain.BusinessException;
import cosmetic.business.domain.EvaluationCommittee;
import cosmetic.business.domain.Product;

public interface ProductManagementService {

	Collection<Product> getAllProdutcs() throws BusinessException;

	Product getProductById(Long productId) throws BusinessException;

	void evaluateProduct(Product choosenProduct, Long evaluatorId, Float nota) throws BusinessException;

	List<Product> getUnacceptableProducts(String committeeName) throws BusinessException;

	List<Product> getAcceptableProducts(String committeeName) throws BusinessException;

	EvaluationCommittee allocateProducts(String committeeName, Integer numberOfEvaluators) throws BusinessException;

}
