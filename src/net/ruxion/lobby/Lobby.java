package net.ruxion.lobby;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.ruxion.lobby.runtimes.LobbyTimer;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Lobby
{
	private List<UUID> uus = new ArrayList<UUID>();
	private World world;
	private Plugin plugin;
	
	public Lobby(List<UUID> uus, World world, Plugin plugin)
	{
		this.world = world;
		if(uus != null)
		{
			this.uus = uus;
		}
		this.plugin = plugin;
	}

	public List<UUID> getPlayers()
	{
		return uus;
	}
	
	public void startTimer()
	{
		LobbyTimer t = new LobbyTimer(this);
		t.runTaskTimer(plugin, 0, 20);
	}
	
	public void addPlayer(Player p)
	{
		uus.add(p.getUniqueId());
	}
	
	public void remPlayer(Player p)
	{
		uus.remove(p.getUniqueId());
	}
	
	public World getWorld()
	{
		return world;
	}
	
}