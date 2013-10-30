package com.id;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

public class StartActivityOld2 extends Activity {
	private GameView firstDrawView;
	int sleepTime = 50; 
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Set full screen view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                         WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);	
        
        RelativeLayout mainLayout = new RelativeLayout(this);
        mainLayout.setLayoutParams(new RelativeLayout.LayoutParams
(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        
        
        ImageButton leftButton = new ImageButton(this);
        ImageButton rightButton = new ImageButton(this);
        ImageButton upButton = new ImageButton(this);
        ImageButton downButton = new ImageButton(this);
        
        /*leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                
                 //Toast.makeText(getApplicationContext(),"Left",Toast.LENGTH_SHORT).show();
                 for ( BgObject b : firstDrawView.backgrounds){
                	 b.setX(b.getX()+b.getD());
                 }
                 
                 for ( Monster b : firstDrawView.decarabias){
              	 b.setX(b.getX()+b.getD());
               }
               
                 
                 firstDrawView.player.update();
                 
                 firstDrawView.progress--;

            }
        });	*/
        
        leftButton.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
                for ( BgObject b : firstDrawView.backgrounds){
               	 b.setX(b.getX()+b.getD());
                }
                
                for ( Decarabia b : firstDrawView.decarabias){
                 	 b.moveRight();
                  }
                
                firstDrawView.player.update();
                firstDrawView.progress--;

				return true;
			}
		});
        
        /*rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                
                 //Toast.makeText(getApplicationContext(),"Right",Toast.LENGTH_SHORT).show();

                 for ( BgObject b : firstDrawView.backgrounds){
                	 b.setX(b.getX()-b.getD());
                 }
                 
                 firstDrawView.player.update();
                 
                 firstDrawView.progress++;

            }
        });		*/
        
        rightButton.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
                for ( BgObject b : firstDrawView.backgrounds){
               	 b.setX(b.getX()-b.getD());
                }
                
                for ( Decarabia b : firstDrawView.decarabias){
                  	 b.moveLeft();
                   }
				
                firstDrawView.player.update();
                firstDrawView.progress++;

				return true;
			}
		});
        
        
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                
                 //Toast.makeText(getApplicationContext(),"Up",Toast.LENGTH_SHORT).show();
                
                /*for ( BgObject b : firstDrawView.backgrounds){
               	 b.setY(b.getY()+b.getD());
                }*/
            	
            	if (firstDrawView.player.isCrouched){
            		firstDrawView.player.uncrouch();
            	}
            	else {
            		firstDrawView.player.jump();
            	}
            	
            }
        });	
        /*
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                
                 //Toast.makeText(getApplicationContext(),"Down",Toast.LENGTH_SHORT).show();
                
                
                //for ( BgObject b : firstDrawView.backgrounds){
                //  	 b.setY(b.getY()-b.getD());
                //   }
                
            	
            	firstDrawView.player.crouch();
            	
            }
        });	
        */
        
        downButton.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
            	firstDrawView.player.crouch();

				return true;
			}
		});
        
        
        
        Bitmap buttonLeft = BitmapFactory.decodeResource(getResources(), R.drawable.button_left);
        Bitmap buttonRight = BitmapFactory.decodeResource(getResources(), R.drawable.button_right);
        Bitmap buttonUp = BitmapFactory.decodeResource(getResources(), R.drawable.button_up);
        Bitmap buttonDown = BitmapFactory.decodeResource(getResources(), R.drawable.button_down);
        
        
        leftButton.setImageBitmap(buttonLeft);
        rightButton.setImageBitmap(buttonRight);
        upButton.setImageBitmap(buttonUp);
        downButton.setImageBitmap(buttonDown);
        
        //TODO ADD ME BACK
        //firstDrawView = new GameView(this);
       // mainLayout.addView(firstDrawView);
        
        
        mainLayout.addView(leftButton);  
        mainLayout.addView(rightButton);  
        mainLayout.addView(upButton);  
        mainLayout.addView(downButton);  
        
        //firstDrawView.requestFocus();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        leftButton.setLayoutParams(params);

       RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(100, LayoutParams.WRAP_CONTENT);
       params2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
       params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
       rightButton.setLayoutParams(params2);
       
       RelativeLayout.LayoutParams params3 = new RelativeLayout.LayoutParams(100, LayoutParams.WRAP_CONTENT);
  //     params3.addRule(RelativeLayout.ALIGN_TOP, leftButton.getId());
       params3.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
       upButton.setLayoutParams(params3);

      RelativeLayout.LayoutParams params4 = new RelativeLayout.LayoutParams(100, LayoutParams.WRAP_CONTENT);
  //    params4.addRule(RelativeLayout.ALIGN_TOP, rightButton.getId());
      params4.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
      downButton.setLayoutParams(params4);

       leftButton.bringToFront();
       rightButton.bringToFront();
       upButton.bringToFront();
       downButton.bringToFront();
       
       this.setContentView(mainLayout);    

        updateUI();
    }
    
    private RefreshHandler mRedrawHandler = new RefreshHandler();   
    
    class RefreshHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

   	    firstDrawView.update(); 
   	    updateUI();
   	    
        }
        public void sleep(long delayMillis) {
                this.removeMessages(0);
                sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    };
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.new_game:    
            newGame();
            return true;
        case R.id.easy:
            easyGame();
            return true;
        case R.id.hard:
            hardGame();
            return true;
        case R.id.beast:
        	beastGame();
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    public void updateUI(){
    	
	     firstDrawView.invalidate();
//	     updateUI();
  	     mRedrawHandler.sleep(sleepTime);	    	
    }
    
    
    public void newGame()
    {
     //whatever your code is to start a new game
     Toast.makeText(StartActivityOld2.this, "New Game", Toast.LENGTH_SHORT).show();
    }

    
    public void easyGame()
    {
     Toast.makeText(StartActivityOld2.this, "Easy Mode", Toast.LENGTH_SHORT).show();
     sleepTime=2000;
    }

    public void hardGame()
    {
     Toast.makeText(StartActivityOld2.this, "Hard Mode", Toast.LENGTH_SHORT).show();
     sleepTime=600;
    }
    
    public void beastGame()
    {
     Toast.makeText(StartActivityOld2.this, "Beast Mode", Toast.LENGTH_SHORT).show();
     sleepTime=200;
    }
    
    
}