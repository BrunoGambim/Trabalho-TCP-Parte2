package cosmetic.business.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private Long id;
	private String name;
	private State state;
	private List<ProductCategory> productCategories;
	private List<Evaluation> evaluations;
	
	public User(Long id, String name, State state) {
		this(id,name,state,new ArrayList<ProductCategory>());
	}

	public User(Long id, String name, State state, List<ProductCategory> productCategories) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.productCategories = productCategories;
		this.evaluations = new ArrayList<>();
	}
	
	public void addProductCategory(ProductCategory category) {
		productCategories.add(category);
	}

	public Long getId() {
		return id;
	}
	
	public Integer getNumberOfEvaluations() {
		return evaluations.size();
	}
	
	public void evaluate(Evaluation evaluation) {
		evaluations.add(evaluation);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof User) {
			User user = (User) obj;
			return this.equals(user.getId());
		}else {
			return false;
		}
	}
	
	public boolean equals(Long id) {
		if(getId().equals(id)) {
			return true;
		}else {
			return false;
		}
	}

}
