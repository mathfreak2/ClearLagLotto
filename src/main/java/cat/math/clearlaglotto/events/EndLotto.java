package cat.math.clearlaglotto.events;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

public class EndLotto extends Event {

	private static final HandlerList handlers = new HandlerList();
	private String message;
	private Plugin plugin;
	private BeginLotto lotto;
	
	EndLotto(String message, Plugin plugin, BeginLotto lotto) {
		this.message = message;
		this.plugin = plugin;
		this.lotto = lotto;
	}
	
    public void runEvent() {
    	
    	Bukkit.getServer().getPluginManager().callEvent(this);
    	BeginLotto.isRunning = false;
    	Bukkit.getServer().broadcastMessage(message);
    }
	
	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return null;
	}

}
