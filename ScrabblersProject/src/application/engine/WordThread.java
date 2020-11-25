package application.engine;

import java.util.LinkedList;

import application.controller.WordHelperController;

public class WordThread implements Runnable {
	Thread runner;
	WordEngine wordEngine;
	int test = 0;
	WordHelperController controller;
	
	boolean running = false;
  	
	public WordThread(WordHelperController controller) {
  		this.wordEngine = new WordEngine();
  		this.controller = controller;
  		running = true;
  		this.runner = new Thread(this);
	  	this.runner.start();
  	}

  	
  	private void tick() {
  		if (test == 0) {	 	
  			wordEngine.addList(GameEngine.getHand());
  			wordEngine.createInclusiveList();
  			LinkedList<String> reccomendations = wordEngine.getCurrentList();
  			this.controller.populateLLThread(reccomendations);
  			test++;
  			//Try to update list from here
  		}
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
  	 
  	public static void main(String[] args) {
  		//new WordThread();
  	}
}