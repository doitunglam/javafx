package java_btl;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class MainScene extends Scene {

    public static MainArray mainArray;
    public AnimationQueue animationQueue;

    public MainScene(Parent arg0, double arg1, double arg2) {
        super(arg0, arg1, arg2);
        animationQueue = new AnimationQueue();
    }

    public MainArray getMainArray() {
        return mainArray;
    }

    public AnimationQueue getAnimationQueue() {
        return animationQueue;
    }

    public void setMainArray(MainArray mainArray) {
        MainScene.mainArray = mainArray;
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
        bp.setPadding(new Insets(100));
        bp.getChildren().add(mainArray.renderedArray);
        bp.getChildren().addAll(mainArray.primaryIndicator, mainArray.secondaryIndicator);
        this.setRoot(bp);
    }

    public void setAnimationQueue(int n) {
        animationQueue.add(mainArray.moveIndicatorTo(mainArray.primaryIndicator, 0));
        for (int i = 0; i < n-1; i++) {
            if (i == 0)
                animationQueue.add(mainArray.primaryIndicator.makeAppear());
            else
                animationQueue.add(mainArray.moveIndicatorTo(mainArray.primaryIndicator, i));
            animationQueue.add(mainArray.moveIndicatorTo(mainArray.secondaryIndicator, i + 1));
            for (int j = i + 1; j < n; j++) {
                if (j == i + 1)
                    animationQueue.add(mainArray.secondaryIndicator.makeAppear());
                else
                    animationQueue.add(mainArray.moveIndicatorTo(mainArray.secondaryIndicator, j));
                if (mainArray.get(i).getKey() < mainArray.get(j).getKey())
                    animationQueue.add(mainArray.swap(i, j));
            }
            animationQueue.add(mainArray.secondaryIndicator.makeDisappear());
        }
        animationQueue.add(mainArray.primaryIndicator.makeDisappear());
        animationQueue.setCompleted(true);
    }
    public void setAnimationQueue(AnimationQueue animationQueue){
        this.animationQueue = animationQueue;
    }
}
