package application.engine;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;


public abstract class PieceEventHandler implements EventHandler<PieceEvent> {

    @Override
    public void handle(PieceEvent event) {
        event.invokeHandler(this);
    }

	public void onEvent1(char c, boolean inTray) {
		// TODO Auto-generated method stub	
	}

	
	public void onEvent2(int row, int col, boolean inTray) {
		// TODO Auto-generated method stub
		
	}
}

