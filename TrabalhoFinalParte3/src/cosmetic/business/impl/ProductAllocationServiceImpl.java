package cosmetic.business.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cosmetic.business.ProductAllocationService;
import cosmetic.business.domain.BusinessException;
import cosmetic.business.domain.Evaluation;
import cosmetic.business.domain.EvaluationCommittee;
import cosmetic.business.domain.Product;
import cosmetic.business.domain.User;
import cosmetic.database.Database;
import cosmetic.utils.comparators.ProductComparatorById;

public class ProductAllocationServiceImpl implements ProductAllocationService{
	
	private final Database database; 
	
	public ProductAllocationServiceImpl(Database database) {
		this.database = database;
	}

	@Override
	public List<Evaluation> allocateProducts(String committeeName, Integer numberOfEvaluators) throws BusinessException {
		List<Evaluation> newEvaluations = new ArrayList<>();
		EvaluationCommittee evaluationCommittee = this.database.getEvaluationCommitteeByName(committeeName);
		List<Product> alocationSet = evaluationCommittee.getSubmittedProducts();	
		while(!evaluationCommittee.areAllProductsAlocated(numberOfEvaluators)) {
			Collections.sort(alocationSet,new ProductComparatorById());
			for(Product product : alocationSet) {
				newEvaluations.add(product.alocate());
			}
		}
		return newEvaluations;
	}

}
