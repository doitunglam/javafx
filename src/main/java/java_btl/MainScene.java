package java_btl;
//from me with love <3

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class MainScene extends Scene {


    public static MainArray mainArray;
    public AnimationQueue animationQueue;
    

    // private MainArray mainArray;
    // private AnimationQueue animationQueue;
    public TextArea textIndicator = new TextArea(); 


    public MainScene(Parent arg0, double arg1, double arg2) {
        super(arg0, arg1, arg2);
        textIndicator = new TextArea();
        animationQueue = new AnimationQueue();
    }

    public static MainArray getMainArray() {
        return mainArray;
    }

    public AnimationQueue getAnimationQueue() {
        return animationQueue;
    }

    public void setMainArray(MainArray mainArray) {
        this.mainArray = mainArray;
    }

    public void render() {
        mainArray.setWindowSize(this.getWidth(), this.getHeight());
        mainArray.render();

        Button pauseButton = new Button();
        Button playButton = new Button();
        Button exitButton = new Button();

        pauseButton.setDisable(true);
        pauseButton.setText("Pause");
        pauseButton.setOnMouseClicked(Event -> {
            pauseButton.setDisable(true);
            playButton.setDisable(false);
            animationQueue.setInterupted();
        });

        playButton.setText("Play");
        playButton.setOnMouseClicked(Event -> {
            playButton.setDisable(true);
            pauseButton.setDisable(false);
            animationQueue.resetInteruputed();
        });

        exitButton.setOnMouseClicked(Event -> {
            this.setRoot(new Parent() {
            });
        });

        FlowPane fp = new FlowPane();
        fp.getChildren().addAll(playButton, pauseButton);
        fp.setHgap(10.0);
        fp.setAlignment(Pos.CENTER);

        BorderPane bp = new BorderPane();
        bp.setBottom(fp);
        fp.setPadding(new Insets(100));
        
        textIndicator.setMaxWidth(200);
        textIndicator.setMaxHeight(50);
        textIndicator.setEditable(false);
        textIndicator.setOnMouseClicked(Event ->{});
        BorderPane.setAlignment(textIndicator, Pos.BOTTOM_RIGHT);
        bp.setTop(textIndicator);
        bp.getChildren().add(mainArray.renderedArray);
        bp.getChildren().addAll(mainArray.primaryIndicator, mainArray.secondaryIndicator);
        this.setRoot(bp);
    }

    public void setAnimationQueue() {
        // animationQueue.add(mainArray.moveIndicatorTo(mainArray.primaryIndicator, 0));
        // for (int i = 0; i < 14; i++) {
        //     if (i == 0)
        //         animationQueue.add(mainArray.primaryIndicator.makeAppear());
        //     else
        //         animationQueue.add(mainArray.moveIndicatorTo(mainArray.primaryIndicator, i));
        //     animationQueue.add(mainArray.moveIndicatorTo(mainArray.secondaryIndicator, i + 1));
        //     for (int j = i + 1; j < 15; j++) {
        //         if (j == i + 1)
        //             animationQueue.add(mainArray.secondaryIndicator.makeAppear());
        //         else
        //             animationQueue.add(mainArray.moveIndicatorTo(mainArray.secondaryIndicator, j));
        //         if (mainArray.get(i).getKey() < mainArray.get(j).getKey())
        //             animationQueue.add(mainArray.swap(i, j));
        //     }
        //     animationQueue.add(mainArray.secondaryIndicator.makeDisappear());
        // }
        // animationQueue.add(mainArray.primaryIndicator.makeDisappear());
        // animationQueue.setCompleted(true);
        animationQueue.add(mainArray.moveIndicatorTo(mainArray.primaryIndicator, 0));
        animationQueue.add(mainArray.moveIndicatorTo(mainArray.secondaryIndicator, 1));
        int swapped = 0;
        do{
            swapped = 0;
        for(int i=0;i<mainArray.getSize()-1;i++){
            if(i==0){
                animationQueue.add(mainArray.primaryIndicator.makeAppear());
                animationQueue.add(mainArray.secondaryIndicator.makeAppear());
            }
            else{
                 animationQueue.add(mainArray.moveIndicatorTo(mainArray.primaryIndicator, i));
                animationQueue.add(mainArray.moveIndicatorTo(mainArray.secondaryIndicator, i+1));
            }
            if(mainArray.get(i).getKey() > mainArray.get(i+1).getKey()){
                  animationQueue.add(mainArray.moveIndicatorTo(mainArray.primaryIndicator, i));
                  animationQueue.add(mainArray.moveIndicatorTo(mainArray.secondaryIndicator, i+1));
                  animationQueue.add(mainArray.secondaryIndicator.makeAppear());
                  animationQueue.add(mainArray.swap(i, i+1));
                  swapped = 1;
            }
        }
        animationQueue.add(mainArray.primaryIndicator.makeDisappear());
        animationQueue.add(mainArray.secondaryIndicator.makeDisappear());
    } while(swapped == 1);
        animationQueue.setCompleted(true);
    }
    // public void quickSort(){
    //     animationQueue.add(mainArray.moveIndicatorTo(mainArray.primaryIndicator, 0));

    // }

    public void setAnimationQueue(AnimationQueue animationQueue){
        this.animationQueue = animationQueue;
    }
    public void playAnimation(){
        animationQueue.resetInteruputed();
    }
    

}
