package cosmetic.business.domain;

public class Evaluation {
	
	private final static int HIGHESTRATING = 3;
	private final static int LOWESTRATING = -3;
	
	private Product product;
	private User evaluator;
	private Float rating;

	public Evaluation(Product product, User evaluator, Float rating) {
		setRating(rating);
		setProduct(product);
		setEvaluator(evaluator);
	}
	
	public Evaluation(Product product, User evaluator) {
		setProduct(product);
		setEvaluator(evaluator);
	}

	public Float getRating() {
		return rating;
	}

	private void setRating(Float rating) {
		if(rating <= HIGHESTRATING  && rating >= LOWESTRATING) {
			this.rating = rating;
		}
	}

	private void setProduct(Product product) {
		this.product = product;
		product.addEvaluation(this);
	}

	private void setEvaluator(User evaluator) {
		this.evaluator = evaluator;
		evaluator.evaluate(this);
	}
	
	public User getEvaluator() {
		return this.evaluator;
	}
	
	public Product getProduct() {
		return this.product;
	}
	
}
