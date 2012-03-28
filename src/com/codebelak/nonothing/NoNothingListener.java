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

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class NoNothingListener implements Listener {

	NoNothing plugin;

	public NoNothingListener(NoNothing instance) {
		plugin = instance;
	}

	// Entity stuff
	@EventHandler
	public void onEntityDamage(EntityDamageEvent event) {
		// If it's a player
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			// If the player has the safe permission
			if (plugin.checkPermissions((CommandSender) player, "disable.damage")) {
				// Remove fall damage
				event.setCancelled(true);
			}
		}
	}

	// Make sure all Food Events are taken care of
	@EventHandler
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		// If it's a player
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			// If the player has the permission
			if (plugin.checkPermissions((CommandSender) player, "disable.hunger")) {
				// Set exhaustion to 0
				player.setExhaustion(0);
				
				// TODO: Possibly change this later so the food doesn't shake - or make an option
				// With maximum food level (20) the player would be unable to
				// eat something, so set it just 19
				event.setFoodLevel(19);
				
				event.setCancelled(true);
			}
		}
	}
	
	// Make it so mobs can't target the player
	// NOTE: This is probably broken - It currently only affects anything that implements entity.Monster
	@EventHandler
	public void onTargetLivingEntity(EntityTargetLivingEntityEvent event) {
		if (event.getTarget() instanceof Player && event.getEntity() instanceof Monster) {
			Player player = (Player) event.getEntity();
			
			// Stop the target
			if (plugin.checkPermissions((CommandSender) player, "disable.target")) {
				event.setCancelled(true);
			}
		}
	}
	
	// Player Stuff
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (player.isSprinting() && plugin.checkPermissions((CommandSender) player, "disable.exhaustion")) {
			player.setExhaustion(0);
		}
	}
}
