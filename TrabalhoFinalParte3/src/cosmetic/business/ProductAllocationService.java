package cosmetic.business;

import cosmetic.business.domain.BusinessException;
public interface ProductAllocationService {

	String allocateProducts(String committeeName, Integer numberOfEvaluators) throws BusinessException;

}
