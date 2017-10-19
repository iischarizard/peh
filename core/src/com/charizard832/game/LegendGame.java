package com.charizard832.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.charizard832.gameobjects.Legend;
import com.charizard832.helpers.AssetLoader;
import com.charizard832.screens.GameScreen;
import com.charizard832.screens.TutorialScreen;

public class LegendGame extends Game {

	private ActionResolver ar;

	public LegendGame(ActionResolver ar){
		this.ar = ar;
	}

	@Override
	public void create() {
		Gdx.app.log("Peh", "created");
		AssetLoader.load();
		setScreen(new GameScreen(this));
	}

	public ActionResolver getAr(){return ar;}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
