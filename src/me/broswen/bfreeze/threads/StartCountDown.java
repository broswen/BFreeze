package me.broswen.bfreeze.threads;

import me.broswen.bfreeze.BFreeze;
import me.broswen.bfreeze.utils.GameManager;

public class StartCountDown implements Runnable{

	private static int timeUntilEnd;

	@Override
	public void run() {
		timeUntilEnd = BFreeze.config.getInt("time-until-win");
		
		while(true){
			for(; timeUntilEnd >= 0; timeUntilEnd--){
				
				if(timeUntilEnd == 0){
					GameManager.endGame();
				}
				
				if(timeUntilEnd == 60 || timeUntilEnd == 300){
					
				}
			}
		}
		
	}
}
