package cosmetic.business.impl;

import java.util.Collections;
import java.util.List;

import cosmetic.business.ProductAllocationService;
import cosmetic.business.domain.BusinessException;
import cosmetic.business.domain.EvaluationCommittee;
import cosmetic.business.domain.Product;
import cosmetic.business.domain.User;
import cosmetic.database.Database;
import cosmetic.utils.comparators.ProductComparatorById;
import cosmetic.utils.log.AllocationLogBuilder;
import cosmetic.utils.log.LogBuilder;

public class ProductAllocationServiceImpl implements ProductAllocationService{
	
	private final Database database; 
	private LogBuilder logBuilder;
	
	public ProductAllocationServiceImpl(Database database) {
		this.database = database;
		this.logBuilder = new AllocationLogBuilder();
	}

	@Override
	public String allocateProducts(String committeeName, Integer numberOfEvaluators) throws BusinessException {
		EvaluationCommittee evaluationCommittee = getEvaluationCommitteeByName(committeeName);
		List<Product> alocationSet = evaluationCommittee.getSubmittedProducts();
		try {
			while(!evaluationCommittee.areAllProductsAlocated(numberOfEvaluators)) {
				allocateProductList(alocationSet);
			}
		} catch (BusinessException be) {
			this.logBuilder.addExceptionLine(be.getMessage());
		}
		return this.logBuilder.getText();
	}
	
	private void allocateProductList(List<Product> allocationSet) throws BusinessException {
		Collections.sort(allocationSet,new ProductComparatorById());
		for(Product product : allocationSet) {
			User evaluator = product.allocate();
			this.logBuilder.addLogLine(new String[] {product.getId().toString(),evaluator.getId().toString()});
		}	
	}
	
	private EvaluationCommittee getEvaluationCommitteeByName(String committeeName) throws BusinessException {
		EvaluationCommittee evaluationCommittee = this.database.getEvaluationCommitteeByName(committeeName);
		if(evaluationCommittee != null) {
			return evaluationCommittee;
		}else {
			throw new BusinessException("exception.invalid.evaluationCommittee");
		}
	}

}
