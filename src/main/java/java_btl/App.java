package java_btl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private double screenHeight, screenWidth;

    @Override
    public void start(Stage stage) throws IOException {
        this.screenHeight = 768.0;
        this.screenWidth = 1024.0;
        ArrayList<Integer> init = new ArrayList<Integer>();
        Random rand = new Random();
        for (Integer i = 0; i < 8; i++)
            init.add(rand.nextInt(50));
        MainArray mainArray = new MainArray(init);
        mainArray.setWindowSize(screenWidth, screenHeight);
        mainArray.render();
        AnimationQueue newQueue = new AnimationQueue();
        for (int i = 0; i < 8; i++)
            for (int j = i + 1; j < 8; j++) {
                if (mainArray.get(i).getKey()<mainArray.get(j).getKey())
                newQueue.add(mainArray.swap(i, j));
            }
        newQueue.setCompleted(true);
        Button newButton = new Button();
        newButton.setText("Start");
        newButton.setOnMouseClicked(Event -> {newQueue.playQueue();});
        AnchorPane ap = new AnchorPane();
        ap.getChildren().add(newButton);
        ap.getChildren().add(mainArray.renderedArray);
        scene = new Scene(ap, screenWidth, screenHeight);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}