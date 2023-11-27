package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Colectible extends FallingObject {
	private HashMap<String,Sprite> colectibleMap;
	private List<String> colectibleTypes;
	private String id;
	
	public Colectible(String id) {
		super(600);
		//seteado X donde empezar
		colectibleMap = new HashMap<String,Sprite>();
		colectibleTypes = new ArrayList<String>();
		
		//Agregar texturas
		colectibleMap.put("Speed", new Sprite(new Texture("Pluma.png")));//Velocidad a Cheesar
		colectibleMap.put("HP", new Sprite(new Texture("Pluma.png")));//Más vida a Cheesar(Máx. tres)
		colectibleMap.put("Bonus", new Sprite(new Texture("Pluma.png")));//Más puntaje/ganancias
		
		//Agregar a la lista
		colectibleTypes.addAll(colectibleMap.keySet());
		id = "";
		reset();
	}
	
	private void resetSprite() {
        Random random = new Random();
        String randomType = colectibleTypes.get(random.nextInt(colectibleTypes.size()));
        id = randomType;
        setSprite(colectibleMap.get(id));
    }
	
	public void reset() {
		resetSprite();
        int minX = 16;
        int maxX = 745;
        int randomX = minX + (int) (Math.random() * ((maxX - minX)));
        this.setX(randomX);
        this.resetHeight();
	}
	
	public ArrayList getIngTypes(){
    	ArrayList<String> cpy = new ArrayList<String>();
    	cpy.addAll(colectibleMap.keySet());
    	return cpy;
    }
	
	public String getType() {
		// TODO Auto-generated method stub
		return id;
	}	
}
