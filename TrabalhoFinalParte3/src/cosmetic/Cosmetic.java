package cosmetic;

import cosmetic.ui.CosmeticInterface;
import cosmetic.ui.impl.CosmeticTextInterface;

public class Cosmetic {

	CosmeticInterface cosmeticInterface;
	
	public static void main(String[] args) {
		Cosmetic cosmetic = new Cosmetic();
		
		cosmetic.cosmeticInterface.createAndShowUI();
	}
	
	public Cosmetic() {
		cosmeticInterface = new CosmeticTextInterface();
	}

}
