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
	
	public String getName() {
		return name;
	}

	private ProductCategory getCategory() {
		return category;
	}

	private User getRequester() {
		return requester;
	}
	
	public List<User> getEvaluators(){
		List<User> evaluators = new ArrayList<>();
		for(Evaluation evaluation : this.evaluations) {
			evaluators.add(evaluation.getEvaluator());
		}
		return evaluators;
	}

	private void setEvaluationCommittee(EvaluationCommittee evaluationCommittee) {
		evaluationCommittee.submitProducts(this);
		this.evaluationCommittee = evaluationCommittee;
	}
	
	public void addEvaluation(Evaluation evaluation) {
		evaluations.add(evaluation);
	}
	
	public Float getEvalutionsMean() throws BusinessException {
		float mean = 0;
		for(Evaluation evaluation : this.evaluations) {
			if(evaluation.getRating() != null) {
				mean += evaluation.getRating();
			}else {
				throw new BusinessException("exception.incompleteProductEvaluations");
			}
		}
		if(getNumberOfEvaluations() > 0) {
			mean = mean / getNumberOfEvaluations();
		}else {
			throw new BusinessException("exception.notAllocatedProduct");
		}
		return mean;
	}
	
	public boolean isAllocated(int numberOfEvaluators) {
		return getNumberOfEvaluations() >= numberOfEvaluators;
	}
	
	private boolean isEvaluatedBy(User user) {
		for(Evaluation evaluation : this.evaluations) {
			if(evaluation.getEvaluator().equals(user)) {
				return true;
			}
		}
		return false;
	}
	
	public Integer getNumberOfEvaluations() {
		return this.evaluations.size();
	}

	public boolean canBeEvaluatedBy(User user) {	
		return (!isEvaluatedBy(user)) && (!user.equals(this.getRequester())) && (!user.isInTheSameStateAs(this.getRequester())) && user.isInterestedIn(this.getCategory());
	}
	
	public User allocate() throws BusinessException {
		User evaluator = evaluationCommittee.getValidMember(this);
		new Evaluation(this, evaluator);
		return evaluator;
	}
}
