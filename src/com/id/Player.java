package com.id;

import java.lang.Thread.State;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.KeyEvent;

class Player{
    
	public ArrayList<Bitmap> ary;
    int current;
    int experience;
    int nextlvlexp;
    int prevlvlexp;
    int health, maxHealth;
    int mana, maxMana;
    int level;
    
    int healthRecoveryRate; //two variable with mod that determine how fast the player 
    int manaRecoveryRate; //recovers
    
    int flycount = 0;
    
    public int x;
    public int y;
    public int initX;
    public int initY;
    
    Bitmap currentBitmap;
    Bitmap crouchPic;
    Bitmap jumpPic;
    Bitmap damagedPic;
    ArrayList<Bitmap> flying;
    
    boolean isCrouched = false;
    
    public States playerState;
    public States jumpState;
    public States walkState;
    
    public enum States {
    	CROUCHED, UP, DOWN, WALKING, JUMPING, NO_JUMPING, RUNNING, STILL, DOUBLE_CROUCH
    };
    
    public Player(int myx, int myy){
    	
    	playerState = States.STILL;
    	jumpState = States.NO_JUMPING;
    	x = myx;
    	y = myy;
    	initX = myx;
    	initY = myy;
    	
    	health = 100;
    	maxHealth = 100;
    	mana = 200;
    	maxMana = 200;
    	nextlvlexp = lvlUpExp(level+1);
    	prevlvlexp = lvlUpExp(level);
    	healthRecoveryRate = 200;
    	manaRecoveryRate = 100;
    	
    	level = 1;
    	experience = 0;
    	
    	current = 0;
        ary = new ArrayList<Bitmap>();
        flying = new ArrayList<Bitmap>();
        
    }
    public void update(){
    	
    	current++;
    	current %= ary.size();
    	walkState = States.WALKING;
    	isCrouched = false;
    	
    	nextlvlexp = lvlUpExp(level+1);
    	prevlvlexp = lvlUpExp(level);
        //currentBitmap = ary.get(++current % ary.size());
    	setCurrentBitmap();
    }
    public void lvlUp(){
    	level++;
    	maxMana+=25;
    	maxHealth +=15;
    }
    public void addFlyingSprites(Bitmap b){
    	flying.add(b);
    }
    
    public void takeDamage(Decarabia d){
    	health -= d.strength;
    	currentBitmap = damagedPic;
    	if (health<=0){
    		health = 0;
    		gameOver();
    	}
    }
    public int getHealth(){
    	return health;
    }
    public int getExpForNextLevel(){
    	return experience- prevlvlexp;
    }
    public int getAllExp(){
    	return experience;
    }
    public int getPrevLevelExp(){
    	return prevlvlexp;
    }
    public int getNextLevelExp(){
    	return nextlvlexp;
    }
    public void gainExp(int a){
    	experience+=a;
    }
    public void fly(){
    	currentBitmap = flying.get(flycount);
    	flycount++;
    	flycount %= flying.size();
    }
    
    public void gameOver(){
    //TODO: actually add a game over
    }
    
    public void setDamagedPic(Bitmap b)
    {
    	damagedPic = b;
    }
    
    public int lvlUpExp(int lvl){
    	return (int)(lvl*lvl*15/(lvl+Math.log(lvl))); 
    }
    
    public void resetPosition(){
    	walkState = States.STILL;
    	x= initX;
    	y= initY;
    }
    
    public void setCurrentBitmap(){
    	currentBitmap = ary.get(current);
    }
    
    public Bitmap getCurrentBitmap(){
        return currentBitmap;
    }
    
    public void setCrouchPic(Bitmap b){
    	crouchPic = b;
    }
    
    public void setJumpPic(Bitmap b){
    	jumpPic = b;
    }
    public int getMaxHealth(){
    	return maxHealth;
    }
    public int getMana(){
    	return mana;
    }
    public int getTotalMana(){
    	return maxMana;
    }
    public void crouch(){
    	
    	if (isCrouched){
    		playerState = States.DOUBLE_CROUCH;
    		resetPosition();
    		currentBitmap = crouchPic;
    	}
    	
    	else{
    	isCrouched = true;
    	currentBitmap = crouchPic;
    	playerState = States.CROUCHED;
    	}
    }
    
    public int getX(){
    	return x;
    }
    
    public int getY(){
    	return y;
    }
    
    public void uncrouch(){
    	isCrouched = false;
    	currentBitmap = ary.get(0);
    }
    
    public void setY(int myy){
    	y = myy;
    }
    
    public void setX(int myx){
    	x = myx;
    }
    public int getLevel(){
    	return level;
    }
    
    public void jump(){
    	currentBitmap = jumpPic;
		
    	playerState = States.UP;
    	jumpState = States.JUMPING;
    	
    	/*for (int i=0; i <11; i++){
    		setY(getY()+i);
    		//firstDrawView.invalidate();
    	}
    	
    	playerState = States.DOWN;
    	for (int i=0; i <11; i++){
    		setY(getY()-i);                		
    		//firstDrawView.invalidate();
    	}*/
    	
    	/*for (int i=0; i <11; i++){
    		y+= i;
    	}
    	
    	for (int i=0; i <11; i++){
    		y-= i;
    	}*/
    	
    }
    
    public void addSprite(Bitmap b){
    	
    	ary.add(b);
    	setCurrentBitmap();
    }
    
    public static void flipImage(){
    	//TODO:
    }
    
    /*
    public Boolean onKeyUp(int keyCode, KeyEvent event){
        return true;
    }
    */
}