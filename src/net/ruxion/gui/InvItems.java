package net.ruxion.gui;

import net.ruxion.player.PlayerTeam;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum InvItems
{
	COMPASS(
			InvGui.cItem(
					Material.COMPASS,
					new String[]{
							ChatColor.YELLOW+"Right click this item",
							ChatColor.YELLOW+"in order to join a team"
							    },
					ChatColor.GREEN+"Right click to open teams!"
						)
			, 0, false, PlayerTeam.Other),
	WhiteWool(
			InvGui.cItem(
					(byte)0,
					ChatColor.WHITE+"Click this to join team White"
						)
			, 0, true, PlayerTeam.White),
	OrangeWool(
			InvGui.cItem(
					(byte)1,
					ChatColor.GOLD+"Click this to join team Orange"
						)
			, 1, true, PlayerTeam.Orange),
	PurpleWool(
			InvGui.cItem(
					(byte)2,
					ChatColor.DARK_PURPLE+"Click this to join team Purple"
						)
			, 2, true, PlayerTeam.Purple),
	YellowWool(
			InvGui.cItem(
					(byte)4,
					ChatColor.YELLOW+"Click this to join team Yellow"
						)
			, 3, true, PlayerTeam.Yellow),
	GreenWool(
			InvGui.cItem(
					(byte)5,
					ChatColor.GREEN+"Click this to join team Green"
						)
			, 4, true, PlayerTeam.Green),
	PinkWool(
			InvGui.cItem(
					(byte)6,
					ChatColor.LIGHT_PURPLE+"Click this to join team Pink"
						)
			, 5, true, PlayerTeam.Pink),
	RedWool(
			InvGui.cItem(
					(byte)14,
					ChatColor.RED+"Click this to join team Red"
						)
			, 6, true, PlayerTeam.Red),
	GrayWool(
			InvGui.cItem(
					(byte)8,
					ChatColor.GRAY+"Click this to join team Gray"
						)
			, 7, true, PlayerTeam.Gray),
	BlueWool(
			InvGui.cItem(
					(byte)11,
					ChatColor.BLUE+"Click this to join team Blue"
						)
			, 8, true, PlayerTeam.Blue);
	
	private int pos;
	private ItemStack i;
	private boolean embedded;
	private PlayerTeam team;
	
	private InvItems(ItemStack i, int pos, boolean embedded, PlayerTeam team)
	{
		this.pos = pos;
		this.i = i;
		this.embedded = embedded;
		this.team = team;
	}
	
	public ItemStack getStack(){return i;}
	public int getPos(){return pos;}
	public boolean isEmbedded(){return embedded;}
	public PlayerTeam getTeam(){return team;}
	
}