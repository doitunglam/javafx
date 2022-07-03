package java_btl;

import java.util.ArrayList;

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

    public void render1() {
        GridPane root = new GridPane();
        root.setPadding(new Insets(20));
        root.setHgap(20);
        root.setVgap(15);

        Label labelTitle = new Label("Enter your array:");
        root.add(labelTitle, 0, 0, 2, 1);

        TextField fieldArray = new TextField();
        root.add(fieldArray, 2, 0, 2, 1);

        Label labelSelect = new Label("Select the method:");
        root.add(labelSelect, 0, 2);

        Method bubblesort = new Method("Bubble sort");
        Method quicksort = new Method("Quick sort");
        Method heapsort = new Method("Heap sort");
        Method radixsort = new Method("Radix sort");
        ObservableList<Method> argorithms //
                = FXCollections.observableArrayList(bubblesort, quicksort, heapsort, radixsort);
        ChoiceBox<Method> choiceBox = new ChoiceBox<Method>(argorithms);
        root.add(choiceBox, 2, 2);

        Button start = new Button("Start");
        GridPane.setHalignment(start, HPos.RIGHT);
        root.add(start, 2, 4);

        start.setOnMouseClicked(e -> {
            // start.setDisable(true);
            String array = fieldArray.getText();
            String[] b = array.split(" ");
            ArrayList<Integer> init = new ArrayList<Integer>();
            init.clear();
            for (int i = 0; i < b.length; i++) {
                init.add(Integer.parseInt(b[i]));
            }

            MainScene mainScene = new MainScene(new Group(), 1024, 500);
            MainArray mainArray = new MainArray(init);
            mainScene.setMainArray(mainArray);
            mainScene.render();

            Sorting sorting = new Sorting();
            String option = choiceBox.getValue().toString();
            if (option.compareTo("Bubble sort") == 0) {
                sorting.bubbleSort(mainScene.animationQueue, mainScene.mainArray);
            } else if (option.compareTo("Quick sort") == 0) {
                sorting.quickSort(mainScene.animationQueue, mainScene.mainArray, 0, mainArray.getSize() - 1);
            } else
                System.out.println("meo chay");
            
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
