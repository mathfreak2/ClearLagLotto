package cat.math.clearlaglotto.events;

import cat.math.clearlaglotto.ClearLagLotto;
import cat.math.clearlaglotto.Entry;
import cat.math.clearlaglotto.Util;
import com.earth2me.essentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.ArrayList;

public class EndLotto extends Event {

	private static final HandlerList handlers = new HandlerList();
	private ClearLagLotto plugin;
	
	public EndLotto(ClearLagLotto plugin) {
		this.plugin = plugin;
	}
	
    public void runEvent(int removed) {
    	
    	Bukkit.getServer().getPluginManager().callEvent(this);
    	BeginLotto.isRunning = false;
    	String message = Util.colorMessage(plugin.getLottoWinMessage());
    	ArrayList<Entry> entries = plugin.getLottery().getEntryList();
    	String winning_condition = plugin.getWinningCondition();
    	int mindifference = -1;
    	Entry winner = null;
    	
    	for(Entry e : entries) {
    		
    		if(winning_condition.equals("exact") && e.getEntry() != removed) continue;
    		if(winning_condition.equals("blackjack") && e.getEntry() > removed) continue;
    		int difference = Math.abs(e.getEntry()-removed);
    		if(mindifference == -1) {
    			mindifference = difference;
    			winner = e;
    		}
    		else {
    			if(difference < mindifference) {
    				mindifference = difference;
    				winner = e;
    			}
    			else if(difference == mindifference) {
    				if(e.getEntryTime() < winner.getEntryTime()) {
    					winner = e;
    				}
    			}
    		}
    	}
    	
    	if(winner == null) Bukkit.getServer().broadcastMessage(Util.colorMessage(plugin.getLottoNoWinMessage()));
    	else {
    		
    		double winning = plugin.getLottery().getPot();
    		if(winner.getEntry() == removed) {
    			winning *= plugin.getJackpotMultiplier();
    			message = Util.colorMessage(plugin.getLottoJackpotMessage());
    		}
    		String winnings = String.format("%.2f", winning);
    		message = message.replaceAll("%name%",winner.getPlayer().getName());
    		message = message.replaceAll("%guess%", ""+winner.getEntry());
    		message = message.replaceAll("%winnings%", ""+winnings);
    		Bukkit.getServer().broadcastMessage(message);
    		
    		Essentials essentials = plugin.getEssentials();
    		CommandSender sender = (CommandSender)Bukkit.getServer().getConsoleSender();
    		Bukkit.getServer().dispatchCommand(sender, "eco give "+winner.getPlayer().getName()+" "+winnings);
    		plugin.getLottery().setPot(0);
    		plugin.getLottery().removeAllEntries();
    		
    	}
    }
	
	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return handlers;
	}

}
