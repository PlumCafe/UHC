package net.ruxion.game.teams;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.ruxion.player.PlayerTeam;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class GameTeam
{
	private List<UUID> teamplayers = new ArrayList<UUID>();
	private Location location;
	private PlayerTeam team;
	
	public GameTeam(List<UUID> teamplayers, Location location, PlayerTeam team)
	{
		this.teamplayers = teamplayers;
		this.location = location;
		this.team = team;
	}
	
	public PlayerTeam getPlayerTeam()
	{
		return team;
	}
	
	public Location getStartPoint()
	{
		return location;
	}
	
	public List<UUID> getPlayers()
	{
		return teamplayers;
	}
	
	public void remPlayer(UUID uu)
	{
		teamplayers.remove(uu);
	}
	
	public void addPlayer(UUID uu)
	{
		teamplayers.add(uu);
	}
	
	public void reloadEntry()
	{
		
	}
	
	public String getTeamPrefix()
	{
		return "";
	}
	
	public String[] getPlayersLore()
	{
		List<String> convertList = new ArrayList<String>();
		for(UUID s : teamplayers)
		{
			convertList.add(Bukkit.getPlayer(s).getName());
		}
		return convertList.toArray(new String[convertList.size()]);
	}
	
	public boolean isFire(int x, int y, int z)
	{
		Block below = Bukkit.getWorld("game").getBlockAt(x, y - 1, z);
		if (below.getType() == Material.FIRE)
		{
			return true;
		}
		return false;
	}
	
	public boolean isLava(int x, int y, int z)
	{
		Block below = Bukkit.getWorld("game").getBlockAt(x, y - 1, z);
		if (below.getType() == Material.LAVA || below.getType() == Material.STATIONARY_LAVA)
		{
			return true;
		}
		return false;
	}
	
	public void setSafeLocation(Location l)
	{
		int centerX = l.getBlockX();
		int centerY = l.getBlockY();
		int centerZ = l.getBlockZ();
		int radius = 5;
		List<Block> blocks = new ArrayList<Block>();
        for (int x = centerX - radius; x < centerX + radius; x++)
        {
            for (int y = centerY - radius; y < centerY + radius; y++)
            {
                for (int z = centerZ - radius; z < centerZ + radius; z++)
                {
                    blocks.add(l.getWorld().getBlockAt(x, y, z));
                }
            }
        }
		for(Block b : blocks)
		{
			if(b.getType().equals(Material.LAVA)||b.getType().equals(Material.STATIONARY_LAVA))
			{
				b.setType(Material.STONE);
			}
		}
	}
	
}