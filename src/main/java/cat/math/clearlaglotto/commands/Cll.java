package cat.math.clearlaglotto.commands;

import cat.math.clearlaglotto.ClearLagLotto;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Cll implements TabExecutor {

	ClearLagLotto plugin;
	
	public Cll(ClearLagLotto plugin) {
		this.plugin = plugin;
	}

    String help_config_dialog_1 = ChatColor.LIGHT_PURPLE + "-=-=-=-=["+ ChatColor.WHITE +"ClearLagLotto"+ChatColor.GREEN+" Page 1"+ChatColor.WHITE+"/" + ChatColor.GREEN+"3"+ChatColor.LIGHT_PURPLE+"]=-=-=-=-\n" +
            ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll Config " + ChatColor.LIGHT_PURPLE + "winning-condition " + ChatColor.GRAY +"absolute"+ChatColor.ITALIC + "\n Defines how the lotto will be winnable\n" +
            ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll Config " + ChatColor.LIGHT_PURPLE + "randomize-frequency " + ChatColor.GRAY +"true"+ChatColor.ITALIC + "\n Defines if frequency will be random or not\n" +
            ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll Config " + ChatColor.LIGHT_PURPLE + "zero-activation-warning " + ChatColor.GRAY +"true"+ChatColor.ITALIC + "\n Option to display warning in case chance is zero\n" +
            ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll Config " + ChatColor.LIGHT_PURPLE + "activation-chance " + ChatColor.GRAY +"1"+ChatColor.ITALIC + "\n Chance that the frequency is applied, if randomized\n" +
            ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll Config " + ChatColor.LIGHT_PURPLE + "iterations-to-activate " + ChatColor.GRAY +"1"+ChatColor.ITALIC + "\n Defines # of ClearLogs to iterate before opening lotto\n" +
            ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll Config " + ChatColor.LIGHT_PURPLE + "entry-cost " + ChatColor.GRAY +"100"+ ChatColor.ITALIC +"\n Cost of betting in an open pool\n" +
            ChatColor.LIGHT_PURPLE + "Type " + ChatColor.WHITE + "/cll Config Help 2"+ChatColor.LIGHT_PURPLE+" to read the next page";

    String help_config_dialog_2 = ChatColor.LIGHT_PURPLE + "-=-=-=-=["+ ChatColor.WHITE +"ClearLagLotto"+ChatColor.GREEN+" Page 2"+ChatColor.WHITE+"/" + ChatColor.GREEN+"3"+ChatColor.LIGHT_PURPLE+"]=-=-=-=-\n" +
            ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll Config " + ChatColor.LIGHT_PURPLE + "entry-confirm " +ChatColor.GRAY +"<message>"+ChatColor.ITALIC + "\n Message shown when you have been added to a lotto\n" +
            ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll Config " + ChatColor.LIGHT_PURPLE + "entry-no-money " +ChatColor.GRAY +"<message>"+ChatColor.ITALIC + "\n Message when a user doesn't have sufficient funds\n" +
            ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll Config " + ChatColor.LIGHT_PURPLE + "pot-multiplier " +ChatColor.GRAY +"1"+ChatColor.ITALIC + "\n Multiplier for pot winnings\n" +
            ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll Config " + ChatColor.LIGHT_PURPLE + "pot-adder " + ChatColor.GRAY +"0"+ChatColor.ITALIC + "\n Amount added to the pot per entry + entry-cost\n" +
            ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll Config " + ChatColor.LIGHT_PURPLE + "jackpot-multiplier " + ChatColor.GRAY +"3"+ChatColor.ITALIC + "\n Multiplier for jackpot winnings\n" +
            ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll Config " + ChatColor.LIGHT_PURPLE + "seconds-before-clearlag " + ChatColor.GRAY +"60"+ChatColor.ITALIC + "\n Time prior to a clear lag that the lotto opens\n" +
            ChatColor.LIGHT_PURPLE + "Type " + ChatColor.WHITE + "/cll Config Help 3"+ChatColor.LIGHT_PURPLE+" to read the next page";

    String help_config_dialog_3 = ChatColor.LIGHT_PURPLE + "-=-=-=-=["+ ChatColor.WHITE +"ClearLagLotto"+ChatColor.GREEN+" Page 3"+ChatColor.WHITE+"/" + ChatColor.GREEN+"3"+ChatColor.LIGHT_PURPLE+"]=-=-=-=-\n" +
            ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll Config " + ChatColor.LIGHT_PURPLE + "start-lotto " + ChatColor.GRAY +"<message>"+ChatColor.ITALIC + "\n Message shown at start of Lotto\n" +
            ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll Config " + ChatColor.LIGHT_PURPLE + "no-lotto " + ChatColor.GRAY +"<message>"+ChatColor.ITALIC + "\n Message shown when bets are placed without a lotto open\n" +
            ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll Config " + ChatColor.LIGHT_PURPLE + "lotto-win " + ChatColor.GRAY +"<message>"+ChatColor.ITALIC + "\n Message shown when a user wins\n" +
            ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll Config " + ChatColor.LIGHT_PURPLE + "lotto-no-win " + ChatColor.GRAY +"<message>"+ChatColor.ITALIC + "\n Message shown when no one wins\n" +
            ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll Config "  + ChatColor.LIGHT_PURPLE + "lotto-jackpot " + ChatColor.GRAY +"<message>"+ChatColor.ITALIC + "\n Message shown at jackpot win";
	
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if(!sender.hasPermission("clearlaglotto.cll")) {
            return null;
        }

        if (command.getName().equalsIgnoreCase("clearlaglotto")) {

            if (args.length == 1) {

                List<String> list = new ArrayList<String>();

                list.add("Reload");
                list.add("Config");
                list.add("Help");

                return list;

            }

            if (args.length >= 2) {

                if (args[0].equalsIgnoreCase("config")) {

                    if(args.length == 2){
                        List<String> list = new ArrayList<String>();

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
                        list.add("Help");

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

        if(!(sender instanceof Player) || !sender.hasPermission("clearlaglotto.cll")) {
            sender.sendMessage(ChatColor.RED + "You do not have sufficient permissions.");
            return true;
        }

		
		if(args.length == 0) return false;
		
		if(args[0].equalsIgnoreCase("reload")) {
			plugin.loadConfig();
			sender.sendMessage("Config reloaded");
			return true;
		}


		if(args[0].equalsIgnoreCase("help")){

		    String help_dialog = ChatColor.LIGHT_PURPLE + "-=-=-=-=["+ ChatColor.WHITE +"ClearLagLotto"+ChatColor.LIGHT_PURPLE+"]=-=-=-=-\n" +
                    ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll " + ChatColor.LIGHT_PURPLE + "Reload" + ChatColor.GRAY + " - Reload Config files\n" +
                    ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll " + ChatColor.LIGHT_PURPLE + "Config" + ChatColor.GRAY + " - Change config values in-game\n" +
                    ChatColor.LIGHT_PURPLE + "/" + ChatColor.WHITE + "cll " + ChatColor.LIGHT_PURPLE + "Config Help" + ChatColor.GRAY + " - Shows options for config\n";

		    sender.sendMessage(help_dialog);
		    return true;
        }



		if(args[0].equalsIgnoreCase("config")) {

		    if(args.length == 3) {

                if(args[1].equalsIgnoreCase("help")){


                    if(args[2].equalsIgnoreCase("2")){
                        sender.sendMessage(this.help_config_dialog_2);
                        return true;
                    }

                    if(args[2].equalsIgnoreCase("3")){
                        sender.sendMessage(this.help_config_dialog_3);
                        return true;
                    }

                    sender.sendMessage(this.help_config_dialog_1);
                    return true;

                }



                switch (args[1].toLowerCase()) {
                    case "winning-condition": {

                        if(args[2].equalsIgnoreCase("absolute") || args[2].equalsIgnoreCase("blackjack") || args[2].equalsIgnoreCase("exact")){

                            String input = args[2];

                            plugin.setWinningCondition(input);
                            plugin.editConfig();
                            sender.sendMessage(ChatColor.GREEN + "winning-condition updated with: " + input);
                            return true;
                        }

                        sender.sendMessage(ChatColor.RED + "Please check your input for errors!" + ChatColor.GRAY + " (blackjack or absolute or exact)");
                        return false;

                    }

                    case "randomize-frequency":{

                        if(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")){

                            boolean input = Boolean.parseBoolean(args[2]);

                            plugin.setRandomizingFrequency(input);
                            plugin.editConfig();
                            sender.sendMessage(ChatColor.GREEN + "randomize-frequency updated with: " + input);
                            return true;
                        }

                        sender.sendMessage(ChatColor.RED + "Please check your input for errors!" + ChatColor.GRAY + " (True or False)");
                        return false;

                    }

                    case "zero-activation-warning": {

                        if(args[2].equalsIgnoreCase("true") || args[2].equalsIgnoreCase("false")){

                            boolean input = Boolean.parseBoolean(args[2]);

                            plugin.setZero_activation_warning(input);
                            plugin.editConfig();
                            sender.sendMessage(ChatColor.GREEN + "zero-activation-warning updated with: " + input);
                            return true;

                        }

                        sender.sendMessage(ChatColor.RED + "Please check your input for errors!" + ChatColor.GRAY + " (True or False)");
                        return false;

                    }


                    case "activation-chance": {
                        if(args[2].matches("[0-9]*\\.?[0-9]*")) {
                            if (Double.valueOf(args[2]).doubleValue() >= 0 && Double.valueOf(args[2]).doubleValue() <= 1) {

                                double num = Double.valueOf(args[2]).doubleValue();
                                plugin.setActivationChance(num);
                                plugin.editConfig();
                                sender.sendMessage(ChatColor.GREEN + "activation-chance updated with: " + num);
                                return true;
                            }
                        }

                        sender.sendMessage(ChatColor.RED + "Please check your input for errors!" + ChatColor.GRAY + " (Any decimal between 0 and 1)");
                        return false;

                    }

                    case "iterations-to-activate": {

                        if(args[2].matches("[0-9]+")) {

                            if (Integer.valueOf(args[2]).intValue() >= 0) {

                                int num = Integer.valueOf(args[2]).intValue();
                                plugin.setIterationsToActivate(num);
                                plugin.editConfig();
                                sender.sendMessage(ChatColor.GREEN + "iterations-to-activate has been updated with: " + num);
                                return true;
                            }
                        }

                        sender.sendMessage(ChatColor.RED + "Please check your input for errors!" + ChatColor.GRAY + " (Any integer 0 and greater)");
                        return false;

                    }

                    case "pot-multiplier": {
                        if(args[2].matches("[0-9]*\\.?[0-9]*")) {

                            if (Double.valueOf(args[2]).doubleValue() >= 0 ) {

                                double num = Double.valueOf(args[2]).doubleValue();
                                plugin.setPotMultiplier(num);
                                plugin.editConfig();
                                sender.sendMessage(ChatColor.GREEN + "pot-multiplier updated with: " + num);
                                return true;
                            }
                        }
                        sender.sendMessage(ChatColor.RED + "Please check your input for errors!" + ChatColor.GRAY + " (Any decimal 0 and greater )");
                        return false;

                    }

                    case "pot-adder": {

                        if(args[2].matches("[0-9]*\\.?[0-9]*")) {
                            if (Double.valueOf(args[2]).doubleValue() >= 0) {

                                double num = Double.valueOf(args[2]).doubleValue();
                                plugin.setPotAdder(num);
                                plugin.editConfig();
                                sender.sendMessage(ChatColor.GREEN + "pot-adder updated with: " + num);
                                return true;
                            }


                        }

                        sender.sendMessage(ChatColor.RED + "Please check your input for errors!" + ChatColor.GRAY + " (Any decimal 0 or greater)");
                        return false;


                    }
                    case "jackpot-multiplier": {

                        if(args[2].matches("[0-9]*\\.?[0-9]*")) {
                            if (Double.valueOf(args[2]).doubleValue() >= 0) {

                                double num = Double.valueOf(args[2]).doubleValue();
                                plugin.setJackpotMultiplier(num);
                                plugin.editConfig();
                                sender.sendMessage(ChatColor.GREEN + "jackpot-multiplier updated with: " + num);
                                return true;
                            }
                        }

                        sender.sendMessage(ChatColor.RED + "Please check your input for errors!" + ChatColor.GRAY + " (Any decimal 0 and greater)");
                        return false;

                    }

                    case "seconds-before-clearlag": {

                        if(args[2].matches("[0-9]+")) {

                            if (Integer.valueOf(args[2]).intValue() >= 0) {

                                int num = Integer.valueOf(args[2]).intValue();
                                plugin.setSecondsBeforeClearLag(num);
                                plugin.editConfig();
                                sender.sendMessage(ChatColor.GREEN + "seconds-before-clearlag has been updated with: " + num);
                                return true;
                            }
                        }
                    sender.sendMessage(ChatColor.RED + "Please check your input for errors!" + ChatColor.GRAY + " (Any integer 0 and greater)");
                    return false;

                    }
                    case "entry-cost":{
                        if(args[2].matches("[0-9]*\\.?[0-9]*")) {
                            if (Double.valueOf(args[2]).doubleValue() >= 0) {

                                double num = Double.valueOf(args[2]).doubleValue();
                                plugin.setEntryCost(num);
                                plugin.editConfig();
                                sender.sendMessage(ChatColor.GREEN + "entry-cost updated with: " + num);
                                return true;
                            }
                        }
                        sender.sendMessage(ChatColor.RED + "Please check your input for errors!" + ChatColor.GRAY + " (Any decimal 0 and greater)");
                        return false;

                    }

                    default: {
                        sender.sendMessage(ChatColor.RED + "Please enter a valid config option or config input.");
                        return false;
                    }
                }

            }


		    if(args.length == 2){

                if(args[1].equalsIgnoreCase("help")){


                    sender.sendMessage(this.help_config_dialog_1);
                    return true;

                }
            }


            if(args.length >= 3) {


                int count = args.length;

                switch (args[1].toLowerCase()){

                    case "entry-confirm":{
                        String message = "";

                        for(int i = 2; i<count; i++){
                            message += args[i] + " ";
                        }

                        plugin.setEntryConfirmMessage(message);
                        plugin.editConfig();
                        sender.sendMessage(ChatColor.GREEN + "entry-confirm updated with: " + message);
                        return true;


                    }

                    case "entry-no-money":{

                        String message = "";

                        for(int i = 2; i<count; i++){
                            message += args[i] + " ";
                        }

                        plugin.setEntryNoMoneyMessage(message);
                        plugin.editConfig();
                        sender.sendMessage(ChatColor.GREEN + "entry-no-money updated with: " + message);
                        return true;

                    }

                    case "start-lotto":{
                        String message = "";

                        for(int i = 2; i<count; i++){
                            message += args[i] + " ";
                        }

                        plugin.setStartLottoMessage(message);
                        plugin.editConfig();
                        sender.sendMessage(ChatColor.GREEN + "start-lotto updated with: " + message);
                        return true;

                    }

                    case "no-lotto":{
                        String message = "";

                        for(int i = 2; i<count; i++){
                            message += args[i] + " ";
                        }

                        plugin.setNoLottoMessage(message);
                        plugin.editConfig();
                        sender.sendMessage(ChatColor.GREEN + "no-lotto updated with: " + message);
                        return true;

                    }

                    case "lotto-win":{
                        String message = "";

                        for(int i = 2; i<count; i++){
                            message += args[i] + " ";
                        }

                        plugin.setLottoWinMessage(message);
                        plugin.editConfig();
                        sender.sendMessage(ChatColor.GREEN + "lotto-win updated with: " + message);
                        return true;

                    }

                    case "lotto-no-win":{
                        String message = "";

                        for(int i = 2; i<count; i++){
                            message += args[i] + " ";
                        }

                        plugin.setLottoNoWinMessage(message);
                        plugin.editConfig();
                        sender.sendMessage(ChatColor.GREEN + "lotto-no-win updated with: " + message);
                        return true;

                    }

                    case "lotto-jackpot": {
                        String message = "";

                        for(int i = 2; i<count; i++){
                            message += args[i] + " ";
                        }

                        plugin.setLottoJackpotMessage(message);
                        plugin.editConfig();
                        sender.sendMessage(ChatColor.GREEN + "lotto-jackpot updated with: " + message);
                        return true;

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