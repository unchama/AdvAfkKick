package com.github.unchama.advafkkick;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

public class AdvAfkKick extends JavaPlugin {
	public static AdvAfkKick plugin;
	public static Config config;
	//Playerdataに依存するデータリスト
	public static final HashMap<UUID,PlayerData> playermap = new HashMap<UUID,PlayerData>();
	@Override
	public void onEnable(){
		plugin = this;

		//コンフィグ系の設定は全てConfig.javaに移動
		config = new Config(this);
		config.loadConfig();

		//リスナーの登録
		getServer().getPluginManager().registerEvents(new PlayerLoginListener(), this);
	}
	@Override
	public void onDisable(){

	}
}
