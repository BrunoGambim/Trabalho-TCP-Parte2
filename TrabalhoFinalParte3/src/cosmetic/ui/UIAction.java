package cosmetic.ui;

import cosmetic.business.domain.BusinessException;

public interface UIAction {

	public abstract void execute() throws BusinessException;
	
}
