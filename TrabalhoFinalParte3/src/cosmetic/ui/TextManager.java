
package cosmetic.ui;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class TextManager {

	private ResourceBundle bundle;

	public TextManager(String baseName) {
		this.bundle = ResourceBundle.getBundle(baseName);
	}

	public String getText(String key) {
		String text = null;
		try {
			text = bundle.getString(key);
		} catch (MissingResourceException exc) {
			text = key;
		}
		return text;
	}

	public String getText(String key, String arg) {
		return getText(key, arg, true);
	}

	public String getText(String key, String arg, boolean prepare) {
		return getText(key, new String[] { arg }, prepare);
	}

	public String getText(String key, String[] args) {
		return getText(key, args, true);
	}

	public String getText(String key, String[] args, boolean prepare) {
		String[] newArgs = args;
		if (prepare) {
			newArgs = new String[args.length];
			for (int i = 0; i < newArgs.length; i++) {
				newArgs[i] = getText(args[i]);
			}
		}
		return new MessageFormat(getText(key)).format(newArgs);
	}

}
