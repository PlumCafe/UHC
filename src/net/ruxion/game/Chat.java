package net.ruxion.game;

import net.ruxion.player.PlayerMaps;
import net.ruxion.player.PlayerTeam;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Chat implements Listener
{
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e)
	{
		Player p = e.getPlayer();
		PermissionUser user = PermissionsEx.getUser(p);
		if(!PlayerMaps.maps.get(p.getUniqueId()).getTeam().equals(PlayerTeam.Other))
		{
			e.setFormat(
					ChatColor.translateAlternateColorCodes('&',
							GameLogic.game.getTeamByPlayerTeam(PlayerMaps.maps.get(p.getUniqueId()).getTeam()).getTeamPrefix()
							+user.getPrefix()
							+p.getName()
							+user.getSuffix()
							+ChatColor.GRAY
							+": ")
							+ChatColor.WHITE+e.getMessage()
					);
		}else{
			e.setFormat(
					ChatColor.translateAlternateColorCodes('&',
							user.getPrefix()
							+p.getName()
							+user.getSuffix()
							+ChatColor.GRAY
							+": ")
							+ChatColor.WHITE+e.getMessage()
					);
		}
	}
}