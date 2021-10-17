package cosmetic.ui.command;

import cosmetic.ui.TextManager;
import cosmetic.ui.UIAction;
import cosmetic.ui.UIUtils;

public abstract class Command implements UIAction{
	
	protected TextManager getTextManager() {
		return UIUtils.INSTANCE.getTextManager();
	}

}
