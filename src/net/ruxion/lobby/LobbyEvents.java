package net.ruxion.lobby;

import net.ruxion.game.GameLogic;
import net.ruxion.lobby.runtimes.LobbyTimer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class LobbyEvents implements Listener
{
	public static Lobby l;
	
	public static LobbyTimer timer;
	
	@EventHandler
	public void onMobSpawn(CreatureSpawnEvent e)
	{
		if(e.getLocation().getWorld().getName().equalsIgnoreCase("lobby"))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e)
	{
		if(e.getBlock().getLocation().getWorld().getName().equalsIgnoreCase("lobby"))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e)
	{
		if(e.getBlockPlaced().getLocation().getWorld().getName().equalsIgnoreCase("lobby"))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent e)
	{
		if(e.getWorld().getName().equalsIgnoreCase("lobby"))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e)
	{
		if(GameLogic.inGame == false)
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		
	}
	
}