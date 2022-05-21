package java_btl;

import java.util.ArrayList;

public class mainArray extends ArrayList<ArrayNode> {
    public mainArray(ArrayList<Integer> src) {
        for (Integer integer : src) {
            this.add(new ArrayNode(integer));
        }
    }

    public ArrayGroup render(double width, double height) {
        double centerX = height / 2;
        double centerY = width / 2;
        double offset = 60;
        ArrayGroup gp = new ArrayGroup();
        double offsetBias = this.size() / 2.0 - 0.5;
        for (int i = 0; i < this.size(); i++) {
            ArrayNode aNode = this.get(i);
            aNode.setxCoor(centerX + offset * (i - offsetBias));
            aNode.setyCoor(centerY);
            gp.getChildren().add(aNode.render());
        }
        return gp;
    }
}
