package application.engine;

import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;

public abstract class PieceEvent extends MouseEvent {

    public static final EventType<PieceEvent> CUSTOM_EVENT_TYPE = new EventType(MOUSE_CLICKED);
 
	private static double sceneX;
	private static MouseButton button;
	private static int clickCount;
	private static boolean altDown;
	private static PickResult pickResult;

    public PieceEvent(EventType<? extends MouseEvent> eventType) {
        super(eventType, sceneX, sceneX, sceneX, sceneX, button, clickCount, altDown, altDown, altDown, altDown, altDown, altDown, altDown, altDown, altDown, altDown, pickResult);
    }

    public abstract void invokeHandler(PieceEventHandler pieceEventHandler);

}