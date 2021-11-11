package cosmetic;

import cosmetic.business.ProductManagementService;
import cosmetic.business.domain.BusinessException;
import cosmetic.ui.CosmeticInterface;
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
		try {
			database = new DatabaseImpl();
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}

		ProductManagementService productManagementService = new ProductManagementServiceImpl(database);
		
		cosmeticInterface = new CosmeticTextInterface(productManagementService);
	}

}
