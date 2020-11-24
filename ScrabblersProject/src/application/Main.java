package application;
	
import application.engine.WordThread;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private static Stage mainStage;

	@Override
	public void start(Stage primaryStage) {
		try {
			
			mainStage = primaryStage;
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("view/Main.fxml"));
			Scene scene = new Scene(root,1200,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			mainStage.setScene(scene);
			mainStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage getStage() {
		return mainStage;
	}
	
	@Override
	public void stop() throws Exception {
		//System.out.println("stopped");
		super.stop();
		System.exit(0);
	}
}
