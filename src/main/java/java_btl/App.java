package java_btl;

import java.io.IOException;
import java.util.ArrayList;

import javafx.animation.ParallelTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        mainArray hb = new mainArray(init); 
        ArrayGroup gp = hb.render(screenHeight, screenWidth);
        ParallelTransition ts = gp.swapTransition(0, 5);
        ts.play();
        scene = new Scene(gp, screenWidth, screenHeight);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}