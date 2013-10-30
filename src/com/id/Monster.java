package com.id;

import android.graphics.Bitmap;

class Monster {

	int delta;
	int x;
	int y;
	
	Bitmap currentBitmap;

	Player target;
	int exp;
	public Monster(Player p, int myx, int myy){
		target = p;
		x = myx;
		y = myy;
		
	}
	public int getD(){
		return delta;
	}
	
	public void setBitmap(Bitmap b){
		currentBitmap = b;
	}

	public int getX(){
		return x;
	}
	
	public Bitmap getBitmap(){
		return currentBitmap;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int myx){
		x = myx;
	}
	
	public void setY(int myy){
		y = myy;
	}
	
}

