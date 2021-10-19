package cosmetic;

import cosmetic.business.ProductAllocationService;
import cosmetic.business.ProductManagementService;
import cosmetic.business.domain.BusinessException;
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
		Database database = null;
		try {
			database = new Database();
		} catch (BusinessException e) {
			System.out.println(e.getMessage());
		}

		ProductAllocationService productAllocationService = new ProductAllocationServiceImpl(database);
		ProductManagementService productManagementService = new ProductManagementServiceImpl(database);
		
		cosmeticInterface = new CosmeticTextInterface(productAllocationService,productManagementService);
	}

}
