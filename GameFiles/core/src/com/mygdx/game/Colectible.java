package com.mygdx.game;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.scenes.scene2d.ui.List;

public class Colectible extends FallingObject {
	private HashMap<String,Sprite> spriteMap;
	
	private String id;
	
	public Colectible(String id) {
		super(600);
		

		//seteado X donde empezar
		spriteMap = new HashMap<String,Sprite>();
		spriteMap.put("Speed", new Sprite(new Texture("drop.png")));//placeholder
		//pensado para tener mas sprites
		Sprite sprite = new Sprite(new Texture("drop.png"));
		if (sprite!=null)
		{
			setSprite(sprite);
			
			int minX = 16;
	        int maxX = 463;
	        int randomX = minX + (int) (Math.random() * ((maxX - minX)));
			this.setX(randomX);
			this.resetHeight();
			

		}
		
	}

	

	@Override
	public void reset() {
		this.remove();
	}



	public String getType() {
		// TODO Auto-generated method stub
		return id;
	}
	
}
