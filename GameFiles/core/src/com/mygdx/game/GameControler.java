package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GameControler implements GameObject {
    private long score;
    private int lifes;
    private float speedDiff;
    private Player cheesar;
    private ArrayList<String> pizzaOrder;
    private ArrayList<Ingredients> ingredientsList; // Lista de ingredientes

    public GameControler() {
        cheesar = new Player(250);
        score = 0;
        lifes = 3;
        speedDiff = 1;
        pizzaOrder = new ArrayList<String>();
        ingredientsList = new ArrayList<Ingredients>();
        // Agregar dos ingredientes a la lista
        
        addIngredient(2);
        generateOrder();
        
        
    }

    @Override
    public void update(float delta) {
        cheesar.update(delta);
        
        //checkear si se termino la lista de ordenes
        if (pizzaOrder.size() == 0)
        {
        	generateOrder();
        	speedDiff+=0.2;
        	score++;
        	difficultyAdjust(speedDiff);
        	//Ver si agregar mas ingredientes
            if (score % 3 == 0)
            {
            	addIngredient(1);
            }
            //elegir si añadir power Up
            
        }
        
        	
        
        for (Ingredients ingredient : ingredientsList) {
            ingredient.update(delta);
        }
        
        Ingredients auxIng = detectCollisionsIng();  //get Ingredient that colided.
        if (auxIng != null)
        {
        	
        	String type = auxIng.getType();
        	checkCorrectOrder(type);
        	auxIng.reset();
        }
        
        
    	
    }

    

	@Override
    public void render(SpriteBatch batch) {
        // Renderizar ingredientes y otros elementos del juego
    	cheesar.render(batch);
    	
        for (Ingredients ingredient : ingredientsList) {
            ingredient.render(batch);
        }
    }

    @Override
    public Rectangle getBounds() {
        // Devolver los límites del controlador del juego (que no es necesario)
        return null;
    }
    private void generateOrder() {
        // Vaciar la lista de pedidos de pizza si tiene datos
        if (pizzaOrder != null) {
            pizzaOrder.clear();
        }

        // Elegir al azar uno de los 4 ingredientes disponibles
        String[] availableIngredients = {"Salame", "Pina", "Champinon", "Pimenton"};
        Random random = new Random();

        // Agregar ingredientes al pedido (por ejemplo, 3 ingredientes)
        int numberOfIngredients = 2; // Ajusta el número de ingredientes según tus preferencias
        for (int i = 0; i < numberOfIngredients-1; i++) {
            int randomIndex = random.nextInt(availableIngredients.length);
            pizzaOrder.add(availableIngredients[randomIndex]);
        }
    }
    
    private void checkCorrectOrder(String type) {
		//Revisa si es parte de una pizza ordenada
    	//si no es, pierdes vida
    	if (pizzaOrder == null)
    		return;
    	
    	for (int i=0; i < pizzaOrder.size(); i++) {
    		String order = pizzaOrder.get(i);
    		
    		if (type.equals(order))
    		{
    			pizzaOrder.remove(i);
    			return;
    		}
    			
    	}
    	this.lifes--;
	}
    private Ingredients detectCollisionsIng() {
        Rectangle playerBounds = cheesar.getBounds();

        for (Ingredients ingredient : ingredientsList) {
            Rectangle ingredientBounds = ingredient.getBounds();

            if (playerBounds.overlaps(ingredientBounds)) {
                // Colisión detectada, aumenta el puntaje y elimina el ingrediente
                return ingredient; // Rompe el bucle después de encontrar una colisión (si no se deben permitir múltiples colisiones)
            }
        }
        return null;
    }
    private void addIngredient(int cant)
    {
    	for (int i = 0; i < cant; i++)
    	{
    		ingredientsList.add(new Ingredients(0));
    	}
    }
    private void difficultyAdjust(float speedModificator)
    {
    	for (Ingredients ingredient: ingredientsList)
    	{
    		ingredient.modificarVeloc(speedModificator);
    	}
    }
    //Setters y getters necesarios
    public long getScore() {
    	return this.score;
    }
    public int getLifes()
    {
    	return this.lifes;
    }
    public String getOrderConcat()
    {
    	if (pizzaOrder == null)
    		return "Esperando comensales";
    	//Regresa el contenido de la orderPizza concatenado.
    	String concatList = "";
    	for (String order: pizzaOrder)
    	{
    		concatList += concatList ;
    		concatList += order;
    	}
    	return concatList;
    }
    //Revisar si es gameOver para el contexto
    public boolean gameOver()
    {
    	return lifes <=0;
    }
}
    
