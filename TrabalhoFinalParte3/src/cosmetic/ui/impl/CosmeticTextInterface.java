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
import cosmetic.business.ProductManagementService;
import cosmetic.business.domain.BusinessException;

public class CosmeticTextInterface implements CosmeticInterface{
	
	protected final Map<String, UIAction> actions;
	private static final String EXIT = "E";
	private static final UIUtils UIUTILS = UIUtils.INSTANCE;
	
	public CosmeticTextInterface(ProductManagementService productManagementService) {
		this.actions = new LinkedHashMap<>();
		this.addAction("A", new EvaluationCommand(productManagementService));
		this.addAction("L", new ProductAllocationCommand(productManagementService));
		this.addAction("S", new ProductSelectionCommand(productManagementService));
	}
	
	private void addAction(String key, UIAction action) {
		this.actions.put(key, action);
	}

	@Override
	public void createAndShowUI() {
		String commandKey = null;
		do {
			printMenu();
			commandKey = UIUTILS.readString(null);
			UIAction command = actions.get(commandKey);
			if (command != null) {
				try {
					command.execute();
				} catch (BusinessException be) {
					System.out.println(UIUTILS.getTextManager().getText(
							be.getMessage()));
				} catch (Exception e) {
					UIUTILS.handleUnexceptedError(e);
				}
			}
		} while (!EXIT.equals(commandKey));
	}
	
	private void printMenu() {
		TextManager textManager = UIUTILS.getTextManager();
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
