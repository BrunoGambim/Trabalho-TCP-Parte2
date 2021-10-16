package cosmetic.business.domain;

import java.util.ArrayList;
import java.util.List;

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

}
