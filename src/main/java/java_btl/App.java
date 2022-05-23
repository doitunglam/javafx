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
        for (Integer i = 0; i < 15; i++)
            init.add(rand.nextInt(100));
        MainArray mainArray = new MainArray(init);
        mainArray.setWindowSize(screenWidth, screenHeight);
        mainArray.render();
        AnimationQueue newQueue = new AnimationQueue();
        newQueue.add(mainArray.moveIndicatorTo(mainArray.primaryIndicator, 0));
        for (int i = 0; i < 14; i++) {
            if (i == 0)
                newQueue.add(mainArray.primaryIndicator.makeAppear());
            else
                newQueue.add(mainArray.moveIndicatorTo(mainArray.primaryIndicator, i));
            newQueue.add(mainArray.moveIndicatorTo(mainArray.secondaryIndicator, i+1));
            for (int j = i + 1; j < 15; j++) {
                if (j == i + 1)
                    newQueue.add(mainArray.secondaryIndicator.makeAppear());
                else
                    newQueue.add(mainArray.moveIndicatorTo(mainArray.secondaryIndicator, j));
                if (mainArray.get(i).getKey() < mainArray.get(j).getKey())
                    newQueue.add(mainArray.swap(i, j));
            }
            newQueue.add(mainArray.secondaryIndicator.makeDisappear());
        }
        newQueue.add(mainArray.primaryIndicator.makeDisappear());
        newQueue.setCompleted(true);
        Button newButton = new Button();
        newButton.setText("Start");
        newButton.setOnMouseClicked(Event -> {
            newQueue.playQueue();
        });
        AnchorPane ap = new AnchorPane();
        ap.getChildren().add(newButton);
        ap.getChildren().add(mainArray.renderedArray);
        ap.getChildren().addAll(mainArray.primaryIndicator, mainArray.secondaryIndicator);
        scene = new Scene(ap, screenWidth, screenHeight);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}