package com.github.unchama.advafkkick;

import org.bukkit.entity.Player;

public class Util {
	//プレイヤーネームを格納（toLowerCaseで全て小文字にする。)
	public static String getName(Player p) {
		return p.getName().toLowerCase();
	}
	public static String getName(String name) {
		return name.toLowerCase();
	}
	public static double toDouble(String s){
		return Double.parseDouble(s);
	}
	public static int toInt(String s) {
		return Integer.parseInt(s);
	}

	public static void sendEveryMessage(String str){
		AdvAfkKick plugin = AdvAfkKick.plugin;
		for ( Player player : plugin.getServer().getOnlinePlayers() ) {
			player.sendMessage(str);
		}
	}
}
