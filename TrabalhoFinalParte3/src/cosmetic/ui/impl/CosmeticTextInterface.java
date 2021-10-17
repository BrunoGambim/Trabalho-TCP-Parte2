package cosmetic.ui.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import cosmetic.ui.CosmeticInterface;
import cosmetic.ui.TextManager;
import cosmetic.ui.UIAction;
import cosmetic.ui.UIUtils;
import cosmetic.ui.command.EvaluationCommand;
import cosmetic.ui.command.ProductAllocationCommand;
import cosmetic.ui.command.ProductSelectionCommand;
import cosmetic.business.ProductAllocationService;
import cosmetic.business.ProductManagementService;
import cosmetic.business.domain.BusinessException;

public class CosmeticTextInterface implements CosmeticInterface{
	
	protected final Map<String, UIAction> actions;
	private static final String EXIT = "E";
	
	public CosmeticTextInterface(ProductAllocationService productAllocationService, ProductManagementService productManagementService) {
		this.actions = new LinkedHashMap<>();
		this.addAction("A", new EvaluationCommand());
		this.addAction("L", new ProductAllocationCommand(productAllocationService));
		this.addAction("S", new ProductSelectionCommand());
	}
	
	private void addAction(String key, UIAction action) {
		this.actions.put(key, action);
	}

	@Override
	public void createAndShowUI() {
		UIUtils uiUtils = UIUtils.INSTANCE;
		String commandKey = null;
		do {
			printMenu(UIUtils.INSTANCE.getTextManager());
			commandKey = uiUtils.readString(null);
			UIAction command = actions.get(commandKey);
			if (command != null) {
				try {
					command.execute();
				} catch (BusinessException be) {
					System.out.println(uiUtils.getTextManager().getText(
							be.getMessage(), be.getArgs()));
				} catch (Exception e) {
					uiUtils.handleUnexceptedError(e);
				}
			}
		} while (!EXIT.equals(commandKey));
	}
	
	private void printMenu(TextManager textManager) {
		StringBuffer sb = new StringBuffer();
		sb.append(textManager.getText("message.options", EXIT, false))
				.append(":\n");
		for (String key : actions.keySet()) {
			UIAction action = actions.get(key);
			sb.append(key)
					.append(" - ")
					.append(textManager.getText(action.getClass()
							.getSimpleName())).append("\n");
		}
		sb.append(textManager.getText("message.choose.option")).append(": ");
		System.out.print(sb.toString());
	}
	
}
