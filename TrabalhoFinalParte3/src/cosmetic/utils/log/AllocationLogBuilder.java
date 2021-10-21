package cosmetic.utils.log;

import cosmetic.ui.TextManager;
import cosmetic.ui.UIUtils;

public class AllocationLogBuilder implements LogBuilder{
	
	private TextManager textManager;
	private StringBuffer log;
	
	public AllocationLogBuilder(){
		this.textManager = UIUtils.INSTANCE.getTextManager();
		startLog();
	}

	@Override
	public void addLogLine(String[] args) {
		this.log.append(this.textManager.getText("log.productAllocation", args, false)).append("\n");
	}

	@Override
	public void addExceptionLine(String message) {
		this.log.append(this.textManager.getText(message)).append("\n");
	}

	@Override
	public String getText() {
		return this.log.append(this.textManager.getText("log.end.allocation")).toString();
	}

	@Override
	public void startLog() {
		this.log = new StringBuffer();
		this.log.append(this.textManager.getText("log.start.allocation")).append("\n");
	}

}
