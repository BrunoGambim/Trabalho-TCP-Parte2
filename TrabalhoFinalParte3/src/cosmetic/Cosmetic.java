package cosmetic;

import cosmetic.business.ProductManagementService;
import cosmetic.business.domain.BusinessException;
import cosmetic.ui.CosmeticInterface;
import cosmetic.ui.UIUtils;
import cosmetic.ui.impl.CosmeticTextInterface;
import cosmetic.business.impl.ProductManagementServiceImpl;
import cosmetic.database.Database;
import cosmetic.database.DatabaseImpl;

public class Cosmetic {

	CosmeticInterface cosmeticInterface;
	
	public static void main(String[] args) {
		Cosmetic cosmetic = new Cosmetic();
		
		cosmetic.cosmeticInterface.createAndShowUI();
	}
	
	public Cosmetic() {
		Database database = null;
		UIUtils uiUtils = UIUtils.INSTANCE;
		
		try {
			database = new DatabaseImpl();
		} catch (BusinessException be) {
			System.out.println(uiUtils.getTextManager().getText(
					be.getMessage()));
		} catch (Exception e) {
			uiUtils.handleUnexceptedError(e);
		}

		ProductManagementService productManagementService = new ProductManagementServiceImpl(database);
		
		cosmeticInterface = new CosmeticTextInterface(productManagementService);
	}

}
