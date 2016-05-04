package br.com.casadocodigo.loja.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class BundleUtil {

	private static ResourceBundle resourceBundle;

	static {
		resourceBundle = ResourceBundle.getBundle("messages", new Locale("en", "US"));
	}
	
	public static String get(String key){
		return resourceBundle.getString(key);
	}
}