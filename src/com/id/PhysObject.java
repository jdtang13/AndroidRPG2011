package com.id;


public class PhysObject {
	
	public int selfX;
	public int selfY;
	
	int width;
	int height;
	int delta;
	
	int initialX;
	int initialY;
	
	public PhysObject(){
		
	}
	public PhysObject(int x, int y, int w, int h, int d){
		selfX = x;
		selfY = y;
		
		initialX = x;
		initialY = y;
		
		width = w;
		height = h;
		delta = d; // DELTA is a variable to control velocity
	}

	public void setCoords(int myX, int myY){
		selfX = myX;
		selfY = myY;
	}
	
	
    public boolean contains(int x, int y){
    	
    	if ( x - selfX < width && x - selfX > 0){
    		return true;
    	}
    	if ( y - selfY < height && x - selfY > 0){
    		return true;
    	}
    	
    	return false;
    }
	
	public int getCoord(char i){
		if (i == 'x' || i == 'X'){
			return selfX;
		}
		else if (i == 'y' || i == 'Y'){
			return selfY;
		}
		return 0;
	}

}
