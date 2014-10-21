package net.ruxion.game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.ruxion.game.runtimes.GameRuntime;
import net.ruxion.game.teams.GameTeam;
import net.ruxion.lobby.LobbyEvents;
import net.ruxion.main.UHC;
import net.ruxion.player.GamePlayer;
import net.ruxion.player.PlayerMaps;
import net.ruxion.player.PlayerTeam;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.player.PlayerMoveEvent;

public class GameLogic implements Listener
{
	public static ObjGame game;
	public static boolean inGame;
	public static GameStage stage;

	public static void startGame()
	{
		inGame = true;
		game.setLobby(LobbyEvents.l);
		
		CheckTeams();
		
		teleportTeams();
		
		new GameRuntime().runTaskTimer(UHC.uhc, 0, 20);
		
		Bukkit.getWorld("game").setTime(0L);
		
	}
	
	public static List<GameTeam> getOnePlayerNeededTeams()
	{
		List<GameTeam> oneplayerneededteams = new ArrayList<GameTeam>();
		for(GameTeam team : game.getTeams())
		{
			if(team.getPlayers().size() == 1) oneplayerneededteams.add(team);
		}
		return oneplayerneededteams;
	}
	
	public static List<GameTeam> getEmptyTeams()
	{
		List<GameTeam> emptyteams = new ArrayList<GameTeam>();
		for(GameTeam team : game.getTeams())
		{
			if(team.getPlayers().size() == 0) emptyteams.add(team);
		}
		return emptyteams;
	}
	
	@SuppressWarnings("deprecation")
	public static void CheckTeams()
	{
		for(Player p : Bukkit.getOnlinePlayers())
		{
			GamePlayer p2 = PlayerMaps.maps.get(p.getUniqueId());
			if(p2.getTeam().equals(PlayerTeam.Other))
			{
				if(getOnePlayerNeededTeams().size()>0)
				{
					GameTeam team = null;
					for(GameTeam tm : getOnePlayerNeededTeams())
					{
						tm.addPlayer(p.getUniqueId());
						p2.setTeam(tm.getPlayerTeam());
						team = tm;
						break;
					}
					if(team == null)
					{
						p.kickPlayer("Game Started not enough teams for you to play.");
					}
					team.reloadEntry();
					PlayerMaps.maps.put(p.getUniqueId(), p2);
				}else{
					GameTeam team = null;
					for(GameTeam tm : getEmptyTeams())
					{
						tm.addPlayer(p.getUniqueId());
						p2.setTeam(tm.getPlayerTeam());
						team = tm;
						break;
					}
					if(team == null)
					{
						p.kickPlayer("Game Started not enough teams for you to play.");
					}
					team.reloadEntry();
					PlayerMaps.maps.put(p.getUniqueId(), p2);
				}
			}
		}
	}
	
	public static void teleportTeams()
	{
		for(GameTeam team : game.getTeams())
		{
			if(team.getPlayers().size() > 0)
			{
				for(UUID uu : team.getPlayers())
				{
					if(Bukkit.getPlayer(uu) != null)
					{
						Bukkit.getPlayer(uu).teleport(team.getStartPoint());
					}
				}
			}
		}
	}
	
	public static void wipeGameStage()
	{
		stage = GameStage.notingame;
	}
	
	enum GameStage
	{
		notingame(), start(), oneT(), fiveT();
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e)
	{
		Player p = e.getPlayer();
		int x = p.getLocation().getBlockX();
		int z = p.getLocation().getBlockX();
		
		//if()
		//{
			
		//}
	}
	
	@EventHandler
	public void onHealthRegain(EntityRegainHealthEvent e)
	{
		if(!e.getRegainReason().equals(RegainReason.MAGIC_REGEN) && !e.getRegainReason().equals(RegainReason.REGEN))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e)
	{
		if(e.getDamager().getType().equals(EntityType.PLAYER) && e.getEntity().getType().equals(EntityType.PLAYER))
		{
			if(PlayerMaps.maps.get(((Player)e.getDamager()).getUniqueId()).getTeam()
					==
					PlayerMaps.maps.get(((Player)e.getEntity()).getUniqueId()).getTeam())
			{
				e.setCancelled(true);
			}
		}
	}

}