package cosmetic.ui.command;

import cosmetic.ui.UIUtils;
import cosmetic.business.ProductAllocationService;
import cosmetic.business.domain.BusinessException;

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
		String log = this.productAllocationService.allocateProducts(committeeName,numberOfEvaluators);
		System.out.println(log);
	}

}
