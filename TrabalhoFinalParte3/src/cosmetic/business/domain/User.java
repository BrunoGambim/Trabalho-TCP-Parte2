package cosmetic.business.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private Long id;
	private String name;
	private String state;
	private List<String> productCategories;
	
	public User(Long id, String name, String state) {
		this(id,name,state,new ArrayList<String>());
	}

	public User(Long id, String name, String state, List<String> productCategories) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.productCategories = productCategories;
	}
	
	public void addProductCategory(String category) {
		productCategories.add(category);
	}

	public Long getId() {
		return id;
	}
	
}
