package application.algo;

import java.util.LinkedList;

import application.controller.WordHelperController;
import application.engine.GameEngine;

public class WordThread implements Runnable {
	Thread runner;
	WordEngine wordEngine;
	WordEngineTemp wordEngineTemp;
	
	int test = 0;
	LinkedList<String> reccomendations;
	
	boolean running = false;
  	
	public WordThread() {
		this.reccomendations = new LinkedList<String>();
		this.wordEngineTemp = new WordEngineTemp();
		this.wordEngine = new WordEngine();
		wordEngine.combine();
  		wordEngine.main();
		
  		running = true;
  		this.runner = new Thread(this);
	  	this.runner.start();
  	}

  	
  	private void tick() {
  		//if (test == 0) {	 	
  			wordEngineTemp.addList(GameEngine.getHand());
  			wordEngineTemp.createInclusiveList();
  			wordEngineTemp.removeLettersNotOnBoardOrHand(GameEngine.getHand(), GameEngine.getBoard());
  			this.reccomendations = wordEngineTemp.getCurrentList();
  		//	test++;
  		//}
  	}
  	
  	@Override
  	public void run() {
  		long lastTime = System.nanoTime();
        double amountOfTicks = 0.3;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while( running ){
            long now = System.nanoTime();
            delta += ( now - lastTime ) / ns;
            lastTime = now;
            while ( delta >= 1) {
                tick();
                delta--;
            }
            //if ( running )
                
            frames++;
            if( System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println( "FPS: " + frames);
                frames = 0;
            }
        }
        stop();
  	}
  	
  	 public synchronized void stop(){
         try{
             runner.join();
             running = false;
         }catch ( Exception e){
             e.printStackTrace();
         }
     }
  	
  	public void end() {
  		running = false;
  	} 
  	 
  	public LinkedList<String> getReccomendations() {
  		return this.reccomendations;
  	}
  	
  	public static void main(String[] args) {
  		//new WordThread();
  	}
}