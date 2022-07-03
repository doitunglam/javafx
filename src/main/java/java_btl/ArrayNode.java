package java_btl;

import javafx.collections.ObservableList;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ArrayNode {
    private double xCoor,yCoor;
    private int key;

    public double getxCoor() {
        return xCoor;
    }

    public void setxCoor(double xCoor) {
        this.xCoor = xCoor;
    }

    public double getyCoor() {
        return yCoor;
    }

    public void setyCoor(double yCoor) {
        this.yCoor = yCoor;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public ArrayNode(int key) {
        this.key = key;
        this.xCoor = 0;
        this.yCoor = 0;
    }
    //Javafx 
    public Group render() {

        //dark magic
        Text text = new Text(Integer.toString(this.key));
        text.setX(this.xCoor);
        text.setY(this.yCoor);
        text.setFont(Font.font("Arial", 24));
        double width = text.prefWidth(-1);
        text.setX(this.xCoor - width / 2);
        text.setTextOrigin(VPos.CENTER);        
        Circle circle = new Circle();
        circle.setCenterX(this.xCoor);
        circle.setCenterY(this.yCoor);
        circle.setRadius(30);
        circle.setFill(Color.RED);
        Group newGroup = new Group();
        ObservableList<Node> list = newGroup.getChildren();
        list.add(circle);
        list.add(text);
        
        return newGroup;
    }
}
