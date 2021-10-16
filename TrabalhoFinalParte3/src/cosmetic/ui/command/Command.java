package cosmetic.ui.command;

import cosmetic.ui.TextManager;
import cosmetic.ui.UIUtils;

public abstract class Command {
	
	protected TextManager getTextManager() {
		return UIUtils.INSTANCE.getTextManager();
	}
	
	public abstract void execute();

}
