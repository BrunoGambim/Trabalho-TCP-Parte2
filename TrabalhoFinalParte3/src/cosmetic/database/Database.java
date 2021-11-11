package cosmetic.database;

import java.util.Collection;

import cosmetic.business.domain.EvaluationCommittee;
import cosmetic.business.domain.Product;
import cosmetic.business.domain.User;

public interface Database {
	
	public Collection<Product> getAllProducts();
	
	public Product getProductById(Long id);
	
	public User getUserById(Long id);
	
	public EvaluationCommittee getEvaluationCommitteeByName(String name);
}
