package java_btl;

import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MainArray extends ArrayList<ArrayNode> {
    public Group renderedArray;
    private double windowWidth, windowHeight;
    private ArrayList<Integer> groupIndexMask;
    public Indicator primaryIndicator, secondaryIndicator;
    private Double offset;

    public MainArray(ArrayList<Integer> src) {
        this.offset = 60.0;
        this.primaryIndicator = new Indicator(0.8 * this.offset);
        this.primaryIndicator.setFill(Color.AQUA);
        this.secondaryIndicator = new Indicator(0.8 * this.offset);
        this.secondaryIndicator.setFill(Color.RED);
        this.groupIndexMask = new ArrayList<Integer>();
        for (int i = 0; i < src.size(); i++) {
            this.add(new ArrayNode(src.get(i)));
            this.groupIndexMask.add(i);
        }

    }

    public ParallelTransition moveIndicatorTo(Indicator indicator, int tar) {
        return indicator.moveTo(this.get(tar).getxCoor(), this.get(tar).getyCoor() + 0.55 * this.offset);
    }

    public void setWindowSize(double windowWidth, double windowHeight) {
        this.windowHeight = windowHeight;
        this.windowWidth = windowWidth;
        render();
    }

    public void render() {
        double centerX = this.windowWidth / 2;
        double centerY = this.windowHeight / 2;
        this.offset = 60.0;
        Group gp = new Group();
        double offsetBias = this.size() / 2.0 - 0.5;
        for (int i = 0; i < this.size(); i++) {
            ArrayNode aNode = this.get(i);
            aNode.setxCoor(centerX + this.offset * (i - offsetBias));
            aNode.setyCoor(centerY);
            gp.getChildren().add(aNode.render());
        }
        this.renderedArray = gp;
    }

    public ParallelTransition swap(int index1, int index2) {
        double xOffset = this.get(index2).getxCoor() - this.get(index1).getxCoor();
        double yOffset = this.get(index2).getyCoor() - this.get(index1).getyCoor();
        TranslateTransition ts1 = new TranslateTransition();
        ts1.setNode(renderedArray.getChildren().get(this.groupIndexMask.get(index1)));
        ts1.setByX(xOffset);
        ts1.setByY(yOffset);
        ts1.setAutoReverse(false);
        ts1.setDuration(Duration.millis(600));
        ts1.setCycleCount(1);
        TranslateTransition ts2 = new TranslateTransition();
        ts2.setNode(renderedArray.getChildren().get(this.groupIndexMask.get(index2)));
        ts2.setByX(-xOffset);
        ts2.setByY(-yOffset);
        ts2.setAutoReverse(false);
        ts2.setDuration(Duration.millis(600));
        ts2.setCycleCount(1);
        ParallelTransition prlts = new ParallelTransition(ts1, ts2);
        int tmp = this.get(index1).getKey();
        this.get(index1).setKey(this.get(index2).getKey());
        this.get(index2).setKey(tmp);
        Collections.swap(this.groupIndexMask, index1, index2);
        return prlts;
    }
}
