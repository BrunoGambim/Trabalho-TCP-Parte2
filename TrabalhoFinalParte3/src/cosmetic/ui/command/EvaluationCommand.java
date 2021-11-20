package cosmetic.ui.command;

import java.util.Collection;
import java.util.List;

import cosmetic.business.ProductManagementService;
import cosmetic.business.domain.BusinessException;
import cosmetic.business.domain.Product;
import cosmetic.business.domain.User;
import cosmetic.ui.UIUtils;

public class EvaluationCommand extends Command{

	ProductManagementService productManagementService;
	private UIUtils uiUtils = UIUtils.INSTANCE;
	
	public EvaluationCommand(ProductManagementService productManagementService) {
		this.productManagementService = productManagementService;
	}

	@Override
	public void execute() throws BusinessException {
		Collection<Product> products =  productManagementService.getAllProdutcs();
		printProductList(products);
		
		Long productId = uiUtils.readLong("message.choose.product");
		Product choosenProduct = productManagementService.getProductById(productId);
		List<User> evaluators = choosenProduct.getEvaluators();
		
		if(!evaluators.isEmpty()) {
			printEvaluatorsList(evaluators);
			
			Long evaluatorId = uiUtils.readLong("message.choose.evaluator");
			Float rating = uiUtils.readFloat("message.choose.rating");
			
			productManagementService.evaluateProduct(choosenProduct, evaluatorId, rating);
		}else {
			System.out.println(getTextManager().getText("message.warning.notAllocatedProduct"));
		}
	}
	
	private void printProductList(Collection<Product> products) {
		StringBuffer sb = new StringBuffer();
		sb.append(getTextManager().getText("id")).append("\t\t");
		sb.append(getTextManager().getText("name")).append("\n");
		sb.append("-------------------------------------------------").append("\n");
		for(Product product : products) {
			sb.append(product.getId()).append("\t\t");
			sb.append(product.getName()).append("\n");
		}
		System.out.println(sb);
	}
	
	private void printEvaluatorsList(Collection<User> evaluators) {
		StringBuffer sb = new StringBuffer();
		sb.append(getTextManager().getText("id")).append("\t\t");
		sb.append(getTextManager().getText("name")).append("\n");
		sb.append("-------------------------------------------------").append("\n");
		for(User evaluator : evaluators) {
			sb.append(evaluator.getId()).append("\t\t");
			sb.append(evaluator.getName()).append("\n");
		}
		System.out.println(sb);
	}

}
