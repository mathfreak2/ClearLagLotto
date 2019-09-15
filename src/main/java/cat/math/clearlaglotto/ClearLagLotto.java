package cat.math.clearlaglotto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;

import commands.bet;
import commands.cll;
import commands.pot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import cat.math.clearlaglotto.events.BeginLotto;
import me.minebuilders.clearlag.*;
import net.milkbowl.vault.*;
import com.earth2me.essentials.*;

public class ClearLagLotto extends JavaPlugin {
	
	private Vault vault;
	private Clearlag clearlag;
	private Essentials essentials;
	
	private BeginLotto lotto;
	
	// Variables loaded in from the config.yml
	static private String winning_condition;
	static private boolean randomize_frequency;
	static private boolean zero_activation_warning;
	static private double activation_chance;
	static private int iterations_to_activate;
	static private double entry_cost;
	static private String entry_confirm;
	static private String entry_no_money;
	static private double pot_multiplier;
	static private double pot_adder;
	static private double jackpot_multiplier;
	static private int seconds_before_clearlag;
	static private String start_lotto;
	static private String no_lotto;
	
	// Gives access to the associated plugins this one is dependent on
	public Plugin getVault() {return vault;}
	public Plugin getClearLag() {return clearlag;}
	public Plugin getEssentials() {return essentials;}
	
	// Getter methods for config related values
	static public String getWinningCondition() {return winning_condition;}
	static public String getEntryConfirmMessage() {return entry_confirm;}
	static public String getEntryNoMoneyMessage() {return entry_no_money;}
	static public String getStartLottoMessage() {return start_lotto;}
	static public String getNoLottoMessage() {return no_lotto;}
	static public double getActivationChance() {return activation_chance;}
	static public double getEntryCost() {return entry_cost;}
	static public double getPotMultiplier() {return pot_multiplier;}
	static public double getPotAdder() {return pot_adder;}
	static public double getJackpotMultiplier() {return jackpot_multiplier;}
	static public int getIterationsToActivate() {return iterations_to_activate;}
	static public int getSecondsBeforeClearLag() {return seconds_before_clearlag;}
	static public boolean isRandomizingFrequency() {return randomize_frequency;}
	
	// Setter methods for config related values
	public void setWinningCondition(String s) {winning_condition = s;}
	public void setEntryConfirmMessage(String s) {entry_confirm = s;}
	public void setEntryNoMoneyMessage(String s) {entry_no_money = s;}
	public void setStartLottoMessage(String s) {start_lotto = s;}
	public void setNoLottoMessage(String s) {no_lotto = s;}
	public void setActivationChance(double d) {activation_chance = d;}
	public void setEntryCost(double d) {entry_cost = d;}
	public void setPotMultiplier(double d) {pot_multiplier = d;}
	public void setPotAdder(double d) {pot_adder = d;}
	public void setJackpotMultiplier(double d) {jackpot_multiplier = d;}
	public void setIterationsToActivate(int i) {iterations_to_activate = i;}
	public void setSecondsBeforeClearLag(int i) {seconds_before_clearlag = i;}
	public void setRandomizingFrequency(boolean b) {randomize_frequency = b;}
	
	@Override
	public void onEnable() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		Plugin cl = pm.getPlugin("ClearLag");
		Plugin v = pm.getPlugin("Vault");
		Plugin e = pm.getPlugin("Essentials");
		if(cl == null || v == null || e == null) {
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
		clearlag = (Clearlag) cl;
		vault = (Vault) v;
		essentials = (Essentials) e;

		getCommand("pot").setExecutor(new pot());
		getCommand("clearlaglotto").setExecutor(new cll());
		getCommand("bet").setExecutor(new bet());

		loadConfig();
		registerEvents();
	}
	
	private void registerEvents() {
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
	}
	
	private void loadConfig() {
		
		File directory = getDataFolder();
		
		try {
			
			if(!directory.exists())
				directory.mkdir();
			
			File config = new File(directory, "config.yml");
			if(!config.exists()) this.saveDefaultConfig();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		FileConfiguration config = this.getConfig();
		
		// Load strings from config file
		winning_condition = config.getString("winning-condition", "absolute");
		entry_confirm = config.getString("entry-confirm", ChatColor.GREEN + "You have entered a guess into the lottery!");
		entry_no_money = config.getString("entry-no-money", ChatColor.RED + "You do not have enough money.");
		start_lotto = config.getString("start-lotto", ChatColor.GREEN + "May the guessing begin!" +
		ChatColor.RED + "Type " + ChatColor.YELLOW + "/bet [amount] " + ChatColor.RED + "to enter in a guess for the number of entities removed!");
		no_lotto = config.getString("no-lotto", ChatColor.RED + "No lottery is currently in progress.");
		
		// Load doubles from config file
		activation_chance = config.getDouble("activation-chance", 1.0);
		entry_cost = config.getDouble("entry-cost", 100.0);
		pot_multiplier = config.getDouble("pot-multiplier", 1.0);
		pot_adder = config.getDouble("pot-adder", 0.0);
		jackpot_multiplier = config.getDouble("jackpot_multiplier", 3.0);
		
		// Load integers from config file
		iterations_to_activate = config.getInt("iterations-to-activate", 1);
		seconds_before_clearlag = config.getInt("seconds-before-clearlag", 60);
		
		// Load booleans from config file
		randomize_frequency = config.getBoolean("randomize-frequency", true);
		zero_activation_warning = config.getBoolean("zero-activation-warning", true);
		
		getLogger().log(Level.INFO, "Config loaded!");
		
		if((iterations_to_activate == 0 || activation_chance == 0) && zero_activation_warning) {
			getLogger().log(Level.WARNING, "Either iterations-to-activate or activation-chance in the config.yml is set to 0 and the lottery may be turned off.");
		}
		
	}
	
	@Override
	public void onDisable() {
		
		editConfig();
	}
	
	public void editConfig() {
		
		FileConfiguration config = this.getConfig();
		
		config.set("winning-condition", winning_condition);
		config.set("entry-confirm", entry_confirm);
		config.set("entry-no-money", entry_no_money);
		config.set("start-lotto", start_lotto);
		config.set("no-lotto", no_lotto);
		
		config.set("activation-chance", activation_chance);
		config.set("entry-cost", entry_cost);
		config.set("pot-multiplier", pot_multiplier);
		config.set("pot-adder", pot_adder);
		config.set("jackpot-multiplier", jackpot_multiplier);
		
		config.set("iterations-to-activate", iterations_to_activate);
		config.set("seconds-before-clearlag", seconds_before_clearlag);
		
		config.set("randomize-frequency", randomize_frequency);
		config.set("zero-activation-warning", zero_activation_warning);
		
		this.saveConfig();
	}


}