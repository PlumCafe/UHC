package net.ruxion.command;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.ruxion.cleanup.Cleanup;
import net.ruxion.game.GameLogic;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CommandDev implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
		if(l.equalsIgnoreCase("dev"))
		{
			if(args.length == 0)
			{
				help(s);
			}else if(args.length == 1){
				if(args[0].equalsIgnoreCase("cleanup"))
				{
					Cleanup.startCleanup();
				}
				if(args[0].equalsIgnoreCase("pon3"))
				{
					Player p = ((Player)s);
					p.sendMessage(p.getWorld().toString());
				}
				if(args[0].equalsIgnoreCase("game"))
				{
					((Player)s).teleport(Bukkit.getWorld("game").getSpawnLocation());
				}
				if(args[0].equalsIgnoreCase("lobby"))
				{
					((Player)s).teleport(new Location(Bukkit.getWorld("lobby"), 243.5, 21.5, 0.5, -1f, 0.5f));
				}
				if(args[0].equalsIgnoreCase("killall"))
				{
					for(Entity e : Bukkit.getWorld("lobby").getEntities())
					{
						if(!e.getType().equals(EntityType.PLAYER)){
							e.remove();
						}
					}
				}
				if(args[0].equalsIgnoreCase("start"))
				{
					GameLogic.startGame();
				}
				if(args[0].equalsIgnoreCase("glow"))
				{
					Player p = ((Player)s);
					addGlowShit(p.getItemInHand());
				}
				if(args[0].equalsIgnoreCase("lava"))
				{
					Player p = ((Player)s);
					setSafeLocation(p.getLocation());
					Bukkit.broadcastMessage(p.getLocation().toString());
				}
			}
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
        
        Bukkit.broadcastMessage(blocks.size()+"");
		
		for(Block b : blocks)
		{
			if(b.getType().equals(Material.LAVA)||b.getType().equals(Material.STATIONARY_LAVA))
			{
				b.setType(Material.STONE);
				Bukkit.broadcastMessage("run");
			}
			Bukkit.broadcastMessage(l.getBlockX()+","+l.getBlockY()+","+l.getBlockZ()+"");
		}
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack addGlowShit(ItemStack is)
	{
		try {
		    Field f = Enchantment.class.getDeclaredField("acceptingNew");
		    f.setAccessible(true);
		    f.set(null, true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		EnchantmentWrapper wrapperthing = new EnchantmentWrapper(63)
		{
			@Override 
			public String getName()
			{
				return "";
			}
		};
		if(!(Enchantment.getById(63) != null))
		{
			Enchantment.registerEnchantment(wrapperthing);
		}
		is.addUnsafeEnchantment(wrapperthing, 1);
		return is;
	}
	
	public void help(CommandSender s)
	{
		s.sendMessage
		(
			new String[]
			{
					ChatColor.GREEN+"==========================================",
					ChatColor.BLUE+"/dev cleanup",
					ChatColor.GREEN+"=========================================="
			}
		);
	}
	
}