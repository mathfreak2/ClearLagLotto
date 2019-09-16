package cat.math.clearlaglotto.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

import cat.math.clearlaglotto.ClearLagLotto;

public class EndLotto extends Event {

	private static final HandlerList handlers = new HandlerList();
	private ClearLagLotto plugin;
	
	public EndLotto(ClearLagLotto plugin) {
		this.plugin = plugin;
	}
	
    public void runEvent() {
    	
    	Bukkit.getServer().getPluginManager().callEvent(this);
    	BeginLotto.isRunning = false;
    	String message;
    }
	
	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return null;
	}

}
