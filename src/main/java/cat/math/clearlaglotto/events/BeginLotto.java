package cat.math.clearlaglotto.events;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;

import cat.math.clearlaglotto.ClearLagLotto;
import cat.math.clearlaglotto.Entry;
import cat.math.clearlaglotto.Util;

public class BeginLotto extends Event {

	private static final HandlerList handlers = new HandlerList();
	public static boolean isRunning = false;
	private ClearLagLotto plugin;
	
	// Values for the list of entries and how much is in the pot
	private ArrayList<Entry> entries = new ArrayList<Entry>();
	private double pot;
	
	public BeginLotto(ClearLagLotto plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return handlers;
	}
	
    public static HandlerList getHandlerList() {
        return handlers;
    }
    
    public void runEvent() {
    	
    	Bukkit.getServer().getPluginManager().callEvent(this);
    	isRunning = true;
    	Bukkit.getServer().broadcastMessage(Util.colorMessage(plugin.getStartLottoMessage()));
    }
    
	public ArrayList<Entry> getEntryList() {return entries;}
	public double getPot() {return pot;}

	public void setPot(double d) {pot = d;}
	
	public void addToPot(double d) {pot += d;}
	
	public void addEntry(Entry entry) {
		
		OfflinePlayer player = entry.getPlayer();
		
		for(Entry e : entries) {
			if(e.getPlayer().getName().equals(player.getName())) {
				e.changeEntry(entry.getEntry());
				return;
			}
		}
		
		Essentials essentials = plugin.getEssentials();
		Iterable<User> users = essentials.getOnlineUsers();
		for(User u : users) {
			if(u.getName().equals(player.getName())) {
				BigDecimal balance = u.getMoney();
				BigDecimal entrycost = new BigDecimal(plugin.getEntryCost());
				if(balance.compareTo(entrycost) < 0) {
					u.sendMessage(Util.colorMessage(plugin.getEntryNoMoneyMessage()));
					return;
				}
				break;
			}
		}
		
		entries.add(entry);
		double addtopot = plugin.getPotMultiplier()*plugin.getEntryCost()+plugin.getPotAdder();
		addToPot(addtopot);
		
		if(plugin.getEntryCost() != 0) {
			CommandSender sender = (CommandSender)Bukkit.getServer().getConsoleSender();
			Bukkit.getServer().dispatchCommand(sender, "eco take "+player.getName()+" "+plugin.getEntryCost());
		}
	}
	
	public void removeAllEntries() {
		
		entries = new ArrayList<Entry>();
	}
}