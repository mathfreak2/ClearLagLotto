package cat.math.clearlaglotto;

import org.bukkit.ChatColor;

public class Util {
	
	public static String colorMessage(String message) {
		
		
		message = ChatColor.translateAlternateColorCodes('&', message);
		
		return message;
	}
}
