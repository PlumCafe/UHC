package net.ruxion.player;

import java.util.UUID;

import net.ruxion.game.GameLogic;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GamePlayer
{
	private UUID uu;
	private PlayerTeam team;
	private boolean isSpec = false;
	
	public GamePlayer(UUID uu, PlayerTeam team)
	{
		this.uu = uu;
		this.team = team;
	}
	
	public Player getPlayer()
	{
		return Bukkit.getPlayer(uu);
	}
	
	public boolean inGame()
	{
		return GameLogic.inGame;
	}
	
	public boolean inLobby()
	{
		return !GameLogic.inGame;
	}
	
	public boolean isSpectator()
	{
		return isSpec;
	}
	
	public void setSpectator(boolean bool)
	{
		isSpec = bool;
	}
	
	public PlayerTeam getTeam()
	{
		return team;
	}
	
	public void setTeam(PlayerTeam team)
	{
		this.team = team;
	}
	
	public void saveMap()
	{
		PlayerMaps.maps.put(uu, this);
	}
}