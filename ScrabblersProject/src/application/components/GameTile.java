package application.components;

import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Paint;

public class GameTile extends TilePane{
	public GameTile() { 
		Border border = new Border(new BorderStroke(null,
				BorderStrokeStyle.SOLID,
				new CornerRadii(0),
				new BorderWidths(1)
		));
		this.setBorder(border);
		this.setMaxSize(40, 40);
		this.setPrefSize(40, 40);
		this.setMinSize(40, 40);
	}

}
