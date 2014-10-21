package net.ruxion.command.restrictions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

public class RestrictTab implements Listener
{
	@EventHandler
	public void onTab(PlayerChatTabCompleteEvent e)
	{
		e.getTabCompletions().clear();
	}
}