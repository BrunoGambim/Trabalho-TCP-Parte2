package cosmetic.business.domain;

import java.util.ArrayList;
import java.util.List;

public class Product {
	
	private Long id;
	private String name;
	private User requester;
	private EvaluationCommittee evaluationCommittee;
	private ProductCategory category;
	private List<Evaluation> evaluations;
	
	public Product(Long id, String name, User requester, EvaluationCommittee evaluationCommittee, ProductCategory category) {
		this.id = id;
		this.name = name;
		this.requester = requester;
		setEvaluationCommittee(evaluationCommittee);
		this.category = category;
		this.evaluations = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	private void setEvaluationCommittee(EvaluationCommittee evaluationCommittee) {
		evaluationCommittee.submitProducts(this);
		this.evaluationCommittee = evaluationCommittee;
	}
	
	public void addEvaluation(Evaluation evaluation) {
		evaluations.add(evaluation);
	}
	
	public boolean isEvaluatedBy(User user) {
		for(Evaluation evaluation : evaluations) {
			if(evaluation.getEvaluator().equals(user)) {
				return true;
			}
		}
		return false;
	}
	
}
