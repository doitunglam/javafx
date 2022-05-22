package java_btl;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.ParallelTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
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
        for (Integer i = 0; i < 8; i++)
            init.add(i);
        MainArray mainArray = new MainArray(init);
        mainArray.setWindowSize(screenWidth, screenHeight);
        mainArray.render();
        ParallelTransition ts1 = mainArray.swap(0, 5);
        ParallelTransition ts2 = mainArray.swap(0, 3);
        ParallelTransition ts3 = mainArray.swap(0, 6);
        ParallelTransition ts4 = mainArray.swap(0, 4);
        AnimationQueue newQueue = new AnimationQueue();
        newQueue.add(ts1);
        newQueue.add(ts2);
        newQueue.add(ts3);
        newQueue.add(ts4);
        newQueue.setCompleted(false);
        newQueue.playQueue();
        Group gp = mainArray.renderedArray;
        scene = new Scene(gp, screenWidth, screenHeight);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}