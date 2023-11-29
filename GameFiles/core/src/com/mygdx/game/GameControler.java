package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

public class GameControler implements GameObject {
    private long score;
    private int lifes;
    private float speedDiff;
    private boolean statusChange;
    private float statusChangeTemp; //temporizador de cambio de estado
    private Player cheesar;
    private Cheesar animation;
    private ArrayList<String> pizzaOrder;
    private ArrayList<Ingredients> ingredientsList; // Lista de ingredientes
    private ArrayList<Colectible> powerUps; // Lista de PowerUps
    
    public GameControler() {
        cheesar = new Player(115);//Modifica la altura de la hitbox
        animation = new Cheesar(0, 0);//Modifica la altura de la animación
        score = 0;
        lifes = 3;
        speedDiff = 1;
        statusChange = false;
        pizzaOrder = new ArrayList<String>();
        ingredientsList = new ArrayList<Ingredients>();
        powerUps = new ArrayList<Colectible>();
        
        // Agregar dos ingredientes a la lista
        addIngredient(7);
        generateOrder(2);
        new ShapeRenderer();
    }

    @Override
    public void update(float delta) {
    	int cont=2;
        cheesar.update(delta);
        animation.update(delta); //EXCLUSIVO ANIMACIÓN
        //checkear si se termino la lista de ordenes
        if (pizzaOrder.size() == 0)
        {
        	if (score % 6 == 0) {
        		cont++;
        		generateOrder(cont);
        	} else {
        		generateOrder(cont);
        	}
        	speedDiff+=0.2;
        	score++;
        	difficultyAdjust(speedDiff);
        	//Ver si agregar mas ingredientes
            if (score % 3 == 0)
            {
            	addIngredient(1);
            }
            //elegir si añadir power Up
            Random random = new Random();
            if (0 == random.nextInt(10)) //probabilidad de 1/10
            	addColectible(1);
            	
            addColectible(1);
        }
        
        for (Ingredients ingredient : ingredientsList) {
            ingredient.update(delta);
        }
        
        for (Colectible colectible : powerUps) {
            colectible.update(delta);
        }
        
        Ingredients auxIng = detectCollisionsIng();  //get Ingredient that colided.
        if (auxIng != null)
        {
        	
        	String type = auxIng.getType();
        	checkCorrectOrder(type);
        	auxIng.reset();
        }
        Colectible auxCol = detectCollisionsCol();
        if (auxCol != null)
        {
        	String type = auxCol.getType();
        	activateColectible(type);
        	auxCol.reset();
        }
        //Check change status
        if (statusChangeTemp > 0)
        {
        	statusChangeTemp-=delta;
        }
        if (statusChangeTemp == 0 & statusChange )
        {
        	//statusChange es booleano, pero mas adelante podria tener id
        	//para varios tipos de camio de estado
        	cheesar.modificarVeloc(1); //reinicia veloc
        	animation.modificarVeloc(1);
        }

    }

    

	private void activateColectible(String type) {
		if (type.equals("Speed"))
		{
			cheesar.modificarVeloc(2); //Aumenta el doble
			animation.modificarVeloc(2);
			statusChangeTemp = 5.0f;
		}
		
	}

	private void addColectible(int cant) {
		System.out.println("asdasd");
		for (int i = 0; i < cant; i++)
    	{
			powerUps.add(new Colectible(0));
    	}
	}

	@Override
	 public void render(SpriteBatch batch) {
	    //Renderizar al jugador
	    cheesar.render(batch);
	    //Renderizar animación
	    animation.render(batch);
	    // Renderizar los ingredientes
	    for (Ingredients ingredient : ingredientsList) {
	        ingredient.render(batch);
	    }
	    // Renderizar power-ups
	    for (Colectible colectible : powerUps) {
	        colectible.render(batch);
	    }
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
        String[] availableIngredients = {"Salame", "Pina", "Champinon", "Pimenton"};
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
    private Colectible detectCollisionsCol() {
        Rectangle playerBounds = cheesar.getBounds();

        for (Colectible colectible : powerUps) {
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
    		concatList+=" ";
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
    
