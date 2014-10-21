package net.ruxion.game.teams;

import java.util.List;
import java.util.UUID;

import net.ruxion.game.GameLogic;
import net.ruxion.player.PlayerTeam;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class TeamOrange extends GameTeam
{

	public TeamOrange(List<UUID> players)
	{
		super(players, null, PlayerTeam.Orange);
	}
	
	@Override
	public Location getStartPoint()
	{
		World w = Bukkit.getWorld("game");
		
		Location l = w.getHighestBlockAt(-800, 400).getLocation();
		
		setSafeLocation(l);
		
		return l;
	}
	
	@Override
	public void reloadEntry()
	{
		GameLogic.game.setOrange(this);
	}
	
	@Override
	public String getTeamPrefix()
	{
		return "&3[&6Orange&3]&6";
	}
	
}