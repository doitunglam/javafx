package java_btl;

import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MainArray extends ArrayList<ArrayNode> {
    public Group renderedArray;
    private double windowWidth, windowHeight;
    private ArrayList<Integer> groupIndexMask;
    public Indicator primaryIndicator, secondaryIndicator, pivotIndicator, thirdIndicator;
    private Double offset;

    public MainArray(ArrayList<Integer> src) {
        this.offset = 60.0;
        this.primaryIndicator = new Indicator(0.8 * this.offset);
        this.primaryIndicator.setFill(Color.AQUA);
        this.secondaryIndicator = new Indicator(0.8 * this.offset);
        this.secondaryIndicator.setFill(Color.RED);
        this.pivotIndicator = new Indicator(0.8 * this.offset);
        this.pivotIndicator.setFill(Color.BLACK);
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

    // dao vi tri index1 va index2
    public ParallelTransition swap(int index1, int index2) {
        double xCoor1 = this.get(index1).getxCoor();
        double yCoor1 = this.get(index1).getyCoor();
        double xCoor2 = this.get(index2).getxCoor();
        double yCoor2 = this.get(index2).getyCoor();

        Path path1 = new Path();

        CubicCurveTo cubicTo1 = new CubicCurveTo();
        cubicTo1.setControlX1((xCoor1 + xCoor2) / 2);
        cubicTo1.setControlY1(200);
        cubicTo1.setControlX2((xCoor1 + xCoor2) / 2);
        cubicTo1.setControlY2(200);
        cubicTo1.setX(xCoor1);
        cubicTo1.setY(yCoor1);
        path1.getElements().add(new MoveTo(xCoor2, yCoor2));
        path1.getElements().add(cubicTo1);

        Path path2 = new Path();

        CubicCurveTo cubicTo2 = new CubicCurveTo();
        cubicTo2.setControlX1((xCoor1 + xCoor2) / 2);
        cubicTo2.setControlY1(200);
        cubicTo2.setControlX2((xCoor1 + xCoor2) / 2);
        cubicTo2.setControlY2(200);
        cubicTo2.setX(xCoor2);
        cubicTo2.setY(yCoor2);
        path2.getElements().add(new MoveTo(xCoor1, yCoor1));
        path2.getElements().add(cubicTo2);

        PathTransition pt1 = new PathTransition();
        pt1.setNode(renderedArray.getChildren().get(this.groupIndexMask.get(index1)));
        pt1.setPath(path2);
        pt1.setAutoReverse(false);
        pt1.setDuration(Duration.millis(600));
        pt1.setCycleCount(1);

        PathTransition pt2 = new PathTransition();
        pt2.setNode(renderedArray.getChildren().get(this.groupIndexMask.get(index2)));
        pt2.setPath(path1);
        pt2.setAutoReverse(false);
        pt2.setDuration(Duration.millis(600));
        pt2.setCycleCount(1);

        ParallelTransition prlts = new ParallelTransition(pt1, pt2);
        int tmp = this.get(index1).getKey();
        this.get(index1).setKey(this.get(index2).getKey());
        this.get(index2).setKey(tmp);
        Collections.swap(this.groupIndexMask, index1, index2);
        return prlts;
    }

    public int getSize() {
        return groupIndexMask.size();
    }
}
