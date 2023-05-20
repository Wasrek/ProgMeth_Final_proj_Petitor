package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Testing extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create an ImageView and load the image
    	ImageView imageView = new ImageView();
    	String imagePath = "../img/Duck.png"; // Replace with the actual relative path to your image
    	Image image = new Image(getClass().getResourceAsStream(imagePath));
    	imageView.setImage(image);
        // Create a StackPane and add the ImageView
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(imageView);

        // Create a Scene with the StackPane
        Scene scene = new Scene(stackPane, 800, 600);

        // Set the Scene to the Stage
        primaryStage.setTitle("Simple Image FX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
