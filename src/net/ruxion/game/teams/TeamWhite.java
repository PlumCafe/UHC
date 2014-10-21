package net.ruxion.game.teams;

import java.util.List;
import java.util.UUID;

import net.ruxion.game.GameLogic;
import net.ruxion.player.PlayerTeam;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class TeamWhite extends GameTeam
{
	public TeamWhite(List<UUID> players)
	{
		super(players, null, PlayerTeam.White);
	}
	
	@Override
	public Location getStartPoint()
	{
		World w = Bukkit.getWorld("game");
		
		Location l = w.getHighestBlockAt(-700, 900).getLocation();
		
		setSafeLocation(l);
		
		return l;
	}
	
	@Override
	public void reloadEntry()
	{
		GameLogic.game.setWhite(this);
	}
	
	@Override
	public String getTeamPrefix()
	{
		return "&3[&fWhite&3]&f";
	}
	
}
