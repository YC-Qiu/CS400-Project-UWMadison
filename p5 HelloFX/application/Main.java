//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Course:          COMP SCI 400 SPRING 2020
// Author:          Yucheng Qiu
// Email:           yqiu56@wisc.edu
// Lecture Number:  001
// Description:     My Main class to display a window, based on JavaFX
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
package application;

import java.io.FileInputStream;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JDK 11, Eclipse 2019-06 https://gluonhq.com/products/javafx/
 * https://openjfx.io/openjfx-docs/
 *
 * @author YC Qiu
 */
public class Main extends Application {
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;

	private static final int WINDOW_WIDTH = 300;
	private static final int WINDOW_HEIGHT = 200;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// save args example
		args = this.getParameters().getRaw();

		// Main layout is Border Pane example (top,left,center,right,bottom)
		BorderPane root = new BorderPane();

		// Add the vertical box to the center of the root pane
		root.setTop(new Label("CS400 My First JavaFX Program"));
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		// Add a new combo box
		ComboBox cBox = new ComboBox();
		cBox.getItems().add("A");
		cBox.getItems().add("B");
		cBox.getItems().add("C");
		root.setLeft(cBox);
		// Add an image
		FileInputStream input = new FileInputStream("face.jpg");
		Image image = new Image(input);
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(124);
		imageView.setFitWidth(100);
		root.setCenter(imageView);
		// Add a button
		Button button = new Button("Done");
		root.setBottom(button);
		// Add a label 
		root.setRight(new Label("Thank you!"));
		// Add the stuff and set the primary stage
		
		//primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
