package io.github.ratismal.fakechieve;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import mkremins.fanciful.FancyMessage;

public class FakeChieve extends JavaPlugin {




	public static FakeChieve plugin;
	public Logger log = getLogger();


	@Override
	public void onEnable() {

		//saveCustomConfig();
		//log.info("Running on version " + getDescription().getVersion());
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[FakeChieve] Plugin by Ratismal");
		//Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[MoneyThief] Check for updates at: ");
		//Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[MoneyThief]" + ChatColor.AQUA + " http://dev.bukkit.org/bukkit-plugins/moneythief/");
		plugin = this;

		this.getServer().getPluginManager();




	}


	@Override
	public void onDisable() {
		log.info("onDisable has been invoked!");
		getServer().getServicesManager().unregisterAll(this);
		Bukkit.getScheduler().cancelTasks(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


		if ((cmd.getName().equalsIgnoreCase("fakechieve")) && (args.length >= 3)) {
			if (sender.hasPermission("fakechieve")) {
				Player player = gettPlayer(args[0]);
				String message = args[1];
				message = message.replaceAll("_", " ");
				message = message.trim();
				/*
				int i;
				for (i = 1; i<=args.length; i++) {
					if ((args[i].startsWith("\"")) && 
							(args[i].endsWith("\""))) {
						message = args[i].replaceAll("\"", " ");
						message = args[i].trim();
						break;
					}
					else if (args[i].startsWith("\"")) {
						message = args[i].replaceAll("\"", " ");
						message = args[i].trim();
					}
					else if (!args[i].startsWith("\"") || !args[i].endsWith("\"")) {
						message = message + " " + args[i];
					}
					else if (args[i].endsWith("\"")) {
						message = message + " " + args[i].replaceAll("\"", " ");
						message = message.trim();
						break;
					}
				}
				log.info("" + i);;
				*/
				String message2 = args[2];
				/*
				for (int p = i; i<=args.length; i++) {
					if ((args[p].startsWith("\"")) && 
							(args[p].endsWith("\""))) {
						message2 = args[p].replaceAll("\"", " ");
						message2 = args[p].trim();
						break;
					}
					else if (args[p].startsWith("\"")) {
						message2 = args[p].replaceAll("\"", " ");
						message2 = args[p].trim();
					}
					else if (!args[p].startsWith("\"") || !args[p].endsWith("\"")) {
						message2 = message2 + " " + args[p];
					}
					else if (args[i].endsWith("\"")) {
						message2 = message2 + " " + args[p].replaceAll("\"", " ");
						message2 = message2.trim();
						break;
					}
				}
				*/
				message2 = message2.replaceAll("_", " ");
				message2 = message2.trim();
				//List<String> al = Arrays.asList(ChatColor.GREEN + message, "" + ChatColor.WHITE + ChatColor.ITALIC + "Achievement");
				//String[] messages = new String[10];
				//messages[0] = ChatColor.GREEN + message;
				//messages[1] = "" + ChatColor.WHITE + ChatColor.ITALIC + "Achievement";
				ArrayList<String> al = new ArrayList<String>();
				al.add(ChatColor.GREEN + message);
				al.add("" + ChatColor.WHITE + ChatColor.ITALIC + "Achievement");

				int breaks;
				//String tempMessage;
				//al.add("" + ChatColor.GREEN + message);
				//al.add("" + ChatColor.WHITE + ChatColor.ITALIC + "Achievement");
				if (message2.contains("<n>")) {
					while (message2.contains("<n>")) {
						breaks = message2.indexOf("<n>");
						String tempMessage = message2.substring(0, breaks);
						//tempMessage = tempMessage.replaceAll("\n", " ");
						//sender.sendMessage(tempMessage);
						tempMessage = tempMessage.trim();
						//log.info(tempMessage);
						al.add("" + ChatColor.RESET + ChatColor.WHITE + tempMessage);
						
						message2 = message2.substring(breaks, message2.length());
						message2 = message2.replaceFirst("<n>", " ");
						message2 = message2.trim();
					}
				}
				if (!message2.contains("<n>")){
					//log.info(message2);
					al.add("" + ChatColor.RESET + ChatColor.WHITE + message2);
				}
				//List<String> al = Arrays.asList(messages);
				Iterable<String> it = al;
				
				message2 = message2.trim();
				if (player != null) {
					FancyMessage fancy = new FancyMessage(player.getName() + " has just earned the achievement ")
						.then("[")
							.color(ChatColor.GREEN)
						.then(message)
							.color(ChatColor.GREEN)
							//.tooltip(ChatColor.GREEN + message, "" + ChatColor.WHITE + ChatColor.ITALIC + "Achievement")
							.tooltip(it)
						.then("]")
							.color(ChatColor.GREEN);
					sendAll(fancy);
					//fancy.send(sender);
					//Bukkit.broadcastMessage(fancy);
					//Bukkit.broadcastMessage(ChatColor.WHITE + player.getName() + 
					//		" has just earned the achievement " + ChatColor.GREEN + "[" + message + "]");
					//Bukkit.broadcastMessage(chieve);
				}
				else {
					sender.sendMessage(ChatColor.RED + "No such player, you silly!");
				}

			}
			return true;
		}


		return false; 
	}



	public void noPerms(CommandSender sender) {
		String noPerms = ChatColor.RED + "I'm sorry Dave, I'm afraid I can't do that.";
		//noPerms = ChatColor.translateAlternateColorCodes('&', noPerms);
		sender.sendMessage(noPerms);
		return;
	}

	public Player gettPlayer(String name) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.getName().equalsIgnoreCase(name))
				return p;
		}
		return null;
	}

	public void sendAll(FancyMessage fancy) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			fancy.send(p);
		}
		return;
	}
}