package application.event;

import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

public class PieceEventTray extends PieceEvent {

	private char letter;
	private boolean inTray;
	
	public static final EventType<PieceEvent> PIECE_EVENT_TYPE_1 = new EventType(CUSTOM_EVENT_TYPE, "PieceEventTray");
	
	public PieceEventTray(char letter, boolean inTray) {
		super(PIECE_EVENT_TYPE_1);
		this.letter = letter;
		this.inTray = inTray;
	}

	@Override
	public void invokeHandler(PieceEventHandler handler) {
		handler.onEvent1(letter, inTray);
	}

	
}
