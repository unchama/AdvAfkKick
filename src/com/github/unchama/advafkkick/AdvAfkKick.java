package com.github.unchama.advafkkick;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

public class AdvAfkKick extends JavaPlugin {
	//Playerdataに依存するデータリスト
	public static final HashMap<UUID,PlayerData> playermap = new HashMap<UUID,PlayerData>();
	@Override
	public void onEnable(){

	}
	@Override
	public void onDisable(){

	}
}
