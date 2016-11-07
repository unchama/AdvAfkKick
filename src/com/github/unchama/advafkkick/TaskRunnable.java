package com.github.unchama.advafkkick;


import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskRunnable extends BukkitRunnable{
	private AdvAfkKick plugin = AdvAfkKick.plugin;
	private HashMap<UUID, PlayerData> playermap = AdvAfkKick.playermap;
	private Config config = AdvAfkKick.config;

	//newインスタンスが立ち上がる際に変数を初期化したり代入したりする処理
	public TaskRunnable() {

	}

	@Override
	public void run() {
		playermap = AdvAfkKick.playermap;
		plugin = AdvAfkKick.plugin;

		//playermapが空の時return
		if(playermap.isEmpty()){
			return;
		}
		//プレイヤーマップに記録されているすべてのplayerdataについての処理
		for(PlayerData playerdata : playermap.values()){
			//プレイヤーがオフラインの時処理を終了、次のプレイヤーへ
			if(playerdata.isOffline()){
				continue;
			}
			//プレイﾔｰが必ずオンラインと分かっている処理

			//プレイヤーを取得
			Player player = plugin.getServer().getPlayer(playerdata.uuid);

			//放置判定
			if(player.getLocation().equals(playerdata.loc)){
				playerdata.idletime ++;
			}else{
				playerdata.loc = player.getLocation();
				playerdata.idletime = 0;
			}

		}

	}
}