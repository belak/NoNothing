/*
 * NoNothing - a bukkit plugin
 * Copyright 2012 Schwarzer Zylinder
 * Copyright 2012 Kaleb Elwert <kelwert@mtu.edu>
 *
 * This file is part of NoNothing.
 *
 * NoNothing is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NoNothing is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NoNothing.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.codebelak.nonothing;

import java.util.Locale;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NoNothing extends JavaPlugin {

	private final NoNothingListener listener = new NoNothingListener(this);
	private FileConfiguration config;
	
	Logger log = Logger.getLogger("Minecraft");

	// Plugin name in square brackets, can be set as
	// identifier in front of a message: [NoNothing]
	public String name;

	public void onDisable() {
		log.info(name + "is disabled.");
	}

	public void onEnable() {
		PluginDescriptionFile pdf = this.getDescription();
		PluginManager pm = this.getServer().getPluginManager();
		
		config = getConfig();
		
		config.options().copyDefaults(true);
		saveConfig();
		
		pm.registerEvents(listener, this);

		name = "[" + pdf.getName() + "] ";

		log.info(name + "version " + pdf.getVersion() + " by Kaleb Elwert is enabled.");
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(label.equalsIgnoreCase("nonothing") || label.equalsIgnoreCase("no")) || args.length < 1 || !sender.hasPermission("nonothing.admin"))
			return false;

		if (args[0].equalsIgnoreCase("reload")) {
			reloadConfig();
			config = getConfig();
			sender.sendMessage(name + "Config reloaded.");
			return true;
		} else if (args[0].equalsIgnoreCase("save")) {
			saveConfig();
			sender.sendMessage(name + "Config saved.");
			return true;
		} else if (args.length > 2 && args[0].equalsIgnoreCase("set")) {				
			if (args[1].equalsIgnoreCase("global")) {
				config.set("global", new Boolean(args[2]));
			} else if (args[1].equalsIgnoreCase("enabled")) {
				config.set("enabled", new Boolean(args[2]));
			} else if (args[1].equalsIgnoreCase("disable-hunger")) {
				config.set("disable.hunger", new Boolean(args[2]));
			} else if (args[1].equalsIgnoreCase("disable-damage")) {
				config.set("disable.damage", new Boolean(args[2]));
			} else if (args[1].equalsIgnoreCase("disable-exhaustion")) {
				config.set("disable.exhaustion", new Boolean(args[2]));
			}
		} else if (args.length > 1 && args[0].equalsIgnoreCase("get")) {
			if (args[1].equalsIgnoreCase("global")) {
				sender.sendMessage(name + "Global: " + config.getBoolean("global"));
			} else if (args[1].equalsIgnoreCase("enabled")) {
				sender.sendMessage(name + "Enabled: " + config.getBoolean("enabled"));
			} else if (args[1].equalsIgnoreCase("hunger")) {
				sender.sendMessage(name + "disable.hunger: " + config.getBoolean("disable.hunger"));
			} else if (args[1].equalsIgnoreCase("damage")) {
				sender.sendMessage(name + "disable.damage: " + config.getBoolean("disable.damage"));
			} else if (args[1].equalsIgnoreCase("exhaustion")) {
				sender.sendMessage(name + "disable.exhaustion: " + config.getBoolean("disable.exhaustion"));
			}
		}

		return false;
	}
	
	protected boolean checkPermissions(CommandSender sender, String permission) {
		if (config.getBoolean("enabled")) {
			// TODO: Make sure this is really needed - do we want to give Entities that aren't players all these permissions
			if (!(sender instanceof Player))
				return true;
			Player player = (Player) sender;
	
			if (config.getBoolean("global")) {
				if (config.getBoolean(permission.toLowerCase(Locale.ENGLISH), false))
					return true;
			} else if (player.hasPermission("nonothing." + permission.toLowerCase(Locale.ENGLISH))) {
				return true;
			}
		}
		
		return false;
	}
}