package cat.math.clearlaglotto.events;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

import cat.math.clearlaglotto.Entry;

public class BeginLotto extends Event {

	private static final HandlerList handlers = new HandlerList();
	public static boolean isRunning = false;
	private String message;
	private Plugin plugin;
	
	// Values for the list of entries and how much is in the pot
	private ArrayList<Entry> entries = new ArrayList<Entry>();
	private double pot;
	
	public BeginLotto(String message, Plugin plugin) {
		this.message = message;
		this.plugin = plugin;
	}
	
	public String getMessage() {return message;}
	
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
    	Bukkit.getServer().broadcastMessage(message);
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
		
		entries.add(entry);
	}
}