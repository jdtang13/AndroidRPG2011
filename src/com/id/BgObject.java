package com.id;

import android.graphics.Bitmap;

public class BgObject {
	
	Bitmap image;
	int selfX;
	int selfY;
	int width;
	int height;
	int delta;
	
	int initialX;
	int initialY;
	
	public BgObject(Bitmap b, int x, int y, int d){
		image = b;
		selfX = x;
		selfY = y;
		
		initialX = x;
		initialY = y;
		
		width = b.getWidth();
		height = b.getHeight();
		delta = d; // DELTA is a variable to control perspective and how fast it moves on camera
	}
	
	public BgObject(Bitmap b, int d){
		image = b;
		width = b.getWidth();
		height = b.getHeight();
		
		delta = d; // DELTA is a variable to control perspective and how fast it moves on camera
	}
	
	public Bitmap getIcon(){
		return image;
	}
	
	public int getW(){
		return width;
	}
	
	public int getH(){
		return height;
	}
	
	public int getX(){
		return selfX;
	}
	
	public int getY(){
		return selfY;
	}
	
	public int getD(){
		return delta;
	}
	
	public void setX(int x){
		selfX = x;
	}
	
	public void setY(int y){
		selfY = y;
	}
	
	public void setCoords(int x, int y){
		selfX=x;
		selfY=y;
	}

}
