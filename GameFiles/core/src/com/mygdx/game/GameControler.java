package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class GameControler implements GameObject {
    private long score; // Puntaje
    private int lifes; // Vidas de Cheesar
    private int cont; // Contador auxiliar
    private int cantIng; // Cantidad de ingredientes
    private float speedDiff; // Velocidad de Cheesar
    private String statusChange; // Cambio de estado
    private float statusChangeTemp; // Temporizador de cambio de estado
    private Player cheesar;
    private Cheesar animation;
    private ArrayList<String> pizzaOrder; // Pedido de ingredientes a recolectar
    private ArrayList<Ingredients> ingredientsList; // Lista de ingredientes
    private ArrayList<Collectible> powerUps; // Lista de PowerUps
    private static GameControler instance;    
    private PowerUpStrategy strategy;
    //private LevelConfig currentLevelConfig;
    
    private GameControler(){
        cheesar = new Player(115);// Modifica la altura de la hitbox
        animation = new Cheesar(0, 0);// Modifica la altura de la animación
        
        score = 0;
        lifes = 3;
        cont = 2;
        cantIng = 7;
        speedDiff = 1;
        statusChange = "";
        pizzaOrder = new ArrayList<String>();
        ingredientsList = new ArrayList<Ingredients>();
        powerUps = new ArrayList<Collectible>();
        strategy = null;
        
        // Agregar dos ingredientes a la lista
        addIngredient(cantIng);
        generateOrder(2);
        new ShapeRenderer();
        addColectible(1);
    }
    
    public static GameControler getInstance() {
        if (instance == null) {
            instance = new GameControler();
        }
        return instance;
    }
    
    @Override
    public void update(float delta) {
        cheesar.update(delta);
        animation.update(delta); //EXCLUSIVO ANIMACIÓN
        
        //Checkear si se terminó la orden de ingredientes
        if (pizzaOrder.size() == 0)
        {
        	speedDiff+=0.2;
        	score++;
        	difficultyAdjust(speedDiff);
        	
        	if (score % 6 == 0) {
        		cont++;
        		generateOrder(cont);
        	} else {
        		generateOrder(cont);
        	}
        	
        	//Verificar si agregar más ingredientes
            if (score % 3 == 0)
            {
            	addIngredient(1);
            	cantIng++;
            }
            
            //Probabilidad de aparición de Power-Up
            Random random = new Random();
            if (0 == random.nextInt(7)) //probabilidad de 1/7
            	addColectible(1);
        }
        
        for (Ingredients ingredient : ingredientsList) {
            ingredient.update(delta);
        }
        
        for (Collectible colectible : powerUps) {
            colectible.update(delta);
        }
        
        Ingredients auxIng = detectCollisionsIng();  //get Ingredient that collided.
        if (auxIng != null)
        {
        	String type = auxIng.getType();
        	checkCorrectOrder(type);
        	auxIng.reset();
        }
        
        Collectible auxCol = detectCollisionsCol();
        if (auxCol != null)
        {
        	String type = auxCol.getType();
        	activateColectible(type);
        	auxCol.remove();
        }
        //Check change status
        if (statusChangeTemp > 0)
        {
        	statusChangeTemp-=delta;
        }
        
        if (statusChangeTemp <= 0 & statusChange == "Speed" )
        {
        	cheesar.modificarVeloc(1); // Reiniciar velocidad
        	animation.modificarVeloc(1);
        	statusChange = "";
        }
        
        if (statusChangeTemp <= 0 & statusChange == "Bonus" )
        {
        	statusChange = "";
        }

    }

	private void activateColectible(String type) {
		if (type.equals("Speed"))
		{
			strategy = new Speed();
			strategy.applyPowerUp(cheesar, animation, instance);
		}
		
		if (type.equals("HP"))
		{
			strategy = new HP();
			strategy.applyPowerUp(cheesar, animation, instance);
		}
		
		if (type.equals("Bonus"))
		{
			statusChangeTemp = 5.0f;
			statusChange = "Bonus";
		}
		
	}

	private void addColectible(int cant) {
		for (int i = 0; i < cant; i++)
    	{
			powerUps.add(new Collectible(0));
    	}
	}

	@Override
	 public void render(SpriteBatch batch) {
	    // Renderizar al jugador
	    cheesar.render(batch);
	    
	    // Renderizar animación
	    animation.render(batch);
	    
	    // Renderizar los ingredientes
	    if(statusChange == "Bonus")
	    {
	    	for (Ingredients ingredient : ingredientsList) {
	    		ingredient.bonus();
	    		ingredient.render(batch);
		    }
	    }
	    else
	    {
	    	for (Ingredients ingredient : ingredientsList) {
		        ingredient.render(batch);
		    }
	    }
	    
	    // Renderizar power-ups
	    ArrayList<Collectible> colToRemove = new ArrayList<Collectible>();
	    for (Collectible colectible : powerUps) {
	        colectible.render(batch);
	        if (colectible.getRemove())
	        	colToRemove.add(colectible);
	    }
	    powerUps.removeAll(colToRemove);
	}
	
    @Override
    public Rectangle getBounds() {
        return null;
    }
    
    private void generateOrder(int number) {
        // Vaciar la lista de pedidos de pizza si tiene datos
        if (pizzaOrder != null) {
            pizzaOrder.clear();
        }

        // Elegir al azar uno de los 4 ingredientes disponibles
        String[] availableIngredients = {"Salame", "Piña", "Champiñón", "Pimentón"};
        Random random = new Random();

        // Agregar ingredientes al pedido (por ejemplo, 3 ingredientes)
        int numberOfIngredients = number; // Ajusta el número de ingredientes según tus preferencias
        for (int i = 0; i < numberOfIngredients; i++) {
            int randomIndex = random.nextInt(availableIngredients.length);
            pizzaOrder.add(availableIngredients[randomIndex]);
        }
    }
    
    private void checkCorrectOrder(String type) {
		//Revisa si es parte de una pizza ordenada
    	//si no es, pierdes vida
    	if (pizzaOrder == null)
    		return;
    	
    	if(type == "BonusPoint") {
    		score++;
    		return;
    	}
    	
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
    
    private Collectible detectCollisionsCol() {
        Rectangle playerBounds = cheesar.getBounds();

        for (Collectible colectible : powerUps) {
            Rectangle colectibleBounds = colectible.getBounds();

            if (playerBounds.overlaps(colectibleBounds)) {
                // Colisión detectada, aumenta el puntaje y elimina el ingrediente
                return colectible; // Rompe el bucle después de encontrar una colisión (si no se deben permitir múltiples colisiones)
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
    
    public int getLifes() {
    	return this.lifes;
    }
    
    public void setLifes(int lifes) {
    	this.lifes = lifes;
    }
    
    public void setStatusChangeTemp(float duration) {
    	this.statusChangeTemp = duration;
    }
    
    public void setStatusChange(String change) {
    	this.statusChange = change;
    }
    
    public String getOrderConcat() {
    	if (pizzaOrder == null)
    		return "Esperando comensales";
    	//Regresa el contenido de la orderPizza concatenado.
    	String concatList = "";
    	for (String order: pizzaOrder)
    	{
    		concatList+=" ";
    		concatList += order;
    	}
    	return concatList;
    }
    //Revisar si es gameOver para el contexto
    public boolean gameOver() {
    	return lifes <= 0;
    }

	@Override
	public void destruir() {
		instance = null;
	}
}
    
