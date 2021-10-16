package cosmetic.database;

import java.util.HashMap;
import java.util.Map;

import cosmetic.business.domain.User;
import cosmetic.business.domain.EvaluationCommittee;
import cosmetic.business.domain.Product;

public class Database {
	
	private Map<Long,User> users;
	private Map<Long,EvaluationCommittee> evaluationCommittees; 
	private Map<Long,Product> products;
	
	public Database() {
		this(true);
	}

	public Database(boolean initData) {
		this.users = new HashMap<>();
		this.evaluationCommittees = new HashMap<>();
		this.products = new HashMap<>();
		if (initData) {
			initData();
		}
	}

	private void initData() {
		User user1 = new User(1L,"João","RS");
		user1.addProductCategory("BB Cream");
		user1.addProductCategory("CC Cream");
		user1.addProductCategory("DD Cream");
		
		users.put(user1.getId(), user1);
	}

}
