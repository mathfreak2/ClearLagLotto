package cat.math.clearlaglotto.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import cat.math.clearlaglotto.ClearLagLotto;
import cat.math.clearlaglotto.events.BeginLotto;
import cat.math.clearlaglotto.events.EndLotto;
import me.minebuilders.clearlag.Clearlag;
import me.minebuilders.clearlag.events.EntityRemoveEvent;

public class ClearLagListener implements Listener {
	
	public static ClearLagLotto clearlaglotto;
	public static boolean onclearlag = false;
	
	public ClearLagListener(ClearLagLotto clearlaglotto) {
		ClearLagListener.clearlaglotto = clearlaglotto;
	}
	
	@EventHandler
	public void onClearLag(EntityRemoveEvent event) {
		
		if(onclearlag) return;
		
		int removed = event.getEntityList().size();
		
		Clearlag clearlag = clearlaglotto.getClearLag();
		FileConfiguration clearlagconfig = clearlag.getConfig();
		
		int interval = clearlagconfig.getInt("auto-removal.autoremoval-interval", 300);
		boolean randomize = clearlaglotto.isRandomizingFrequency();
		int seconds = clearlaglotto.getSecondsBeforeClearLag();
		
		boolean activate_lottery = false;
		
		// Determines whether or not there should be a lottery on next lag clearing event
		if(randomize) {
			double random = Math.random();
			double activation = clearlaglotto.getActivationChance();
			if(random < activation) activate_lottery = true;
		}
		else {
			int iteration = clearlaglotto.getIteration();
			if(iteration == clearlaglotto.getIterationsToActivate())
				activate_lottery = true;
			clearlaglotto.iterate();
		}
		
		if(BeginLotto.isRunning) {
			EndLotto ending = new EndLotto(clearlaglotto);
			ending.runEvent(removed);
		}
		
		class RunLotto implements Runnable {
			
			public ClearLagLotto plugin;
			
			public RunLotto(ClearLagLotto plugin) {
				this.plugin = plugin;
			}
			
			public void run() {
				
				ClearLagListener.onclearlag = false;
				plugin.getLottery().runEvent();
			}
		}
		
		if(activate_lottery) {
			
			BukkitScheduler bs = Bukkit.getServer().getScheduler();
			bs.runTaskLater(clearlaglotto, new RunLotto(clearlaglotto), 20*(interval-seconds));
			onclearlag = true;
		}
	}
}
