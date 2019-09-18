package cat.math.clearlaglotto.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.InvalidConfigurationException;

import cat.math.clearlaglotto.ClearLagLotto;
import cat.math.clearlaglotto.Util;

public class Cll implements TabExecutor {

	ClearLagLotto plugin;
	
	public Cll(ClearLagLotto plugin) {
		this.plugin = plugin;
	}
	
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// TODO Auto-generated method stub
		
		if(args.length == 0) return false;
		
		if(args[0].equalsIgnoreCase("reload")) {
			plugin.loadConfig();
			sender.sendMessage("Config reloaded");
			return true;
		}
		
		return false;
	}

}
