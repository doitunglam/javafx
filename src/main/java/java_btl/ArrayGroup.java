package java_btl;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class ArrayGroup extends Group {
    public ParallelTransition swapTransition(int index1, int index2) {
        Group group1 = (Group) this.getChildren().get(index1);
        Group group2 = (Group) this.getChildren().get(index2);
        Circle circle1 = (Circle) group1.getChildren().get(0);
        Circle circle2 = (Circle) group2.getChildren().get(0);
        TranslateTransition ts1 = new TranslateTransition();
        ts1.setNode(group1);
        ts1.setByX(circle2.getCenterX() - circle1.getCenterX());
        ts1.setByY(circle2.getCenterY() - circle1.getCenterY());
        ts1.setDelay(Duration.millis(1000));
        ts1.setAutoReverse(false);
        ts1.setCycleCount(1);
        TranslateTransition ts2 = new TranslateTransition();
        ts2.setNode(group2);
        ts2.setByX(circle1.getCenterX() - circle2.getCenterX());
        ts2.setByY(circle1.getCenterY() - circle2.getCenterY());
        ts2.setDelay(Duration.millis(1000));
        ts2.setAutoReverse(false);
        ts2.setCycleCount(1);
        ParallelTransition prlts = new ParallelTransition(ts1, ts2);
        return prlts;
    }
    
}
