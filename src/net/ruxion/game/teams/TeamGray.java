package net.ruxion.game.teams;

import java.util.List;
import java.util.UUID;

import net.ruxion.game.GameLogic;
import net.ruxion.player.PlayerTeam;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class TeamGray extends GameTeam
{
	public TeamGray(List<UUID> players)
	{
		super(players, null, PlayerTeam.Gray);
	}
	
	@Override
	public Location getStartPoint()
	{
		World w = Bukkit.getWorld("game");
		
		Location l = w.getHighestBlockAt(100, 200).getLocation();
		
		setSafeLocation(l);
		
		return l;
	}
	
	@Override
	public void reloadEntry()
	{
		GameLogic.game.setGray(this);
	}
	
	@Override
	public String getTeamPrefix()
	{
		return "&3[&7Gray&3]&7";
	}
	
}