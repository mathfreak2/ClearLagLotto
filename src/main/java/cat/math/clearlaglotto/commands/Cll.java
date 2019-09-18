package cat.math.clearlaglotto.commands;

import cat.math.clearlaglotto.ClearLagLotto;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class Cll implements TabExecutor {

	ClearLagLotto plugin;
	
	public Cll(ClearLagLotto plugin) {
		this.plugin = plugin;
	}
	
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (command.getName().equalsIgnoreCase("clearlaglotto")) {

            if (args.length == 1) {

                List<String> list = new ArrayList<>();

                list.add("Reload");
                list.add("Config");

                return list;

            }

            if (args.length >= 2) {

                if (args[0].equalsIgnoreCase("config")) {

                    if(args.length == 2){
                        List<String> list = new ArrayList<>();

                        list.add("winning-condition");
                        list.add("randomize-frequency");
                        list.add("zero-activation-warning");
                        list.add("activation-chance");
                        list.add("iterations-to-activate");
                        list.add("entry-cost");
                        list.add("entry-confirm");
                        list.add("entry-no-money");
                        list.add("pot-multiplier");
                        list.add("pot-adder");
                        list.add("jackpot-multiplier");
                        list.add("seconds-before-clearlag");
                        list.add("start-lotto");
                        list.add("no-lotto");
                        list.add("lotto-win");
                        list.add("lotto-no-win");
                        list.add("lotto-jackpot");

                        return list;
                    }

                    if(args.length == 3){

                        if(args[1].equalsIgnoreCase("winning-condition")){

                            List<String> list = new ArrayList<>();

                            list.add("absolute");
                            list.add("blackjack");
                            list.add("exact");

                            return list;
                        }
                        if(args[1].equalsIgnoreCase("randomize-frequency")){

                            List<String> list = new ArrayList<>();

                            list.add("true");
                            list.add("false");

                            return list;
                        }
                        if(args[1].equalsIgnoreCase("zero-activation-warning")){

                            List<String> list = new ArrayList<>();

                            list.add("true");
                            list.add("false");

                            return list;
                        }
                        if(args[1].equalsIgnoreCase("activation-chance")){

                            List<String> list = new ArrayList<>();

                            list.add("0");
                            list.add("0.25");
                            list.add("0.50");
                            list.add("0.75");
                            list.add("1");

                            return list;
                        }
                        if(args[1].equalsIgnoreCase("iterations-to-activate")){

                            List<String> list = new ArrayList<>();

                            list.add("0");
                            list.add("1");
                            list.add("5");
                            list.add("10");

                            return list;
                        }
                        if(args[1].equalsIgnoreCase("entry-cost")){

                            List<String> list = new ArrayList<>();

                            list.add("<amount>");

                            return list;
                        }
                        if(args[1].equalsIgnoreCase("entry-confirm")){

                            List<String> list = new ArrayList<>();

                            list.add("<message>");

                            return list;
                        }
                        if(args[1].equalsIgnoreCase("entry-no-money")){

                            List<String> list = new ArrayList<>();

                            list.add("<message>");

                            return list;
                        }
                        if(args[1].equalsIgnoreCase("pot-multiplier")){

                            List<String> list = new ArrayList<>();

                            list.add("0.5");
                            list.add("1");
                            list.add("2");


                            return list;
                        }
                        if(args[1].equalsIgnoreCase("pot-adder")){

                            List<String> list = new ArrayList<>();

                            list.add("<amount>");

                            return list;
                        }
                        if(args[1].equalsIgnoreCase("jackpot-multiplier")){

                            List<String> list = new ArrayList<>();

                            list.add("3");
                            list.add("4");
                            list.add("2");
                            list.add("1");

                            return list;
                        }
                        if(args[1].equalsIgnoreCase("seconds-before-clearlag")){

                            List<String> list = new ArrayList<>();

                            list.add("<seconds>");

                            return list;
                        }
                        if(args[1].equalsIgnoreCase("start-lotto")){

                            List<String> list = new ArrayList<>();

                            list.add("<message>");

                            return list;
                        }
                        if(args[1].equalsIgnoreCase("no-lotto")){

                            List<String> list = new ArrayList<>();

                            list.add("<message>");

                            return list;
                        }
                        if(args[1].equalsIgnoreCase("lotto-win")){

                            List<String> list = new ArrayList<>();

                            list.add("<message>");

                            return list;
                        }
                        if(args[1].equalsIgnoreCase("lotto-no-win")){

                            List<String> list = new ArrayList<>();

                            list.add("<message>");

                            return list;
                        }
                        if(args[1].equalsIgnoreCase("lotto-jackpot")){

                            List<String> list = new ArrayList<>();

                            list.add("<message>");

                            return list;
                        }
                    }


                }

                return null;

            }



            return null;
        }
        return null;
    }

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		
		if(args.length == 0) return false;
		
		if(args[0].equalsIgnoreCase("reload")) {
			plugin.loadConfig();
			sender.sendMessage("Config reloaded");
			return true;
		}
		
		return false;
	}

}
