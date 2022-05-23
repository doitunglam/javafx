package java_btl;

import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class Indicator extends Polygon {
    private Double offset;
    private Double xCoor, yCoor;

    public Double getOffset() {
        return offset;
    }

    public void setOffset(Double offset) {
        this.offset = offset;
    }

    public Double getxCoor() {
        return xCoor;
    }

    public void setxCoor(Double xCoor) {
        this.xCoor = xCoor;
    }

    public Double getyCoor() {
        return yCoor;
    }

    public void setyCoor(Double yCoor) {
        this.yCoor = yCoor;
    }

    public Indicator(Double offset) {
        this.offset = offset;
        this.getPoints()
                .addAll(new Double[] { 0.0, 0.0, -this.offset / 2, this.offset / 2, this.offset / 2, this.offset / 2 });
        this.xCoor = 0.0;
        this.yCoor = 0.0;
        this.setVisible(false);
    }

    public ParallelTransition moveTo(Double xCoor, Double yCoor) {
        TranslateTransition ts = new TranslateTransition();
        ts.setByX(xCoor - this.xCoor);
        ts.setByY(yCoor - this.yCoor);
        ts.setAutoReverse(false);
        ts.setDuration(Duration.millis(500));
        ts.setCycleCount(1);
        ts.setNode(this);
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        return new ParallelTransition(ts);
    }

    public ParallelTransition makeAppear() {
        SequentialTransition sq = new SequentialTransition();
        TranslateTransition ts = new TranslateTransition();
        ts.setNode(this);
        ts.setByX(0.0);
        ts.setByY(0.0);
        ts.setOnFinished(Event -> {
            this.setVisible(true);
        });
        PauseTransition ps = new PauseTransition();
        ps.setDuration(Duration.millis(400));
        sq.getChildren().add(ts);
        sq.getChildren().add(ps);
        return new ParallelTransition(sq);
    }

    public ParallelTransition makeDisappear() {
        TranslateTransition ts = new TranslateTransition();
        ts.setNode(this);
        ts.setByX(0.0);
        ts.setByY(0.0);
        ts.setOnFinished(Event -> {
            this.setVisible(false);
        });
        return new ParallelTransition(ts);
    }
}
