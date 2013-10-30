package com.id;

//New StartActivity with d-pad layout by Erik

import com.id.StartActivity.RefreshHandler;

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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends Activity{
	
	private GameView firstDrawView;
	private StatusBarsView statusBars;
	int sleepTime = 50; 
	int timeCount;
	int timeCount2;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playscreen);
		
		// Set full screen view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                         WindowManager.LayoutParams.FLAG_FULLSCREEN);
      //  requestWindowFeature(Window.FEATURE_NO_TITLE);	
        
        firstDrawView = (GameView) findViewById(R.id.viewGame);
        //Status bars....need to figure this out.
        statusBars = (StatusBarsView) findViewById(R.id.viewStatusBars);
        statusBars.setPlayer(firstDrawView.getPlayer());
        statusBars.updateStats();
        //Controller Buttons
        ImageButton leftButton = (ImageButton) findViewById(R.id.leftButton);
        ImageButton rightButton = (ImageButton) findViewById(R.id.rightButton);
        ImageButton upButton = (ImageButton) findViewById(R.id.upButton);
        ImageButton downButton = (ImageButton) findViewById(R.id.downButton);
        
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

        
        //level indicator
        TextView level = (TextView) findViewById(R.id.textViewLevel);
        level.setText("Lvl: "+firstDrawView.getLevel());
        
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
	     statusBars.invalidate();
//	     updateUI();
	     
	     timeCount++;
	     timeCount%=firstDrawView.player.healthRecoveryRate;
	     
	     timeCount2++;
	     timeCount2%=firstDrawView.player.manaRecoveryRate;
	     
	     
	     if (timeCount == 0){
	     firstDrawView.player.health++;
	     }
	     
	     if (timeCount2==0){
	     firstDrawView.player.mana++;
	     }
	     
  	     mRedrawHandler.sleep(sleepTime);	    	
    }
    
    
    public void newGame()
    {
     //whatever your code is to start a new game
     Toast.makeText(StartActivity.this, "New Game", Toast.LENGTH_SHORT).show();
    }

    
    public void easyGame()
    {
     Toast.makeText(StartActivity.this, "Easy Mode", Toast.LENGTH_SHORT).show();
     sleepTime=2000;
    }

    public void hardGame()
    {
     Toast.makeText(StartActivity.this, "Hard Mode", Toast.LENGTH_SHORT).show();
     sleepTime=600;
    }
    
    public void beastGame()
    {
     Toast.makeText(StartActivity.this, "Beast Mode", Toast.LENGTH_SHORT).show();
     sleepTime=200;
    }
    
    
}
