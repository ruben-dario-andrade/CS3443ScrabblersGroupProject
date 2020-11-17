package application.engine;

public class WordThread implements Runnable {
	Thread runner;
	WordEngine wordEngine;
	int test = 0;
	
	boolean running = false;
  	public WordThread() {
  		this.wordEngine = new WordEngine();
  		running = true;
  		this.runner = new Thread(this);
	  	this.runner.start();
  	}

  	private void tick() {
  		wordEngine.addList(GameEngine.getHand());
  		if (test == 0) {		
  			wordEngine.createInclusiveList();
  			wordEngine.getCurrentList();
  			test++;
  		}
  	}
  	
  	@Override
  	public void run() {
  		wordEngine.createInclusiveList();
  		long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
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
  	
  	public static void main(String[] args) {
  		new WordThread();
  	}
}