package cosmetic.business.domain;

public class Product {
	
	private Long id;
	private String name;
	private User requester;
	private EvaluationCommittee evaluationCommittee;
	private String category;
	
	public Product(Long id, String name, User requester, EvaluationCommittee evaluationCommittee, String category) {
		this.id = id;
		this.name = name;
		this.requester = requester;
		this.evaluationCommittee = evaluationCommittee;
		this.category = category;
	}
	
}
