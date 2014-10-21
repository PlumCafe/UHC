package net.ruxion.lobby.runtimes;

import net.ruxion.lobby.Lobby;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyTimer extends BukkitRunnable
{
	Lobby l;
	
	int i;
	
	public LobbyTimer(Lobby l)
	{
		this.l = l;
		i = 0;
	}
	
	@Override
	public void run()
	{
		if(i == 60)
		{
			Bukkit.broadcastMessage(ChatColor.GREEN+"Game Starting!");
		}
		i++;
	}

}
