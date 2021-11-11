
package cosmetic.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UIUtils {
	public static final UIUtils INSTANCE = new UIUtils();
	public static final String PROPERTY_RESOURCE_BUNDLE = "cosmetic.resources.globalMessages";
	private final BufferedReader reader;
	private final TextManager textManager;

	private UIUtils() {
	
		this.reader = new BufferedReader(new InputStreamReader(System.in));
		this.textManager = new TextManager(PROPERTY_RESOURCE_BUNDLE);
	}

	public TextManager getTextManager() {
		return textManager;
	}

	public void handleUnexceptedError(Exception e) {
		System.out.println(textManager.getText("exception.unexpected"));
		
		e.printStackTrace();
		System.exit(-1);
	}

	public Double readDouble(String field) {
		Double value = null;
		while (value == null) {
			try {
				System.out.print(textManager.getText(field) + ": ");
				value = new Double(reader.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println(textManager
						.getText("exception.double.format"));

			} catch (Exception e) {
				handleUnexceptedError(e);
			}
		}
		return value;
	}
	
	public Float readFloat(String field) {
		Float value = null;
		while (value == null) {
			try {
				System.out.print(textManager.getText(field) + ": ");
				value = new Float(reader.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println(textManager
						.getText("exception.double.format"));

			} catch (Exception e) {
				handleUnexceptedError(e);
			}
		}
		return value;
	}

	public Integer readInteger(String field) {
		Integer value = null;
		while (value == null) {
			try {
				if (field != null)
					System.out.print(textManager.getText(field) + ": ");
				value = new Integer(reader.readLine());
			} catch (NumberFormatException nfe) {
				System.out.println(textManager
						.getText("exception.integer.format"));
				
			} catch (Exception e) {
				handleUnexceptedError(e);
			}
		}
		return value;
	}

	public Integer readInteger(String field, int min, int max) {
		Integer value = null;
		while (value == null) {
			value = readInteger(field);
			if (value < min || value > max) {
				value = null;
				System.out.println(textManager.getText(
						"exception.integer.range", new String[] { "" + min,
								"" + max }, false));
			}
		}
		return value;
	}

	public Long readLong(String field) {
		Long value = null;
		while (value == null) {
			try {
				System.out.print(textManager.getText(field) + ": ");
				value = new Long(reader.readLine());
			} catch (NumberFormatException nfe) {
				System.out
						.println(textManager.getText("exception.long.format"));
				
			} catch (Exception e) {
				handleUnexceptedError(e);
			}
		}
		return value;
	}

	public String readString(String field) {
		String value = null;
		while (value == null) {
			try {
				if (field != null)
					System.out.print(textManager.getText(field) + ": ");
				value = reader.readLine();
			} catch (Exception e) {
				handleUnexceptedError(e);
			}
		}
		return value;
	}

}
