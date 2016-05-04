package com.sweter.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sweter.game.dungeonCommander;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 870;
		config.height = 540;
		config.title = "Dungeon Commander Alfa 0.0";
		new LwjglApplication(new dungeonCommander(), config);
	}
}
