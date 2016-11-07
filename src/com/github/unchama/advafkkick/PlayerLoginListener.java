package com.github.unchama.advafkkick;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLoginListener implements Listener {
	private AdvAfkKick plugin = AdvAfkKick.plugin;
	private Config config = AdvAfkKick.config;
	private HashMap<UUID,PlayerData> playermap = AdvAfkKick.playermap;
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		if ((e.getResult().equals(PlayerLoginEvent.Result.KICK_FULL))) {
			//満員時、キック対象のプレイヤーを検索
			for(Player p : plugin.getServer().getOnlinePlayers()){
				if(p.hasPermission("SeichiAssist.fullstay")){
					//権限持ちはスルー
					continue;
				}
				//UUIDを取得
				UUID uuid = p.getUniqueId();
				//playerdata取得
				PlayerData playerdata = playermap.get(uuid);
				//念のためエラー分岐
				if(playerdata == null){
					plugin.getLogger().warning(p.getName() + "'s playerdata is not found.");
					continue;
				}
				//閾値を超えていたら追い出しを実行して処理を終了
				if(playerdata.idletime >= config.getKickMinute()){
					plugin.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "満員の為放置プレイヤーを追い出します");
					Util.sendEveryMessage(ChatColor.YELLOW + "満員の為放置プレイヤーを追い出します");
					p.kickPlayer(ChatColor.YELLOW + "満員の為、放置プレイヤーはキックされます。再度ログイン可能です");
					e.allow();
					return;
				}
			}
			//キック対象が居なかったら…

			//権限持ちはログインさせる
			if(e.getPlayer().hasPermission("advafkkick.bypass")){
				e.allow();
				return;
			}

			//メッセージ表示
			e.disallow(PlayerLoginEvent.Result.KICK_FULL,ChatColor.YELLOW + "ただ今満員です。しばらく経ってから再度お試し下さい");
		}
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		UUID uuid = p.getUniqueId();
		//PlayerDataを新規作成
		playermap.put(uuid, new PlayerData(p));
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		//退出したplayerを取得
		Player player = e.getPlayer();
		//プレイヤーのuuidを取得
		UUID uuid = player.getUniqueId();
		//プレイヤーデータ取得
		PlayerData playerdata = playermap.get(uuid);
		//念のためエラー分岐
		if(playerdata == null){
			plugin.getLogger().warning(player.getName() + "'s playerdata is not found.");
			return;
		}
		//不要なplayerdataを削除
		playermap.remove(uuid);
	}
}
