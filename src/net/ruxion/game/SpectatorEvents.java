package net.ruxion.game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.ruxion.player.PlayerMaps;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class SpectatorEvents implements Listener
{
	
	private static List<UUID> spectators = new ArrayList<UUID>();
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e)
	{
		if(e.getDamager().getType().equals(EntityType.PLAYER) && e.getEntity().getType().equals(EntityType.PLAYER))
		{
			if(spectators.contains(((Player)e.getDamager()).getUniqueId()))
			{
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		reloadVanish();
	}
	
	public static void addSpectator(UUID uu)
	{
		spectators.add(uu);
		hidePlayer(uu);
	}
	
	@SuppressWarnings("deprecation")
	private static void hidePlayer(UUID uu)
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(!PlayerMaps.maps.get(p.getUniqueId()).isSpectator())
			{
				p.hidePlayer(Bukkit.getPlayer(uu));
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	private static void reloadVanish()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			if(!PlayerMaps.maps.get(p.getUniqueId()).isSpectator())
			{
				for(UUID uu : spectators)
				{
					p.hidePlayer(Bukkit.getPlayer(uu));
				}
			}
		}
	}
	
	public static void clearSpecators()
	{
		spectators.clear();
	}

	public static void remSpectator(UUID uu)
	{
		if(spectators.contains(uu))
		{
			spectators.remove(uu);
			reloadVanish();
		}
	}
}