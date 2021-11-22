package cosmetic.business.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cosmetic.utils.comparators.ProductComparatorById;
import cosmetic.utils.comparators.UserComparator;
import cosmetic.utils.log.AllocationLogBuilder;
import cosmetic.utils.log.LogBuilder;

public class EvaluationCommittee {
	
	private String name;
	private List<Product> submittedProducts;
	private List<User> committeeMembers;
	private LogBuilder logBuilder;
	private static final int MAX_NUMBER_OF_EVALUATORS = 5;
	private static final int MIN_NUMBER_OF_EVALUATORS = 2;
	private final static int LOWEST_ACCEPTABLE_MEAN = 0;
	
	public EvaluationCommittee(String name) {
		this(name,new ArrayList<Product>(),new ArrayList<User>());
	}
	
	public EvaluationCommittee(String name, List<Product> submittedProducts, List<User> committeeMembers) {
		this.name = name;
		this.submittedProducts = submittedProducts;
		this.committeeMembers = committeeMembers;
		this.logBuilder = AllocationLogBuilder.INSTANCE;
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
		return new ArrayList<Product>(submittedProducts);
	}
	
	public User getValidMember(Product product) throws BusinessException{
		Collections.sort(this.committeeMembers,new UserComparator());
		for(User member : this.committeeMembers) {
			if(product.canBeEvaluatedBy(member)) {
				return member;
			}
		}
		throw new BusinessException("exception.insufficientCommitteMembers");
	}
	
	public void allocateProducts(Integer numberOfEvaluators) throws BusinessException {
		if(!isValidNumberOfEvaluators(numberOfEvaluators)) {
			throw new BusinessException("exception.invalid.numberOfEvaluators");
		}
		List<Product> alocationSet = getSubmittedProducts();
		this.logBuilder.startLog();
		try {
			do {
				allocateProductList(alocationSet);
			} while(!areAllProductsAlocated(numberOfEvaluators));
		} catch (BusinessException be) {
			this.logBuilder.addExceptionLine(be.getMessage());
		}
		this.logBuilder.finishLog();
	}
	
	private boolean isValidNumberOfEvaluators(Integer numberOfEvaluators) {
		return numberOfEvaluators >= MIN_NUMBER_OF_EVALUATORS && numberOfEvaluators <= MAX_NUMBER_OF_EVALUATORS;
	}
	
	private void allocateProductList(List<Product> allocationSet) throws BusinessException {
		Collections.sort(allocationSet,new ProductComparatorById());
		for(Product product : allocationSet) {
			User evaluator = product.allocate();
			this.logBuilder.addLogLine(new String[] {product.getId().toString(),evaluator.getId().toString()});
		}	
	}
	
	private boolean areAllProductsAlocated(Integer numberOfEvaluators) {
		for(Product product : submittedProducts) {
			if(!product.isAllocated(numberOfEvaluators)) {
				return false;
			}
		}
		return true;
	}
	
	public List<Product> getAcceptableProducts() throws BusinessException {
		List<Product> acceptableProducts = new ArrayList<>();
		for(Product product : getSubmittedProducts()) {
			if(product.getEvalutionsMean() >= LOWEST_ACCEPTABLE_MEAN) {
				acceptableProducts.add(product);
			} 
		}
		return acceptableProducts;
	}
	
	public List<Product> getUnacceptableProducts() throws BusinessException {
		List<Product> unacceptableProducts = new ArrayList<>();
		for(Product product : getSubmittedProducts()) {
			if(product.getEvalutionsMean() < LOWEST_ACCEPTABLE_MEAN) {
				unacceptableProducts.add(product);
			} 
		}
		return unacceptableProducts;
	}
	
}
