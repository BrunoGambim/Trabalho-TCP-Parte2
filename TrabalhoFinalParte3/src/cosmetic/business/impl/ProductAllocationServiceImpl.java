package cosmetic.business.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cosmetic.business.ProductAllocationService;
import cosmetic.business.domain.Evaluation;
import cosmetic.business.domain.EvaluationCommittee;
import cosmetic.business.domain.Product;
import cosmetic.business.domain.User;
import cosmetic.database.Database;
import cosmetic.utils.comparators.ProductComparator;

public class ProductAllocationServiceImpl implements ProductAllocationService{
	
	private final Database database; 
	
	public ProductAllocationServiceImpl(Database database) {
		this.database = database;
	}

	@Override
	public List<Evaluation> allocateProducts(String committeeName, Integer numberOfEvaluators) {
		List<Evaluation> newEvaluations = new ArrayList<>();
		try {
			EvaluationCommittee evaluationCommittee = this.database.getEvaluationCommitteesByName(committeeName);
			List<Product> products = evaluationCommittee.getSubmittedProducts();	
			for(int i = 0; i < numberOfEvaluators; i++) {
				Collections.sort(products,new ProductComparator());
				for(Product product : products) {
					User evaluator = evaluationCommittee.getValidMember(product);
					newEvaluations.add(new Evaluation(product, evaluator));
				}
			}
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		return newEvaluations;
	}

}
