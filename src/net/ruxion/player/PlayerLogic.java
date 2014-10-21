package net.ruxion.player;

import java.io.File;
import java.util.UUID;

import net.md_5.bungee.api.ChatColor;
import net.ruxion.lobby.LobbyEvents;
import net.ruxion.game.GameLogic;
import net.ruxion.game.SpectatorEvents;
import net.ruxion.gui.InvGui;
import net.ruxion.main.UHC;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class PlayerLogic implements Listener
{
	UHC uhc;
	public PlayerLogic(UHC uhc)
	{
		this.uhc = uhc;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e)
	{
		Player p = e.getPlayer();
		GamePlayer p2 = new GamePlayer(p.getUniqueId(), PlayerTeam.Other);
		
		if(GameLogic.inGame == true)p2.setSpectator(true);
		if(GameLogic.inGame == true)SpectatorEvents.addSpectator(p.getUniqueId());
		
		PlayerMaps.maps.put(p.getUniqueId(), p2);
		
		new InvGui(p).setupInventory();
		
		p.teleport(new Location(Bukkit.getWorld("lobby"), 243.5, 21.5, 0.5, -1f, 0.5f));
		p.setGameMode(GameMode.ADVENTURE);
		
		LobbyEvents.l.addPlayer(p);
		p.setFoodLevel(20);
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e)
	{
		LobbyEvents.l.remPlayer(e.getPlayer());
		SpectatorEvents.remSpectator(e.getPlayer().getUniqueId());
		GamePlayer p = PlayerMaps.maps.get(e.getPlayer().getUniqueId());
		if(GameLogic.game.getTeamByPlayerTeam(p.getTeam())!=null)GameLogic.game.getTeamByPlayerTeam(p.getTeam()).remPlayer(e.getPlayer().getUniqueId());
		final UUID uu = e.getPlayer().getUniqueId();
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(uhc, new Runnable()
		{
			public void run()
			{
				new File(Bukkit.getWorldContainer(), "lobby/playerdata/"+uu.toString()+".dat/").delete();
			}
		}, 20L);
	}
	
	@EventHandler
	public void onPing(ServerListPingEvent e)
	{
		e.setMotd(ChatColor.AQUA+"Luna's waffle warehouse");
		e.setMaxPlayers(999999999);
	}
}