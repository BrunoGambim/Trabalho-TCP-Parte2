package cosmetic;

import cosmetic.business.ProductAllocationService;
import cosmetic.business.ProductManagementService;
import cosmetic.ui.CosmeticInterface;
import cosmetic.ui.impl.CosmeticTextInterface;
import cosmetic.business.impl.ProductAllocationServiceImpl;
import cosmetic.business.impl.ProductManagementServiceImpl;
import cosmetic.database.Database;

public class Cosmetic {

	CosmeticInterface cosmeticInterface;
	
	public static void main(String[] args) {
		Cosmetic cosmetic = new Cosmetic();
		
		cosmetic.cosmeticInterface.createAndShowUI();
	}
	
	public Cosmetic() {
		Database database = new Database();

		ProductAllocationService productAllocationService = new ProductAllocationServiceImpl(database);
		ProductManagementService productManagementService = new ProductManagementServiceImpl();
		
		cosmeticInterface = new CosmeticTextInterface(productAllocationService,productManagementService);
	}

}
