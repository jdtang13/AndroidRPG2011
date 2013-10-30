package com.id;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GameViewOld extends View implements OnTouchListener{

	public int WIDTH=0;
	public int HEIGHT=0;
	public final int TOLERANCE = 20; //feather radius for the blah blhah background

	public int myI=11;
	
    Paint myPaint;
    Paint black;
    
    Random rand = new Random();
    BgObject Clouds;
    BgObject Hills;
    BgObject Sun;
    
    Player player;
    
    int progress; //use this to track how far the character has walked
    
    ArrayList<BgObject> backgrounds;
    ArrayList<Decarabia> decarabias;
    
    Decarabia m;
    
    Bitmap b;

	public GameViewOld(Context context) {
        super(context);
        setFocusable(true);
    	
	    this.setOnTouchListener(this);
	    
//		Context context = this.getApplicationContext();
	    myPaint = new Paint();
		myPaint.setAntiAlias(true);
    	
	    myPaint.setColor(Color.argb(255, 0, 100, 200));
	    
	    black = new Paint();
	    black.setAntiAlias(true);
	    black.setColor(Color.BLACK);
	    
	    player = new Player(30, 400);
	    
	    backgrounds = new ArrayList<BgObject>();
	    decarabias = new ArrayList<Decarabia>();
		
		Clouds = new BgObject(BitmapFactory.decodeResource(getResources(), R.drawable.bg_clouds),
				0,20,5);
		
		Hills = new BgObject(BitmapFactory.decodeResource(getResources(), R.drawable.bg_hill),
				30);
		Hills.setCoords(-2000,HEIGHT+175); 
		
		Sun = new BgObject(BitmapFactory.decodeResource(getResources(), R.drawable.bg_sun),
				0);
		Sun.setCoords(140, 30);

		backgrounds.add(Sun); 
		backgrounds.add(Clouds); 
		backgrounds.add(Hills); 
		
        player.addSprite(BitmapFactory.decodeResource(getResources(), R.drawable.player_1));
        player.addSprite(BitmapFactory.decodeResource(getResources(), R.drawable.player_2));
        player.addSprite(BitmapFactory.decodeResource(getResources(), R.drawable.player_3));
        player.setCrouchPic(BitmapFactory.decodeResource(getResources(), R.drawable.player_c));
        player.setJumpPic(BitmapFactory.decodeResource(getResources(), R.drawable.player_j1));
        
        m = new Decarabia(player, 100, 350, Hills.getD());

        m.addSprite(BitmapFactory.decodeResource(getResources(),
				R.drawable.decarabia));
        m.addSprite(BitmapFactory.decodeResource(getResources(),
				R.drawable.decarabia2));
        m.addSprite(BitmapFactory.decodeResource(getResources(),
				R.drawable.decarabia3));
        m.addSprite(BitmapFactory.decodeResource(getResources(),
				R.drawable.decarabia4));
        
        decarabias.add(m);
        
        for (Decarabia d: decarabias){
        	d.update();
        }
        
    }

    @Override
    public void onDraw(Canvas canvas) {     

    canvas.drawColor(myPaint.getColor());
    
    /*canvas.drawBitmap(Sun.getIcon(), Sun.getX(), 
    		Sun.getY(), myPaint); //layers
    canvas.drawBitmap(Clouds.getIcon(), Clouds.getX(), 
    		Clouds.getY(), myPaint); //layers
    canvas.drawBitmap(Hills.getIcon(), Hills.getX(), 
    		Hills.getY(), myPaint); //layers*/
    
    for (BgObject b : backgrounds){
    	canvas.drawBitmap(b.getIcon(), b.getX(), 
    	    		b.getY(), myPaint); //layers
    	
    	if (Math.abs(b.getX() - (-b.getW()/4))<=TOLERANCE || Math.abs(b.getX() - 3*(-b.getW()/4))<= TOLERANCE ){
    		b.setX(b.initialX);
    	}
    	
    }

    
    if (player.jumpState == Player.States.JUMPING && player.playerState == Player.States.UP){
		player.setY(player.getY()-myI);
		//firstDrawView.invalidate();
		myI--;
		
	    if (myI == 0){
	    	player.playerState = Player.States.DOWN;
	        myI = 11;
	    }
	}
    
    if (player.jumpState == Player.States.JUMPING && player.playerState == Player.States.DOWN){
		player.setY(player.getY()+myI);
		//firstDrawView.invalidate();
		myI--;
		
	    if (myI == 0){
	    	player.playerState = Player.States.STILL;
	    	player.jumpState = Player.States.NO_JUMPING;
	    	player.setCurrentBitmap();
	    	player.resetPosition();
	    	myI=11;
	    }
	}
    
	canvas.drawBitmap(player.getCurrentBitmap(), player.getX(), 
    		player.getY(), myPaint); //player
	
	canvas.drawBitmap(m.getBitmap(), m.getX(), 
			m.getY(), myPaint); //player
	m.spin("left");
    
    canvas.drawText("Progress: " + progress, WIDTH/2 - 10, HEIGHT-20, black);
   
    }
    
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
       WIDTH = w-1;
       HEIGHT = h-1;
    }

   public void update(){
       //player.checkJump();
	   invalidate();
   }
   
   public boolean onTouch(View view, MotionEvent event) {
	   
	   return true;
   }
	
}
