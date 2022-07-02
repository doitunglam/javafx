package java_btl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    
    //Nhap mang(2) -> Xu ly mang + Sap xep Animation(2) 
    @Override 
    public void start(Stage stage) throws IOException {
        ArrayList<Integer> init = new ArrayList<Integer>();
        Random rand = new Random();
        for (Integer i = 0; i < 15; i++)
            init.add(rand.nextInt(100));

        MainScene mainScene = new MainScene(new Group(),1024,728);
        MainArray mainArray = new MainArray(init);
        
        mainScene.setMainArray(mainArray);
       
        mainScene.render();
        Sorting sorting = new Sorting();
        // sorting.setMainArray(init);
        // sorting.quickSort(mainScene.animationQueue,mainScene.mainArray, 0, mainArray.getSize()-1);
        sorting.quickSort(mainScene.animationQueue,mainScene.mainArray, 0, mainArray.getSize()-1);
        // mainScene.setAnimationQueue();
        // MainScene.Sorting sorting = mainScene.new Sorting();
        // sorting.bubleSort();
        // Sorting sorting = new Sorting();
        // mainScene.setAnimationQueue(sorting.bubleSort(mainArray));
        // mainScene.playAnimation();
        
        stage.setScene(mainScene);
        stage.setTitle("Demo Sorting");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}