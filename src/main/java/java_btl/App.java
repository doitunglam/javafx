package java_btl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private double screenHeight, screenWidth;
    //
    //Nhap mang(2) -> Xu ly mang + Sap xep Animation(2) 
    @Override 
    public void start(Stage stage) throws IOException {
        this.screenHeight = 768.0;
        this.screenWidth = 1024.0;
        ArrayList<Integer> init = new ArrayList<Integer>();
        // Nhap xuat du lieu 
        Random rand = new Random();
        for (Integer i = 0; i < 15; i++)
            init.add(rand.nextInt(100));
        MainArray mainArray = new MainArray(init);
        mainArray.setWindowSize(screenWidth, screenHeight);
        mainArray.render();
        //Khoi thuat toan sap xep
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
        //AnimationQueue day du
        Button playButton = new Button();
        Button pauseButton = new Button();
        pauseButton.setDisable(true);
        pauseButton.setText("Pause");
        playButton.setText("Play");
        playButton.setOnMouseClicked(Event -> {
            playButton.setDisable(true);
            pauseButton.setDisable(false);
            newQueue.resetInteruputed();
        });
        pauseButton.setOnMouseClicked(Event ->{
            pauseButton.setDisable(true);
            playButton.setDisable(false);
            newQueue.setInterupted();
           
        });
        AnchorPane ap = new AnchorPane();
        FlowPane fp = new FlowPane();
        fp.getChildren().addAll(playButton,pauseButton);
        ap.getChildren().add(fp);
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