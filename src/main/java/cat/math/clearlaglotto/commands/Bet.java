package cat.math.clearlaglotto.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import cat.math.clearlaglotto.ClearLagLotto;
import cat.math.clearlaglotto.Entry;
import cat.math.clearlaglotto.Util;
import cat.math.clearlaglotto.events.BeginLotto;

public class Bet implements TabExecutor {
	
	ClearLagLotto clearlaglotto;
	
	public Bet(ClearLagLotto clearlaglotto) {
		this.clearlaglotto = clearlaglotto;
	}
	
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
	        
    	if(!BeginLotto.isRunning) {
	        sender.sendMessage(clearlaglotto.getNoLottoMessage());
	        return true;
	    }
	
	    if(!(sender instanceof Player) || !sender.hasPermission("clearlaglotto.bet")) {
	        sender.sendMessage("There is a lottery currently in progress, but you may not participate!");
	        return true;
	    }
	
	    OfflinePlayer player = (OfflinePlayer) sender;
	
	    if(args.length == 0) return false;
	    int bet = stringToInt(args[0]);
	    if(bet < 0) return false;
	        
	    Entry entry = new Entry(player, bet);
	    BeginLotto lotto = clearlaglotto.getLottery();
	    lotto.addEntry(entry);
	    
	    sender.sendMessage(Util.colorMessage(clearlaglotto.getEntryConfirmMessage()));
	
	    return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }

    private int stringToInt(String s) {

        int result = 0;
        try {
            for(int i=0; i<s.length(); i++) {
                double m;
                if(s.charAt(i) == '0') m = 0;
                else if(s.charAt(i) == '1') m = 1;
                else if(s.charAt(i) == '2') m = 2;
                else if(s.charAt(i) == '3') m = 3;
                else if(s.charAt(i) == '4') m = 4;
                else if(s.charAt(i) == '5') m = 5;
                else if(s.charAt(i) == '6') m = 6;
                else if(s.charAt(i) == '7') m = 7;
                else if(s.charAt(i) == '8') m = 8;
                else if(s.charAt(i) == '9') m = 9;
                else return -1;
                result += Math.pow(10, s.length()-i-1)*m;
            }
        }
        catch(Exception e) {
            return -1;
        }

        return result;
    }

}
