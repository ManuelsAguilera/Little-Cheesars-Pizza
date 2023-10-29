package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.List;

public class Colectible extends FallingObject {
	private ArrayList<Sprite> spriteList;
	private String id;
	
	public Colectible(String id) {
		super(0);
		int minX = 16;
        int maxX = 463;
        int randomX = minX + (int) (Math.random() * ((maxX - minX)));
		this.setX(randomX);
		//seteado X donde empezar
		spriteList = new ArrayList<Sprite>();

		
		
		if (id.equals("Speed"))
		{
			Texture T = new Texture("drop.png"); //placeholder
			spriteList.add(new Sprite(T));
		}
		else {
			Texture T = new Texture("dropBad.png"); //Mientras no tengamos otros mas
			spriteList.add(new Sprite(T));
		}
		

	}

	

	@Override
	public void reset() {
		this.remove();
	}
	
}
