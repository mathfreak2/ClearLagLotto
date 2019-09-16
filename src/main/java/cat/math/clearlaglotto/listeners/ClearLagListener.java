package cat.math.clearlaglotto.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import cat.math.clearlaglotto.ClearLagLotto;
import cat.math.clearlaglotto.events.BeginLotto;
import cat.math.clearlaglotto.events.EndLotto;
import me.minebuilders.clearlag.Clearlag;
import me.minebuilders.clearlag.events.EntityRemoveEvent;

public class ClearLagListener implements Listener {
	
	ClearLagLotto clearlaglotto;
	
	public ClearLagListener(ClearLagLotto clearlaglotto) {
		this.clearlaglotto = clearlaglotto;
		callOnPluginEnable();
	}
	
	@EventHandler
	public void onClearLag(EntityRemoveEvent event) {
		
		int removed = event.getEntityList().size();
		
		Clearlag clearlag = clearlaglotto.getClearLag();
		FileConfiguration clearlagconfig = clearlag.getConfig();
		
		int interval = clearlagconfig.getInt("auto-removal.autoremoval-interval", 300);
		boolean randomize = clearlaglotto.isRandomizingFrequency();
		
		boolean activate_lottery = false;
		
		// Determines whether or not there should be a lottery on next lag clearing event
		if(randomize) {
			double random = 100*Math.random();
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
			ending.runEvent();
		}
	}
	
	public void callOnPluginEnable() {
		onClearLag(new EntityRemoveEvent(null, null));
	}
}
