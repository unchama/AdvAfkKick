package com.github.unchama.advafkkick;

import org.bukkit.configuration.file.FileConfiguration;

public class Config{
	private static FileConfiguration config;
	private AdvAfkKick plugin;

	//コンストラクタ
	Config(AdvAfkKick _plugin){
		plugin = _plugin;
		saveDefaultConfig();
	}

	//コンフィグのロード
	public void loadConfig(){
		config = getConfig();
	}

	/*
	//コンフィグのリロード
	public void reloadConfig(){
		plugin.reloadConfig();
		config = getConfig();
	}
	*/

	/*
	//コンフィグのセーブ
	public void saveConfig(){
		plugin.saveConfig();
	}
	*/

	//plugin.ymlがない時にDefaultのファイルを生成
	public void saveDefaultConfig(){
		plugin.saveDefaultConfig();
	}

	//plugin.ymlファイルからの読み込み
	public FileConfiguration getConfig(){
		return plugin.getConfig();
	}

	public int getKickMinute(){
		return Util.toInt(config.getString("kickminute"));
	}


}