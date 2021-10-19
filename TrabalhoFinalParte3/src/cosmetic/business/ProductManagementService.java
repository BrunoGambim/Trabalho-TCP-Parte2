package cosmetic.business;

import java.util.Collection;
import java.util.List;

import cosmetic.business.domain.BusinessException;
import cosmetic.business.domain.EvaluationCommittee;
import cosmetic.business.domain.Product;

public interface ProductManagementService {

	Collection<Product> getAllProdutcs();

	Product getProductById(Long productId);

	void evaluateProduct(Product choosenProduct, Long evaluatorId, Float nota) throws BusinessException;

	EvaluationCommittee getEvaluationCommitteeByName(String committeeName);

	List<Product> getUnacceptableProducts(EvaluationCommittee evaluationCommittee) throws BusinessException;

	List<Product> getAcceptableProducts(EvaluationCommittee evaluationCommittee) throws BusinessException;


}
