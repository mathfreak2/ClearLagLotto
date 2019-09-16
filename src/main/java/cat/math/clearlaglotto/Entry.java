package cat.math.clearlaglotto;

import org.bukkit.OfflinePlayer;

public class Entry {
	
	private OfflinePlayer player;
	private int entry;
	private long entry_time;
	
	public Entry(OfflinePlayer player, int entry) {
		
		this.player = player;
		this.entry = entry;
		entry_time = System.currentTimeMillis();
	}
	
	public OfflinePlayer getPlayer() {return player;}
	public int getEntry() {return entry;}
	public long getEntryTime() {return entry_time;}
	
	public void changeEntry(int i) {
		entry = i;
		entry_time = System.currentTimeMillis();
	}
}
