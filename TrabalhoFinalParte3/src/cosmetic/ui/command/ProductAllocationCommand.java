package cosmetic.ui.command;

import java.util.List;

import cosmetic.ui.UIUtils;
import cosmetic.business.ProductAllocationService;
import cosmetic.business.domain.BusinessException;
import cosmetic.business.domain.Evaluation;
import cosmetic.business.domain.EvaluationCommittee;

public class ProductAllocationCommand extends Command{
	
	private ProductAllocationService productAllocationService;

	public ProductAllocationCommand(ProductAllocationService productAllocationService) {
		this.productAllocationService = productAllocationService;
	}

	@Override
	public void execute() throws BusinessException{
		final int MIN_NUMBER_OF_EVALUATORS = 2;
		final int MAX_NUMBER_OF_EVALUATORS = 5;
		UIUtils uiUtils = UIUtils.INSTANCE;
		
		String committeeName = uiUtils.readString("message.choose.committee");
		Integer numberOfEvaluators = uiUtils.readInteger("message.choose.numberOfEvaluators", MIN_NUMBER_OF_EVALUATORS, MAX_NUMBER_OF_EVALUATORS);
		System.out.println(getTextManager().getText("log.start.allocation"));
		List<Evaluation> evaluations = this.productAllocationService.allocateProducts(committeeName,numberOfEvaluators);
		printEvaluations(evaluations);
		System.out.println(getTextManager().getText("log.end.allocation"));
	}

	private void printEvaluations(List<Evaluation> evaluations) {
		StringBuffer sb = new StringBuffer();
		for(Evaluation evaluation : evaluations) {
			Long productId = evaluation.getProduct().getId();
			Long evaluatorId = evaluation.getEvaluator().getId();
			sb.append(getTextManager().getText("log.productAllocation", new String[] { "" + productId,"" + evaluatorId }, false));
			sb.append("\n");
		}
		System.out.print(sb);
	}

}
