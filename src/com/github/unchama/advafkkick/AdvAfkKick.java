package com.github.unchama.advafkkick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class AdvAfkKick extends JavaPlugin {
	public static AdvAfkKick plugin;
	public static Config config;
	//Playerdataに依存するデータリスト
	public static final HashMap<UUID,PlayerData> playermap = new HashMap<UUID,PlayerData>();
	//起動するタスクリスト
	private List<BukkitTask> tasklist = new ArrayList<BukkitTask>();
	@Override
	public void onEnable(){
		plugin = this;

		//コンフィグ系の設定は全てConfig.javaに移動
		config = new Config(this);
		config.loadConfig();

		//リスナーの登録
		getServer().getPluginManager().registerEvents(new PlayerLoginListener(), this);

		//オンラインの全てのプレイヤーを処理
		for(Player p : getServer().getOnlinePlayers()){
			UUID uuid = p.getUniqueId();
			//PlayerDataを新規作成
			playermap.put(uuid, new PlayerData(p));
		}

		//タスクスタート
		startTaskRunnable();

		getLogger().info("AdvAfkKick is Enabled!");
	}
	@Override
	public void onDisable(){
		//全てのタスクをキャンセル
		stopAllTaskRunnable();

		getLogger().info("AdvAfkKick is Disabled!");
	}

	public void startTaskRunnable(){
		//一定時間おきに処理を実行するタスク
		tasklist.add(new TaskRunnable().runTaskTimer(this,0,1200));
	}
	public void stopAllTaskRunnable(){
		for(BukkitTask task:tasklist){
			task.cancel();
		}
	}
}
