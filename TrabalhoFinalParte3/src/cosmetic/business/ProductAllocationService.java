package cosmetic.business;

import java.util.List;

import cosmetic.business.domain.Evaluation;

public interface ProductAllocationService {

	List<Evaluation> allocateProducts(String committeeName, Integer numberOfEvaluators);

}
