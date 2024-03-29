package cosmetic.ui.command;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import cosmetic.business.ProductManagementService;
import cosmetic.business.domain.BusinessException;
import cosmetic.business.domain.Product;
import cosmetic.ui.UIUtils;
import cosmetic.utils.comparators.CrescentProductComparatorByRating;
import cosmetic.utils.comparators.DecrescentProductComparatorByRating;

public class ProductSelectionCommand extends Command{

	ProductManagementService productManagementService;
	
	public ProductSelectionCommand(ProductManagementService productManagementService) {
		this.productManagementService = productManagementService;
	}

	@Override
	public void execute() throws BusinessException {
		UIUtils uiUtils = UIUtils.INSTANCE;
		String committeeName = uiUtils.readString("message.choose.committee");
		
		List<Product> acceptableProducts = productManagementService.getAcceptableProducts(committeeName);	
		Collections.sort(acceptableProducts, new DecrescentProductComparatorByRating());
		printAcceptableList(acceptableProducts);
		
		List<Product> unacceptableProducts = productManagementService.getUnacceptableProducts(committeeName);
		Collections.sort(acceptableProducts, new CrescentProductComparatorByRating());
		printUnacceptableList(unacceptableProducts);
	}
	
	private void printAcceptableList(Collection<Product> products) throws BusinessException {
		System.out.println(UIUtils.INSTANCE.getTextManager().getText("list.acceptableProducts"));
		printProductList(products);
	}
	
	private void printUnacceptableList(Collection<Product> products) throws BusinessException {
		System.out.println(UIUtils.INSTANCE.getTextManager().getText("list.unacceptableProducts"));
		printProductList(products);
	}
	
	private void printProductList(Collection<Product> products) throws BusinessException {
		StringBuffer sb = new StringBuffer();
		sb.append(getTextManager().getText("id")).append("\t\t");
		sb.append(getTextManager().getText("rating")).append("\t\t");
		sb.append(getTextManager().getText("name")).append("\n");
		sb.append("------------------------------------------------------------------------").append("\n");
		for(Product product : products) {
			sb.append(product.getId()).append("\t\t");
			sb.append(product.getEvalutionsMean()).append("\t\t");
			sb.append(product.getName()).append("\n");
		}
		System.out.println(sb);
	}

}
