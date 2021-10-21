package cosmetic.business.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cosmetic.utils.comparators.UserComparator;

public class EvaluationCommittee {
	
	private String name;
	private List<Product> submittedProducts;
	private List<User> committeeMembers;
	
	public EvaluationCommittee(String name) {
		this(name,new ArrayList<Product>(),new ArrayList<User>());
	}
	
	public EvaluationCommittee(String name, List<Product> submittedProducts, List<User> committeeMembers) {
		this.name = name;
		this.submittedProducts = submittedProducts;
		this.committeeMembers = committeeMembers;
	}
	
	public void submitProducts(Product product) {
		submittedProducts.add(product);
	}
	
	public void addMember(User user) {
		committeeMembers.add(user);
	}

	public String getName() {
		return name;
	}

	public List<Product> getSubmittedProducts() {
		return new ArrayList<Product>(submittedProducts);
	}
	
	public User getValidMember(Product product) throws BusinessException{
		Collections.sort(this.committeeMembers,new UserComparator());
		for(User member : this.committeeMembers) {
			if(product.canBeEvaluatedBy(member)) {
				return member;
			}
		}
		throw new BusinessException("exception.insufficientCommitteMembers");
	}

	public boolean areAllProductsAlocated(Integer numberOfEvaluators) {
		for(Product product : submittedProducts) {
			if(!product.isAllocated(numberOfEvaluators)) {
				return false;
			}
		}
		return true;
	} 
	
}
