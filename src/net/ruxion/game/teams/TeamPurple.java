package net.ruxion.game.teams;

import java.util.List;
import java.util.UUID;

import net.ruxion.game.GameLogic;
import net.ruxion.player.PlayerTeam;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class TeamPurple extends GameTeam
{
	public TeamPurple(List<UUID> players)
	{
		super(players, null, PlayerTeam.Purple);
	}
	
	@Override
	public Location getStartPoint()
	{
		World w = Bukkit.getWorld("game");
		
		Location l = w.getHighestBlockAt(-800, 800).getLocation();
		
		setSafeLocation(l);
		
		return l;
	}
	
	@Override
	public void reloadEntry()
	{
		GameLogic.game.setPurple(this);
	}
	
	@Override
	public String getTeamPrefix()
	{
		return "&3[&5Purple&3]&5";
	}
	
}