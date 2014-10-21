package net.ruxion.game.teams;

import java.util.List;
import java.util.UUID;

import net.ruxion.game.GameLogic;
import net.ruxion.player.PlayerTeam;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class TeamPink extends GameTeam
{
	public TeamPink(List<UUID> players)
	{
		super(players, null, PlayerTeam.Pink);
	}
	
	@Override
	public Location getStartPoint()
	{
		World w = Bukkit.getWorld("game");
		
		Location l = w.getHighestBlockAt(0, 800).getLocation();
		
		setSafeLocation(l);
		
		return l;
	}
	
	@Override
	public void reloadEntry()
	{
		GameLogic.game.setPink(this);
	}
	
	@Override
	public String getTeamPrefix()
	{
		return "&3[&dPink&3]&d";
	}
	
}