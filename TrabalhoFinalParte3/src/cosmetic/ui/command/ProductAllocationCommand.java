package cosmetic.ui.command;

import cosmetic.ui.UIUtils;

import cosmetic.business.ProductManagementService;
import cosmetic.business.domain.BusinessException;
import cosmetic.business.domain.EvaluationCommittee;
import cosmetic.business.domain.Product;
import cosmetic.business.domain.User;

public class ProductAllocationCommand extends Command{
	private static final int MIN_NUMBER_OF_EVALUATORS = 2;
	private static final int MAX_NUMBER_OF_EVALUATORS = 5;
	private ProductManagementService productManagementService;

	public ProductAllocationCommand(ProductManagementService productManagementService) {
		this.productManagementService = productManagementService;
	}

	@Override
	public void execute() throws BusinessException{
		UIUtils uiUtils = UIUtils.INSTANCE;
		
		String committeeName = uiUtils.readString("message.choose.committee");
		Integer numberOfEvaluators = uiUtils.readInteger("message.choose.numberOfEvaluators", MIN_NUMBER_OF_EVALUATORS, MAX_NUMBER_OF_EVALUATORS);
		
		EvaluationCommittee evaluationCommittee = this.productManagementService.allocateProducts(committeeName,numberOfEvaluators);
		printEvaluationCommitteeProducts(evaluationCommittee);
	}
	
	private void printEvaluationCommitteeProducts(EvaluationCommittee evaluationCommittee) {
		StringBuffer sb = new StringBuffer();
		sb.append(getTextManager().getText("product")).append("\t\t");
		sb.append(getTextManager().getText("evaluator")).append("\n");
		sb.append("-------------------------------------------------").append("\n");
		for(Product product : evaluationCommittee.getSubmittedProducts()) {
			for(User user : product.getEvaluators()) {
				sb.append(user.getName()).append("\t\t");
				sb.append(product.getName()).append("\n");
			}
		}
		System.out.println(sb);
	}

}
