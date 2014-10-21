package net.ruxion.gui.events;

import java.util.List;
import java.util.UUID;

import net.ruxion.game.GameLogic;
import net.ruxion.game.teams.*;
import net.ruxion.gui.InvGui;
import net.ruxion.gui.InvItems;
import net.ruxion.player.GamePlayer;
import net.ruxion.player.PlayerMaps;
import net.ruxion.player.PlayerTeam;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GuiEvents implements Listener
{
	@EventHandler
	public void onInvClick(InventoryClickEvent e)
	{
		if(e.getWhoClicked().getLocation().getWorld().getName().equalsIgnoreCase("lobby"))
		{
			e.setCancelled(true);
			Player p = (Player)e.getWhoClicked();
			if((e.getCurrentItem() != null) && e.getCurrentItem().getType().equals(Material.COMPASS))
			{
		    	runCompass(p);
			}
			if((e.getCurrentItem() != null) && e.getCurrentItem().getType().equals(Material.WOOL))
			{
				switch(e.getCurrentItem().getDurability())
				{
				case 0:
					runWhite(p);
					break;
				case 1:
					runOrange(p);
					break;
				case 2:
					runPurple(p);
					break;
				case 4:
					runYellow(p);
					break;
				case 5:
					runGreen(p);
					break;
				case 6:
					runPink(p);
					break;
				case 14:
					runRed(p);
					break;
				case 8:
					runGray(p);
					break;
				case 11:
					runBlue(p);
					break;
				default:
					Bukkit.broadcastMessage("how the fuck did this break");
					break;
				}
			}
		}
	}
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent e)
	{
		if(e.getPlayer().getLocation().getWorld().getName().equalsIgnoreCase("lobby"))
		{
			if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			{
				e.setCancelled(true);
				for(InvItems it : InvItems.values())
				{
					if(e.getPlayer().getItemInHand().equals(it.getStack()))
					{
						if(it.getStack().getType().equals(Material.COMPASS))
						{
							runCompass(e.getPlayer());
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e)
	{
		if(e.getItemDrop().getLocation().getWorld().getName().equalsIgnoreCase("lobby"))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e)
	{
		if(e.getDamager().getLocation().getWorld().getName().equalsIgnoreCase("lobby"))
		{
			e.setCancelled(true);
		}
	}
	
	public void runCompass(Player p)
	{
		new InvGui(p).setupEmbeddedInv();
	}
	
	public void runBlue(Player p)
	{
		if(!(GameLogic.game.getBlue().getPlayers().size() == 2))
		{
			if(!GameLogic.game.getBlue().getPlayers().contains(p.getUniqueId()))
			{
				if(!PlayerMaps.maps.get(p.getUniqueId()).getTeam().equals(PlayerTeam.Other))
				{
					GamePlayer p2 = PlayerMaps.maps.get(p.getUniqueId());
					GameLogic.game.getTeamByPlayerTeam(p2.getTeam()).remPlayer(p.getUniqueId());
				}
				
				p.sendMessage(ChatColor.GREEN+"You joined team Blue!");
				p.closeInventory();
				
				GamePlayer player = PlayerMaps.maps.get(p.getUniqueId());
				player.setTeam(PlayerTeam.Blue);
				
				PlayerMaps.maps.put(p.getUniqueId(), player);
				List<UUID> uu = GameLogic.game.getBlue().getPlayers();
				uu.add(p.getUniqueId());
				
				GameLogic.game.setBlue(new TeamBlue(uu));
			}
			else
			{
				p.sendMessage(ChatColor.RED+"You are already on this team!");
				p.closeInventory();
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED+"This team is full sorry!");
			p.closeInventory();
		}
	}
	
	public void runGray(Player p)
	{
		if(!(GameLogic.game.getGray().getPlayers().size() == 2))
		{
			if(!GameLogic.game.getGray().getPlayers().contains(p.getUniqueId()))
			{
				if(!PlayerMaps.maps.get(p.getUniqueId()).getTeam().equals(PlayerTeam.Other))
				{
					GamePlayer p2 = PlayerMaps.maps.get(p.getUniqueId());
					GameLogic.game.getTeamByPlayerTeam(p2.getTeam()).remPlayer(p.getUniqueId());
				}
				
				p.sendMessage(ChatColor.GREEN+"You joined team Gray!");
				p.closeInventory();
				
				GamePlayer player = PlayerMaps.maps.get(p.getUniqueId());
				player.setTeam(PlayerTeam.Gray);
				
				PlayerMaps.maps.put(p.getUniqueId(), player);
				List<UUID> uu = GameLogic.game.getGray().getPlayers();
				uu.add(p.getUniqueId());
				
				GameLogic.game.setGray(new TeamGray(uu));
			}
			else
			{
				p.sendMessage(ChatColor.RED+"You are already on this team!");
				p.closeInventory();
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED+"This team is full sorry!");
			p.closeInventory();
		}
	}
	
	public void runGreen(Player p)
	{
		if(!(GameLogic.game.getGreen().getPlayers().size() == 2))
		{
			if(!GameLogic.game.getGreen().getPlayers().contains(p.getUniqueId()))
			{
				if(!PlayerMaps.maps.get(p.getUniqueId()).getTeam().equals(PlayerTeam.Other))
				{
					GamePlayer p2 = PlayerMaps.maps.get(p.getUniqueId());
					GameLogic.game.getTeamByPlayerTeam(p2.getTeam()).remPlayer(p.getUniqueId());
				}
				
				p.sendMessage(ChatColor.GREEN+"You joined team Green!");
				p.closeInventory();
				
				GamePlayer player = PlayerMaps.maps.get(p.getUniqueId());
				player.setTeam(PlayerTeam.Green);
				
				PlayerMaps.maps.put(p.getUniqueId(), player);
				List<UUID> uu = GameLogic.game.getGreen().getPlayers();
				uu.add(p.getUniqueId());
				
				GameLogic.game.setGreen(new TeamGreen(uu));
			}
			else
			{
				p.sendMessage(ChatColor.RED+"You are already on this team!");
				p.closeInventory();
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED+"This team is full sorry!");
			p.closeInventory();
		}
	}
	
	public void runOrange(Player p)
	{
		if(!(GameLogic.game.getOrange().getPlayers().size() == 2))
		{
			if(!GameLogic.game.getOrange().getPlayers().contains(p.getUniqueId()))
			{
				if(!PlayerMaps.maps.get(p.getUniqueId()).getTeam().equals(PlayerTeam.Other))
				{
					GamePlayer p2 = PlayerMaps.maps.get(p.getUniqueId());
					GameLogic.game.getTeamByPlayerTeam(p2.getTeam()).remPlayer(p.getUniqueId());
				}
				
				p.sendMessage(ChatColor.GREEN+"You joined team Orange!");
				p.closeInventory();
				
				GamePlayer player = PlayerMaps.maps.get(p.getUniqueId());
				player.setTeam(PlayerTeam.Orange);
				
				PlayerMaps.maps.put(p.getUniqueId(), player);
				List<UUID> uu = GameLogic.game.getOrange().getPlayers();
				uu.add(p.getUniqueId());
				
				GameLogic.game.setOrange(new TeamOrange(uu));
			}
			else
			{
				p.sendMessage(ChatColor.RED+"You are already on this team!");
				p.closeInventory();
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED+"This team is full sorry!");
			p.closeInventory();
		}
	}
	
	public void runPink(Player p)
	{
		if(!(GameLogic.game.getPink().getPlayers().size() == 2))
		{
			if(!GameLogic.game.getPink().getPlayers().contains(p.getUniqueId()))
			{
				if(!PlayerMaps.maps.get(p.getUniqueId()).getTeam().equals(PlayerTeam.Other))
				{
					GamePlayer p2 = PlayerMaps.maps.get(p.getUniqueId());
					GameLogic.game.getTeamByPlayerTeam(p2.getTeam()).remPlayer(p.getUniqueId());
				}
				
				p.sendMessage(ChatColor.GREEN+"You joined team Pink!");
				p.closeInventory();
				
				GamePlayer player = PlayerMaps.maps.get(p.getUniqueId());
				player.setTeam(PlayerTeam.Pink);
				
				PlayerMaps.maps.put(p.getUniqueId(), player);
				List<UUID> uu = GameLogic.game.getPink().getPlayers();
				uu.add(p.getUniqueId());
				
				GameLogic.game.setPink(new TeamPink(uu));
			}
			else
			{
				p.sendMessage(ChatColor.RED+"You are already on this team!");
				p.closeInventory();
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED+"This team is full sorry!");
			p.closeInventory();
		}
	}
	
	public void runPurple(Player p)
	{
		if(!(GameLogic.game.getPurple().getPlayers().size() == 2))
		{
			if(!GameLogic.game.getPurple().getPlayers().contains(p.getUniqueId()))
			{
				if(!PlayerMaps.maps.get(p.getUniqueId()).getTeam().equals(PlayerTeam.Other))
				{
					GamePlayer p2 = PlayerMaps.maps.get(p.getUniqueId());
					GameLogic.game.getTeamByPlayerTeam(p2.getTeam()).remPlayer(p.getUniqueId());
				}
				
				p.sendMessage(ChatColor.GREEN+"You joined team Purple!");
				p.closeInventory();
				
				GamePlayer player = PlayerMaps.maps.get(p.getUniqueId());
				player.setTeam(PlayerTeam.Purple);
				
				PlayerMaps.maps.put(p.getUniqueId(), player);
				List<UUID> uu = GameLogic.game.getPurple().getPlayers();
				uu.add(p.getUniqueId());
				
				GameLogic.game.setPurple(new TeamPurple(uu));
			}
			else
			{
				p.sendMessage(ChatColor.RED+"You are already on this team!");
				p.closeInventory();
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED+"This team is full sorry!");
			p.closeInventory();
		}
	}
	
	public void runRed(Player p)
	{
		if(!(GameLogic.game.getRed().getPlayers().size() == 2))
		{
			if(!GameLogic.game.getRed().getPlayers().contains(p.getUniqueId()))
			{
				if(!PlayerMaps.maps.get(p.getUniqueId()).getTeam().equals(PlayerTeam.Other))
				{
					GamePlayer p2 = PlayerMaps.maps.get(p.getUniqueId());
					GameLogic.game.getTeamByPlayerTeam(p2.getTeam()).remPlayer(p.getUniqueId());
				}
				
				p.sendMessage(ChatColor.GREEN+"You joined team Red!");
				p.closeInventory();
				
				GamePlayer player = PlayerMaps.maps.get(p.getUniqueId());
				player.setTeam(PlayerTeam.Red);
				
				PlayerMaps.maps.put(p.getUniqueId(), player);
				List<UUID> uu = GameLogic.game.getRed().getPlayers();
				uu.add(p.getUniqueId());
				
				GameLogic.game.setRed(new TeamRed(uu));
			}
			else
			{
				p.sendMessage(ChatColor.RED+"You are already on this team!");
				p.closeInventory();
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED+"This team is full sorry!");
			p.closeInventory();
		}
	}
	
	public void runWhite(Player p)
	{
		if(!(GameLogic.game.getWhite().getPlayers().size() == 2))
		{
			if(!GameLogic.game.getWhite().getPlayers().contains(p.getUniqueId()))
			{
				if(!PlayerMaps.maps.get(p.getUniqueId()).getTeam().equals(PlayerTeam.Other))
				{
					GamePlayer p2 = PlayerMaps.maps.get(p.getUniqueId());
					GameLogic.game.getTeamByPlayerTeam(p2.getTeam()).remPlayer(p.getUniqueId());
				}
				
				p.sendMessage(ChatColor.GREEN+"You joined team White!");
				p.closeInventory();
				
				GamePlayer player = PlayerMaps.maps.get(p.getUniqueId());
				player.setTeam(PlayerTeam.White);
				
				PlayerMaps.maps.put(p.getUniqueId(), player);
				List<UUID> uu = GameLogic.game.getWhite().getPlayers();
				uu.add(p.getUniqueId());
				
				GameLogic.game.setWhite(new TeamWhite(uu));
			}
			else
			{
				p.sendMessage(ChatColor.RED+"You are already on this team!");
				p.closeInventory();
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED+"This team is full sorry!");
			p.closeInventory();
		}
	}
	
	public void runYellow(Player p)
	{
		if(!(GameLogic.game.getYellow().getPlayers().size() == 2))
		{
			if(!GameLogic.game.getYellow().getPlayers().contains(p.getUniqueId()))
			{
				if(!PlayerMaps.maps.get(p.getUniqueId()).getTeam().equals(PlayerTeam.Other))
				{
					GamePlayer p2 = PlayerMaps.maps.get(p.getUniqueId());
					GameLogic.game.getTeamByPlayerTeam(p2.getTeam()).remPlayer(p.getUniqueId());
				}
				
				p.sendMessage(ChatColor.GREEN+"You joined team Yellow!");
				p.closeInventory();
				
				GamePlayer player = PlayerMaps.maps.get(p.getUniqueId());
				player.setTeam(PlayerTeam.Yellow);
				
				PlayerMaps.maps.put(p.getUniqueId(), player);
				List<UUID> uu = GameLogic.game.getYellow().getPlayers();
				uu.add(p.getUniqueId());
				
				GameLogic.game.setYellow(new TeamYellow(uu));
			}
			else
			{
				p.sendMessage(ChatColor.RED+"You are already on this team!");
				p.closeInventory();
			}
		}
		else
		{
			p.sendMessage(ChatColor.RED+"This team is full sorry!");
			p.closeInventory();
		}
	}
	
}