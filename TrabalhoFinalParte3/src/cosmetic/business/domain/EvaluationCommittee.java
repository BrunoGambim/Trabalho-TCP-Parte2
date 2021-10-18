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
		return submittedProducts;
	}
	
	public User getValidMember(Product product) throws BusinessException{
		Collections.sort(this.committeeMembers,new UserComparator());
		System.out.println("Produto id: "+ product.getId());
		for(User member : this.committeeMembers) {
			System.out.println("Membro: "+ member.getId());
			if(product.canBeEvaluatedBy(member)) {
				return member;
			}
		}
		throw new BusinessException("Usuários insuficientes no comitê para a avaliação");
	} 
	
}
