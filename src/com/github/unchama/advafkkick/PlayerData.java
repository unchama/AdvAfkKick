package com.github.unchama.advafkkick;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;




public class PlayerData {
	//プレイヤー名
	public String name;
	//UUID
	public UUID uuid;
	//現在座標
	public Location loc;
	//放置時間
	public int idletime;

	public PlayerData(Player player){
		//初期値を設定
		name = player.getName().toLowerCase();
		uuid = player.getUniqueId();
		loc = null;
		idletime = 0;

	}
}
