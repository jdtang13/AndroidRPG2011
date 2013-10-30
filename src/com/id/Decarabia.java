package com.id;

import java.util.ArrayList;
import java.util.Collections;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

class Decarabia extends Monster{
	
	Player target;
	
	int x;
	int y;
	int delta;
	int health;
	int width;
	int height;
	public int strength;
	
	String spinDirection;
	
	int exp, currentIndex;
	ArrayList<Bitmap> ary;
	Bitmap currentBitmap;
	final int PIC_HEIGHT= 120, PIC_WIDTH = 120;
	
	public Decarabia(Player p, int myx, int myy, int d){
		super(p, myx, myy);
		target = p;
		exp = 5;
		ary = new ArrayList<Bitmap>();
		x=myx;
		y=myy;
		delta = d;
		
		health = 100;
		strength = 4;
	}
	
	public int getD(){
		return delta;
	}
	public int expGive(){
		return exp/target.getLevel();
	}
	public int getX(){
		return x;
	}
	
	public Bitmap getBitmap(){
		return currentBitmap;
	}
	//Update and homes in;
	public void update(){
		if (Math.abs(target.getX()-x)<5){
			spin();
		}
		else if(target.getX()-x<0)
			{
			spin("left");
			moveLeft(4);
			}
		else {
			spin("right");
			moveRight(4);
		}
		
		currentBitmap = ary.get(currentIndex);
	}
	
	public boolean contains(int myx, int myy){
		
		if (myx - getX() > 0 && myx - getX() <= width){
			return true;
		}
		else if (myy - getY() > 0 && myy - getY() <= height){
			return true;
		}
		
		return false;
		
	}
	
	public void takeDamage(Particle p){
		health -= p.damage;
	}
	
	public void moveLeft(){
		setX(getX()-getD());
	}
	
	public void moveLeft(int d){
		setX(getX()-getD()/d);
	}
	
	public void moveRight(){
		setX(getX()+getD());
	}
	
	public void moveRight(int d){
		setX(getX()+getD()/d);
	}
	
	public void flyUp(){
		setY(getY()-getD());
	}
	
	public void flyDown(){
		setY(getY()+getD());
	}
	
	public void setX(int myx){
		x = myx;
	}
	
	public void setY(int myy){
		y = myy;
	}

	/*public void moveLeft(){
		currentIndex--;
		currentIndex%=ary.size();
		
		update();
	}
	public void moveRight(){
		currentIndex++;
		currentIndex%=ary.size();
		
		update();
	}*/
	public void spin(){
		spin(spinDirection);
	}
	public void spin(String s){
		if(s.equals("left")){
			currentIndex--;
			currentIndex+= ary.size();
			spinDirection = "left";
		}
		else{
			currentIndex++;
			spinDirection = "";
		}
		
		currentIndex %= ary.size();
	}
	
	/*public void addImages(ArrayList<Bitmap> temp){
		ary=temp;
		if (ary.size()>0){
		currentBitmap = ary.get(0);
		}
	}*/
	
	public void addSprite(Bitmap b){
		ary.add(b);
		width = b.getWidth();
		height = b.getHeight();
	}
		
}
