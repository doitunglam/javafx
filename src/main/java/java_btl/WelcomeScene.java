package java_btl;

import java.util.ArrayList;
import java.util.Random;



//import com.example.Method;

// import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.Group;

// import java.io.IOException;
public class WelcomeScene extends Scene {

    public WelcomeScene(Parent arg0, double arg1, double arg2) {
        super(arg0, arg1, arg2);
    }
    int a = 0;
    public void render1() {
        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.setHgap(15);
        root.setVgap(15);

        Label labelTitle = new Label("Enter your array:");
        root.add(labelTitle, 0, 0, 2, 1);

        TextField fieldArray = new TextField();
        root.add(fieldArray, 2, 0, 2, 1);

        Button enterArray = new Button("Enter Array");
        GridPane.setHalignment(enterArray, HPos.RIGHT);
        root.add(enterArray,3,1);

        Label labelTitle1 = new Label("Or random:");
        root.add(labelTitle1, 5, 0);

        Button random = new Button("Random Array");
        root.add(random, 6, 0);

        Label labelSelect = new Label("Select the method:");
        root.add(labelSelect, 0, 3);

        Method bubblesort = new Method("Bubble Sort");
        Method quicksort = new Method("Quick Sort");
        Method heapsort = new Method("Heap Sort");
        Method radixsort = new Method("Radix Sort");
        ObservableList<Method> argorithms //
                = FXCollections.observableArrayList(bubblesort, quicksort, heapsort, radixsort);
        ChoiceBox<Method> choiceBox = new ChoiceBox<Method>(argorithms);
        root.add(choiceBox, 3, 3);

        GridPane.setHalignment(choiceBox, HPos.RIGHT);

        Button start = new Button("Start");
        GridPane.setHalignment(start, HPos.LEFT);
        root.add(start, 4, 5);
        start.setPrefSize(75, 40);

        ArrayList<Integer> init = new ArrayList<Integer>();
        
        enterArray.setOnMouseClicked(e ->{
            a=0;
        });
        random.setOnMouseClicked(e -> {
            Random rd = new Random();
            init.clear();
            for (int i = 0; i <= 10; i++) {
                init.add(i,rd.nextInt(100));
            }
            a =1;

        });

        start.setOnMouseClicked(e -> {
            // start.setDisable(true);
            if (a == 0) {
                String array = fieldArray.getText();
                String[] b = array.split(" ");
                init.clear();
                for (int i = 0; i < b.length; i++) {
                    init.add(Integer.parseInt(b[i]));
                }
            }
            MainScene mainScene = new MainScene(new Group(), 1024, 500);
            MainArray mainArray = new MainArray(init);
            mainScene.setMainArray(mainArray);
            mainScene.render();

            Sorting sorting = new Sorting();
            String option = choiceBox.getValue().toString();
            if (option.compareTo("Bubble Sort") == 0) {
                sorting.bubbleSort(mainScene.animationQueue, MainScene.mainArray);
            } else if (option.compareTo("Quick Sort") == 0) {
                sorting.quickSort(mainScene.animationQueue, MainScene.mainArray, 0, mainArray.getSize() - 1);
            } else if(option.compareTo("Heap Sort") == 0){
                sorting.heapsort(mainScene.animationQueue,mainScene.mainArray);
            } else if(option.compareTo("Radix Sort") == 0){
                sorting.RadixSort(mainScene.animationQueue, mainScene.mainArray);
            }
                System.out.println("Initialization failed!");

            Stage scene2 = new Stage();
            scene2.setScene(mainScene);
            scene2.setTitle("Demo sorting");
            scene2.setX(200);
            scene2.setY(50);
            scene2.show();

        });

        this.setRoot(root);
    }
}