package java_btl;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private double screenHeight,screenWidth;
    
    @Override
    public void start(Stage stage) throws IOException {
        this.screenHeight=768.0;
        this.screenWidth=1024.0;
        ArrayList<Integer> init = new ArrayList<Integer>();
        for (Integer i=1;i<8;i++)
        init.add(i);
        init.add(2);
        mainArray hb = new mainArray(init); 
        hb.setWindowSize(screenWidth, screenHeight);
        hb.render();
        Group gp = hb.renderedArray;
        ParallelTransition ts = hb.swap(5, 0);
        ParallelTransition ts2 = hb.swap(0, 3);
        PauseTransition pts = new PauseTransition(Duration.millis(100));
        SequentialTransition sq =new SequentialTransition(ts,pts);
        sq.play();
        sq.getChildren().add(ts2);
        scene = new Scene(gp, screenWidth, screenHeight);
        stage.setScene(scene);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch();
    }

}