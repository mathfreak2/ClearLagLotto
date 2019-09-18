package cat.math.clearlaglotto.commands;

import cat.math.clearlaglotto.ClearLagLotto;
import org.bukkit.ChatColor;
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

                        switch (args[1].toLowerCase()){
                            case "winning-condition":{

                                List<String> list = new ArrayList<>();

                                list.add("absolute");
                                list.add("blackjack");
                                list.add("exact");

                                return list;
                            }

                            case "randomize-frequency":
                            case "zero-activation-warning": {

                                List<String> list = new ArrayList<>();

                                list.add("true");
                                list.add("false");

                                return list;

                            }

                            case "entry-confirm":
                            case "entry-no-money":
                            case "start-lotto":
                            case "no-lotto":
                            case "lotto-win":
                            case "lotto-no-win":
                            case "lotto-jackpot":{

                                List<String> list = new ArrayList<>();

                                list.add("<message>");

                                return list;
                            }
                            case "activation-chance":{
                                List<String> list = new ArrayList<>();

                                list.add("0");
                                list.add("0.25");
                                list.add("0.50");
                                list.add("0.75");
                                list.add("1");

                                return list;
                            }
                            case "iterations-to-activate":{

                                List<String> list = new ArrayList<>();

                                list.add("0");
                                list.add("1");
                                list.add("5");
                                list.add("10");

                                return list;
                            }
                            case "pot-multiplier":{

                                List<String> list = new ArrayList<>();

                                list.add("0.5");
                                list.add("1");
                                list.add("2");


                                return list;

                            }
                            case "entry-cost":
                            case "pot-adder":{

                                List<String> list = new ArrayList<>();

                                list.add("<amount>");

                                return list;

                            }
                            case "jackpot-multiplier":{

                                List<String> list = new ArrayList<>();

                                list.add("4");
                                list.add("3");
                                list.add("2");
                                list.add("1");

                                return list;
                            }

                            case "seconds-before-clearlag":{

                                List<String> list = new ArrayList<>();

                                list.add("<seconds>");

                                return list;

                            }

                            default:{
                                return null;
                            }
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

		if(args[0].equalsIgnoreCase("config")) {

		    if(args.length == 3) {


                switch (args[1].toLowerCase()) {
                    case "winning-condition": {

                        if(args[2].equalsIgnoreCase("absolute") || args[2].equalsIgnoreCase("blackjack") || args[2].equalsIgnoreCase("exact")){

                            String input = args[2];

                            plugin.setWinningCondition(input);
                            plugin.editConfig();
                            sender.sendMessage(ChatColor.GREEN + "winning-condition updated with: " + input);
                            return true;
                        }

                    }

                    case "randomize-frequency":{

                        if(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")){

                            boolean input = Boolean.parseBoolean(args[2]);

                            plugin.setRandomizingFrequency(input);
                            plugin.editConfig();
                            sender.sendMessage(ChatColor.GREEN + "randomize-frequency updated with: " + input);
                            return true;
                        }

                    }

                    case "zero-activation-warning": {

                        if(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")){

                            boolean input = Boolean.parseBoolean(args[2]);

                            plugin.setZero_activation_warning(input);
                            plugin.editConfig();
                            sender.sendMessage(ChatColor.GREEN + "zero-activation-warning updated with: " + input);
                            return true;

                        }

                    }

                    case "entry-confirm":{
                        //TODO argument concat for messages
                    }

                    case "entry-no-money":{
                        //TODO argument concat for messages
                    }

                    case "start-lotto":{
                        //TODO argument concat for messages

                    }

                    case "no-lotto":{
                        //TODO argument concat for messages

                    }

                    case "lotto-win":{
                        //TODO argument concat for messages

                    }

                    case "lotto-no-win":{
                        //TODO argument concat for messages

                    }

                    case "lotto-jackpot": {
                        //TODO argument concat for messages

                    }

                    case "activation-chance": {
                        if(Double.valueOf(args[2]).doubleValue() >= 0 && Double.valueOf(args[2]).doubleValue() <= 1){

                            double num = Double.valueOf(args[2]).doubleValue();
                            plugin.setActivationChance(num);
                            plugin.editConfig();
                            sender.sendMessage(ChatColor.GREEN + "activation-chance updated with: " + num);
                            return true;
                        }

                    }

                    case "iterations-to-activate": {

                        if(Integer.valueOf(args[2]).intValue() >= 0){

                            int num = Integer.valueOf(args[2]).intValue();
                            plugin.setIterationsToActivate(num);
                            plugin.editConfig();
                            sender.sendMessage(ChatColor.GREEN + "iterations-to-activate has been updated with: " + num);
                            return true;
                        }

                    }

                    case "pot-multiplier": {

                        if(Double.valueOf(args[2]).doubleValue() >= 0 && Double.valueOf(args[2]).doubleValue() <= 10){

                            double num = Double.valueOf(args[2]).doubleValue();
                            plugin.setPotMultiplier(num);
                            plugin.editConfig();
                            sender.sendMessage(ChatColor.GREEN + "pot-multiplier updated with: " + num);
                            return true;
                        }

                    }

                    case "pot-adder": {

                        if(Double.valueOf(args[2]).doubleValue() >= 0 && Double.valueOf(args[2]).doubleValue() <= 10){

                            double num = Double.valueOf(args[2]).doubleValue();
                            plugin.setPotAdder(num);
                            plugin.editConfig();
                            sender.sendMessage(ChatColor.GREEN + "pot-adder updated with: " + num);
                            return true;
                        }

                    }
                    case "jackpot-multiplier": {

                        if(Double.valueOf(args[2]).doubleValue() >= 0 && Double.valueOf(args[2]).doubleValue() <= 10){

                            double num = Double.valueOf(args[2]).doubleValue();
                            plugin.setJackpotMultiplier(num);
                            plugin.editConfig();
                            sender.sendMessage(ChatColor.GREEN + "jackpot-multiplier updated with: " + num);
                            return true;
                        }

                    }

                    case "seconds-before-clearlag": {

                        if(Integer.valueOf(args[2]).intValue() >= 0){

                            int num = Integer.valueOf(args[2]).intValue();
                            plugin.setSecondsBeforeClearLag(num);
                            plugin.editConfig();
                            sender.sendMessage(ChatColor.GREEN + "seconds-before-clearlag has been updated with: " + num);
                            return true;
                        }

                    }
                    case "entry-cost":{
                        if(Double.valueOf(args[2]).doubleValue() >= 0 && Double.valueOf(args[2]).doubleValue() <= 1){

                            double num = Double.valueOf(args[2]).doubleValue();
                            plugin.setEntryCost(num);
                            plugin.editConfig();
                            sender.sendMessage(ChatColor.GREEN + "entry-cost updated with: " + num);
                            return true;
                        }
                    }

                    default: {
                        sender.sendMessage(ChatColor.RED + "Please enter a valid config option or config input.");
                        return false;
                    }
                }

            }



        }
		
		return false;
	}

}
