package com.id;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class StatusBarsView extends View{
	int health,exp, mana, expBetweenLvls,totalMana,maxHealth;
	int WIDTH = 0,HEIGHT = 0;
	int xCo,yCo;
	Player player;
	
	public StatusBarsView(Context context, AttributeSet attrs){
		super(context,attrs);
		setFocusable(true);
		xCo = this.getLeft();
		yCo = this.getTop();
		
		health=0;exp=0;mana=0;expBetweenLvls=0;totalMana=0;maxHealth=0;
		
	}
	public void setPlayer(Player p){
		player = p;
	}
	public void onDraw(Canvas canvas){
		updateStats();
		
		int maxLength = WIDTH*2/3;
		int maxHeight = HEIGHT/5;
		
		int xStart = WIDTH/6;
		int yStart = HEIGHT/10;
		
		Paint p = new Paint();
		//Draw health bar
		p.setColor(Color.RED);
		canvas.drawRect(xStart, yStart, maxLength*health/maxHealth, maxHeight, p);
		//Draw mana bar
		yStart+= HEIGHT*3/10;
		p.setColor(Color.BLUE);
		canvas.drawRect(xStart, yStart, maxLength*mana/totalMana, maxHeight, p);
		//Draw exp bar;
		yStart+= HEIGHT*3/10;
		p.setColor(Color.GREEN);
		canvas.drawRect(xStart, yStart, maxLength*exp/expBetweenLvls, maxHeight, p);
	}
	public void updateStats(){
		maxHealth = player.getMaxHealth();
		health = player.getHealth();
		exp = player.getExpForNextLevel();
		expBetweenLvls = player.getNextLevelExp()-player.getPrevLevelExp();
		mana = player.getMana();
		totalMana = player.getTotalMana();
	}
	
	public void onSizeChanged(int w, int h, int oldW, int oldH) {
	       WIDTH = w-1;
	       HEIGHT = h-1;
	    }
}
