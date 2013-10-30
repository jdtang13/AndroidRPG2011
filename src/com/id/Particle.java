package com.id;

import java.util.Random;

import android.graphics.Color;
import android.graphics.Paint;

public class Particle{
	
    int massRange = 150; //Range in which random numbers can be generated
    int massMinimum = 20;
    Random rng = new Random();
    int mass;
	Decarabia target;
	
    int radius;
    int velocityRange = 6;
    int velocityX;
    int velocityY;
    
    int damage;
	
    int selfX = 0;
    int selfY = 0;
    int manaCost = 0;
    
    final int COLOR_RANGE = 256;
    
    Paint color = new Paint();
    
    int beforeState = 0;
    int beforeX = 0; // Previous coords to handle "fast" collisions
    int beforeY = 0;
    
  /*  public Particle(int inX, int inY){
    	
        Random rng = new Random();
        mass = (rng.nextInt(massRange) + massMinimum);
        radius = (mass/4);
        
        velocityX = (rng.nextInt(velocityRange) - velocityRange/2);
        velocityY = (rng.nextInt(velocityRange) - velocityRange/2);
        
        color.setARGB(rng.nextInt(COLOR_RANGE-100)+100, rng.nextInt(COLOR_RANGE), rng.nextInt(COLOR_RANGE),
        		rng.nextInt(COLOR_RANGE)); //randomize
        
        while (color.getColor() == Color.BLACK){
            color.setARGB(rng.nextInt(COLOR_RANGE-100)+100, rng.nextInt(COLOR_RANGE), rng.nextInt(COLOR_RANGE),
            		rng.nextInt(COLOR_RANGE)); //reroll
        }
        
        color.setAntiAlias(true); 
        selfX=inX;
        selfY=inY;
        
    }*/
    
    public Particle(Decarabia trgt){
    	
        Random rng = new Random();
        mass = (rng.nextInt(massRange) + massMinimum);
        radius = (mass/4);
        damage = mass/20;
        manaCost = damage/2;
        
        velocityX = 0;
        velocityY = 0;
        
        setColor();
        
        target = trgt;
       
        
        color.setAntiAlias(true); 

    }
    
    public void setColor(){
        color.setARGB(rng.nextInt(COLOR_RANGE-20)+20, rng.nextInt(COLOR_RANGE-100)+100, rng.nextInt(COLOR_RANGE-100)+100,
        		rng.nextInt(COLOR_RANGE)); //randomize
        
        while (color.getColor() == Color.BLACK){
            color.setARGB(rng.nextInt(COLOR_RANGE-20)+20, rng.nextInt(COLOR_RANGE-100)+100, rng.nextInt(COLOR_RANGE-100)+100,
            		rng.nextInt(COLOR_RANGE)); //reroll
        }
    }
    
    public int getDamage(){
    	return damage;
    }
    
    public void setColor(int a, int r, int g, int b){
        color.setARGB(a, r, g,
        		b); //randomize
    }
    
    public void setColor(int a){
        color.setARGB(a, rng.nextInt(COLOR_RANGE), rng.nextInt(COLOR_RANGE),
        		rng.nextInt(COLOR_RANGE)); //randomize
    }
    
    public void reverseVelocity(){
    	velocityX *= -1;
    	velocityY *= -1;
    }
    
    public int getRadius(){
    	return radius;
    }
    
    public void setCoords(int myx, int myy){
    	selfX = myx;
    	selfY = myy;
    }
    
    public void update(){
    	velocityX += (target.getX()-selfX)/20;
    	velocityY += (target.getY()-selfY)/20;
    	
    	setCoords(selfX+velocityX,selfY+velocityY);
    }
    
    public void setBeforeCoords(int myx, int myy){
    	beforeX = myx;
    	beforeY = myy;
    }
    
    public int getX(){
    	return selfX;
    }
    
    public boolean contains(int x, int y){
    	
    	if ( Math.pow(selfX-x,2)+ Math.pow(selfY-y,2) <= Math.pow(radius,2) ){
    		return true;
    	}
    	
    	return false;
    }
    
    public int getY(){
    	return selfY;
    }
    
}
   