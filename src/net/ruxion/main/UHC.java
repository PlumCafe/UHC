package net.ruxion.main;

import java.io.File;
import java.io.IOException;

import net.ruxion.cleanup.*;
import net.ruxion.command.*;
import net.ruxion.game.*;
import net.ruxion.lobby.*;
import net.ruxion.gui.events.GuiEvents;
import net.ruxion.player.PlayerLogic;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.block.Block;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class UHC extends JavaPlugin
{
	public static UHC uhc;
	
	@Override
	public void onEnable()
	{
		Cleanup.rebooting = true;
		
		//cleanup old data
		clearPlayers();
		
		//setup ocean world generator
		Cleanup.startWorldGen();
		
		//Register Events
		getServer().getPluginManager().registerEvents(new GameLogic(), this);
		getServer().getPluginManager().registerEvents(new Cleanup(), this);
		getServer().getPluginManager().registerEvents(new Chat(), this);
		getServer().getPluginManager().registerEvents(new PlayerLogic(this), this);
		getServer().getPluginManager().registerEvents(new LobbyEvents(), this);
		getServer().getPluginManager().registerEvents(new GuiEvents(), this);
		getServer().getPluginManager().registerEvents(new SpectatorEvents(), this);
		
		//Set command executor
		getCommand("dev").setExecutor(new CommandDev());
		
		//create file sign config file if not exists
		try
		{
			getDataFolder().mkdirs();
			new File(getDataFolder(), "signconfig.yml").createNewFile();
		}
		catch (IOException e) 
		{
			getServer().getLogger().info("Shutting down [Unable to create sign config file]");
			Bukkit.shutdown();
		}
		
		//create world for lobby if not exists
		Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable()
		{
			public void run()
			{
				WorldCreator wc1 = new WorldCreator("game");
				wc1.createWorld();
				WorldCreator wc2 = new WorldCreator("lobby");
				wc2.generator(new VoidGenerator());
				wc2.createWorld();
				
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doDaylightCycle false");
				
				World w = Bukkit.getWorld("game");
				Block l = w.getHighestBlockAt(w.getSpawnLocation());
				w.setSpawnLocation(l.getX(), l.getY(), l.getZ());
				
				World w2 = Bukkit.getWorld("game");
				w2.setTime(0);
				w2.setStorm(false);
				w2.setWeatherDuration(0);
				
				Bukkit.getLogger().info("worlds created turning off whitelist");
				
				Cleanup.rebooting = false;
			}
		}, 100L);
		
		LobbyEvents.l = new Lobby(null, Bukkit.getWorld("lobby"), this);
		GameLogic.game = new ObjGame(LobbyEvents.l, Bukkit.getWorld("game"));
		
		uhc = this;
		
	}
	
	@Override
	public void onDisable()
	{	
		clearPlayers();
	}
	
	public static void clearPlayers()
	{
		File f2 = new File(Bukkit.getWorldContainer(), "lobby/playerdata/");
		if(f2.listFiles() != null)
		{
			for(File f : f2.listFiles())
			{
				f.delete();
			}
		}
	}

	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
	{
		return new VoidGenerator();
	}
	
//	//returns null if amount is higher then array number
//	//er some comment i don't remember
//	public List<Player> getRPlayer(Player[] playerarray, int amount)
//	{
//		List<Player> pl = new ArrayList<Player>();
//		if(amount > playerarray.length)
//		{
//			return null;
//		}
//		Random random = new Random(amount);
//		List<Player> pl2 = Arrays.asList(playerarray);
//		while(true)
//		{
//			Player p = pl2.get(Math.abs(random.nextInt()));
//			if(!pl.contains(p))
//			{
//				pl.add(p);
//			}else{
//				break;
//			}
//			random = new Random(amount);
//		}
//		return pl;
//	}
}
