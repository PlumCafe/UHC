package net.ruxion.cleanup;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import net.ruxion.game.GameLogic;
import net.ruxion.game.ObjGame;
import net.ruxion.game.SpectatorEvents;
import net.ruxion.lobby.Lobby;
import net.ruxion.lobby.LobbyEvents;
import net.ruxion.main.UHC;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class Cleanup implements Listener
{
	
	private static BiomeHandler swapper;
	
	public static boolean rebooting = false;
	
	@EventHandler
	public void onJoin(PlayerLoginEvent e)
	{
		//Disallow players from joining during cleanup
		if(rebooting==true)e.disallow(Result.KICK_OTHER, ChatColor.RED+"Server is cleaning up");
	}
	
	@SuppressWarnings("deprecation")
	public static void startCleanup()
	{
		//turn on whitelist
		Cleanup.rebooting = true;
		
		//Kick all players to start cleanup
		for(Player p : Bukkit.getOnlinePlayers())
		{
			p.kickPlayer(ChatColor.RED+"Server cleaning up");
		}
		
		SpectatorEvents.clearSpecators();
		UHC.clearPlayers();
		GameLogic.inGame = false;
		GameLogic.wipeGameStage();
		
		//unload world
		File wf = new File(Bukkit.getWorldContainer().getParentFile(), "game");
		Bukkit.unloadWorld(Bukkit.getWorld("game"), false);
		
		//delete old world
		try {
			FileUtils.deleteDirectory(wf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//create new world
		WorldCreator wc = new WorldCreator("game");
		wc.createWorld();
		
		//cleanup players
		UHC.clearPlayers();
		
		//restart game and lobby
		LobbyEvents.l = new Lobby(null, Bukkit.getWorld("lobby"), Bukkit.getPluginManager().getPlugin("UHC"));
		GameLogic.game = new ObjGame(LobbyEvents.l, Bukkit.getWorld("game"));
		
		//turn off whitelist
		Cleanup.rebooting = false;
		
	}

	public static void startWorldGen()
	{
		try
		{
			Class<?> clazz = Class.forName("net.ruxion.cleanup.BiomeHandler");
			swapper = ((BiomeHandler) clazz.getConstructor(new Class[0]).newInstance(new Object[0]));
		}
		catch(InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		//swapper.swapBiome(Biome.BEACH, Biome.BIRCH_FOREST);
		//swapper.swapBiome(Biome.OCEAN, Biome.FOREST);
		//swapper.swapBiome(Biome.DEEP_OCEAN, Biome.COLD_TAIGA);
	}
	
	enum Biome 
	{
		OCEAN(0), PLAINS(1), DESERT(2), EXTREME_HILLS(3), FOREST(4), TAIGA(5), SWAMPLAND(
				6), RIVER(7), HELL(8), SKY(9), FROZEN_OCEAN(10), FROZEN_RIVER(11), ICE_PLAINS(
				12), ICE_MOUNTAINS(13), MUSHROOM_ISLAND(14), MUSHROOM_SHORE(15), BEACH(
				16), DESERT_HILLS(17), FOREST_HILLS(18), TAIGA_HILLS(19), SMALL_MOUNTAINS(
				20), JUNGLE(21), JUNGLE_HILLS(22), JUNGLE_EDGE(23), DEEP_OCEAN(24), STONE_BEACH(
				25), COLD_BEACH(26), BIRCH_FOREST(27), BIRCH_FOREST_HILLS(28), ROOFED_FOREST(
				29), COLD_TAIGA(30), COLD_TAIGA_HILLS(31), MEGA_TAIGA(32), MEGA_TAIGA_HILLS(
				33), EXTREME_HILLS_PLUS(34), SAVANNA(35), SAVANNA_PLATEAU(36), MESA(
				37), MESA_PLATEAU_F(38), MESA_PLATEAU(39);

		private final int id;
		
		private Biome(int id){this.id = id;}

		public int getId(){return this.id;}
	}
	
}