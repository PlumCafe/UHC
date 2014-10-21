package net.ruxion.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.ruxion.game.GameLogic;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InvGui
{
	Player p;
	
	public InvGui(Player p)
	{
		this.p = p;
	}
	
	public void setupInventory()
	{
		for(InvItems i : InvItems.values())
		{
			if(!i.isEmbedded())
			{
				p.getInventory().setItem(i.getPos(), i.getStack());
			}
		}
	}
	
	public void setupEmbeddedInv()
	{
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.AQUA+"Teams");
		for(InvItems i : InvItems.values())
		{
			if(i.isEmbedded())
			{
				ItemStack stack = i.getStack();
				ItemMeta meta = stack.getItemMeta();
				List<String> lore = new ArrayList<String>();
				lore.add(ChatColor.AQUA+"=====================");
				lore.add(ChatColor.YELLOW+"Player's on this team");
				lore.add(ChatColor.AQUA+"=====================");
				String[] s = GameLogic.game.getTeamByPlayerTeam(i.getTeam()).getPlayersLore();
				for(String s2 : s)lore.add(s2);
				meta.setLore(lore);
				stack.setItemMeta(meta);
				inv.setItem(i.getPos(), stack);
			}
		}
		p.openInventory(inv);
	}
	
	public static ItemStack cItem(byte t, String name)
	{
		ItemStack is = new ItemStack(Material.WOOL, 1, t);
		ItemMeta md = is.getItemMeta();
		md.setDisplayName(name);
		is.setItemMeta(md);
		return is;
	}
	
	public static ItemStack cItem(Material m, String[] lore, String name)
	{
		ItemStack is = new ItemStack(m);
		ItemMeta md = is.getItemMeta();
		md.setDisplayName(name);
		md.setLore(Arrays.asList(lore));
		is.setItemMeta(md);
		return is;
	}
	
}