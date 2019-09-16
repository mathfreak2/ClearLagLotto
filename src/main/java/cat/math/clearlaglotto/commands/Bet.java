package cat.math.clearlaglotto.commands;

import cat.math.clearlaglotto.ClearLagLotto;
import cat.math.clearlaglotto.events.BeginLotto;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class Bet implements TabExecutor {
	
	ClearLagLotto clearlaglotto;
	
	public Bet(ClearLagLotto clearlaglotto) {
		this.clearlaglotto = clearlaglotto;
	}
	
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!BeginLotto.isRunning) {
            sender.sendMessage(clearlaglotto.getWinningCondition());
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

        // Needs to create an entry and add it to the BeginLotto event

        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }

    private int stringToInt(String s) {

        int result = 0;
        try {
            for(int i=0; i<s.length(); i++) {
                double m = Character.getNumericValue(s.charAt(i));
                double m1 = Math.pow(10, s.length()-i-1)*m;
                if(!(m == 0 || m == 1 || m == 2 || m == 3 || m == 4 || m == 5 || m == 6 || m == 7 || m == 8 || m == 9))
                    return -1;
                result += m1;
            }
        }
        catch(Exception e) {
            return -1;
        }

        return result;
    }

}
