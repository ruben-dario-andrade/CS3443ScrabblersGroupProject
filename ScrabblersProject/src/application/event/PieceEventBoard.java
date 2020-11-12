package application.event;

import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

public class PieceEventBoard extends PieceEvent {

	private int row;
	private int col;
	private boolean inTray;
	
	public static final EventType<PieceEvent> PIECE_EVENT_TYPE_2 = new EventType(CUSTOM_EVENT_TYPE, "PieceEventBoard");
	
	public PieceEventBoard(int row, int col, boolean inTray) {
		super(PIECE_EVENT_TYPE_2);
		this.row = row;
		this.col = col;
		this.inTray = inTray;
	}

	@Override
	public void invokeHandler(PieceEventHandler handler) {
		handler.onEvent2(row, col, inTray);
	}

	
}
