package net.ruxion.game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.ruxion.lobby.Lobby;
import net.ruxion.player.PlayerTeam;
import net.ruxion.game.teams.*;

import org.bukkit.World;

public class ObjGame
{
	private Lobby lobby;
	private World world;
	private List<UUID> spectators;
	
	private TeamWhite white;
	private TeamOrange orange;
	private TeamPurple purple;
	private TeamYellow yellow;
	private TeamGreen green;
	private TeamPink pink;
	private TeamRed red;
	private TeamGray gray;
	private TeamBlue blue;

	public ObjGame(Lobby lobby, World world)
	{
		this.lobby = lobby;
		this.world = world;
		
		this.white = new TeamWhite(new ArrayList<UUID>());
		this.orange = new TeamOrange(new ArrayList<UUID>());
		this.purple = new TeamPurple(new ArrayList<UUID>());
		this.yellow = new TeamYellow(new ArrayList<UUID>());
		this.green = new TeamGreen(new ArrayList<UUID>());
		this.pink = new TeamPink(new ArrayList<UUID>());
		this.red = new TeamRed(new ArrayList<UUID>());
		this.gray = new TeamGray(new ArrayList<UUID>());
		this.blue = new TeamBlue(new ArrayList<UUID>());
	}
	
	public List<UUID> getPlayers()
	{
		return lobby.getPlayers();
	}
	
	public List<UUID> getSpectators()
	{
		return spectators;
	}
	
	public void setLobby(Lobby l)
	{
		lobby = l;
	}
	
	public void setSpectators(List<UUID> specs)
	{
		spectators = specs;
	}
	
	public World getWorld()
	{
		return world;
	}
	
	public GameTeam getTeamByPlayerTeam(PlayerTeam team)
	{
		if(team.equals(PlayerTeam.Blue)){
			return getBlue();
		}else if(team.equals(PlayerTeam.Gray)){
			return getGray();
		}else if(team.equals(PlayerTeam.Green)){
			return getGreen();
		}else if(team.equals(PlayerTeam.Orange)){
			return getOrange();
		}else if(team.equals(PlayerTeam.Pink)){
			return getPink();
		}else if(team.equals(PlayerTeam.Purple)){
			return getPurple();
		}else if(team.equals(PlayerTeam.Red)){
			return getRed();
		}else if(team.equals(PlayerTeam.White)){
			return getWhite();
		}else if(team.equals(PlayerTeam.Yellow)){
			return getYellow();
		}else{
			return null;
		}
	}
	
	public List<GameTeam> getTeams()
	{
		List<GameTeam> teams = new ArrayList<GameTeam>();
		teams.add(white);
		teams.add(orange);
		teams.add(purple);
		teams.add(yellow);
		teams.add(green);
		teams.add(pink);
		teams.add(red);
		teams.add(gray);
		teams.add(blue);
		return teams;
	}
	
	public TeamWhite getWhite()
	{
		return white;
	}
	
	public TeamOrange getOrange()
	{
		return orange;
	}
	
	public TeamPurple getPurple()
	{
		return purple;
	}
	
	public TeamYellow getYellow()
	{
		return yellow;
	}
	
	public TeamGreen getGreen()
	{
		return green;
	}
	
	public TeamPink getPink()
	{
		return pink;
	}
	
	public TeamRed getRed()
	{
		return red;
	}
	
	public TeamGray getGray()
	{
		return gray;
	}
	
	public TeamBlue getBlue()
	{
		return blue;
	}
	
	public void setWhite(TeamWhite white)
	{
		this.white = white;
	}
	
	public void setOrange(TeamOrange orange)
	{
		this.orange = orange;
	}
	
	public void setPurple(TeamPurple purple)
	{
		this.purple = purple;
	}
	
	public void setYellow(TeamYellow yellow)
	{
		this.yellow = yellow;
	}
	
	public void setGreen(TeamGreen green)
	{
		this.green = green;
	}
	
	public void setPink(TeamPink pink)
	{
		this.pink = pink;
	}
	
	public void setRed(TeamRed red)
	{
		this.red = red;
	}
	
	public void setGray(TeamGray gray)
	{
		this.gray = gray;
	}
	
	public void setBlue(TeamBlue blue)
	{
		this.blue = blue;
	}
	
}