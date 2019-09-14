package cat.math.clearlaglotto;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import me.minebuilders.clearlag.Clearlag;

public class ClearLagLotto extends JavaPlugin {
	
	@Override
	public void onEnable() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		Plugin clearlag = pm.getPlugin("ClearLag");
		Plugin vault = pm.getPlugin("Vault");
		Plugin essentials = pm.getPlugin("Essentials");
		if(clearlag == null || vault == null || essentials == null) {
			getLogger().log(Level.SEVERE, "Cannot find dependent plugins. Must include, Essentials, Vault, and ClearLag.");
			StringBuilder pluginlist = new StringBuilder();
			Plugin[] list = pm.getPlugins();
			for(int i=0; i<list.length;i++) {
				pluginlist.append(list[i].getName());
				if(i!=list.length-1) pluginlist.append(", ");
			}
			getLogger().log(Level.SEVERE, "Currently installed plugins include: [" + new String(pluginlist) + "]");
			return;
		}
		getLogger().log(Level.INFO, "[ClearLagLotto] Enabling ClearLagLotto");
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		return false;
	}
}
