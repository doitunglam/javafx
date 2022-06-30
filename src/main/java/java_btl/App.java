package java_btl;

import java.io.IOException;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;

/*
 * JavaFX App
 */
public class App extends Application {
    
    @Override 
    public void start(Stage stage) throws IOException{
        WelcomeScene welcomeScene = new WelcomeScene(new Group(), 500, 200);
        welcomeScene.render1();
        stage.setTitle("Welcome "+ (char)34+ "you"+ (char)34+ " :)");
        stage.setScene(welcomeScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}