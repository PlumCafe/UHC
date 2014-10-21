package net.ruxion.game.teams;

import java.util.List;
import java.util.UUID;

import net.ruxion.game.GameLogic;
import net.ruxion.player.PlayerTeam;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class TeamGreen extends GameTeam
{
	public TeamGreen(List<UUID> players)
	{
		super(players, null, PlayerTeam.Green);
	}
	
	@Override
	public Location getStartPoint()
	{
		World w = Bukkit.getWorld("game");
		
		Location l = w.getHighestBlockAt(400, 800).getLocation();
		
		setSafeLocation(l);
		
		return l;
	}
	
	@Override
	public void reloadEntry()
	{
		GameLogic.game.setGreen(this);
	}
	
	@Override
	public String getTeamPrefix()
	{
		return "&3[&aGreen&3]&a";
	}
	
}