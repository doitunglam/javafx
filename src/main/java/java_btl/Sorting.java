package java_btl;

//import java.util.ArrayList;

import javafx.scene.Group;
//import javafx.scene.Parent;
import javafx.scene.control.TextField;

public class Sorting {
    // private AnimationQueue animationQueue;
    // private MainArray mainArray;
    // public void setMainArray(MainArray mainArray ){
    // this.mainArray = mainArray;
    // }
    public Sorting(MainScene mainScene) {
        TextField textField1 = new TextField("L = ");
        TextField textField2 = new TextField("R = ");
        TextField textField3 = new TextField("Pivot = ");
        Group group = new Group(textField1, textField2, textField3);
        mainScene = new MainScene(group, 1024, 500);
    }

    public Sorting() {

    }

    // public void setMainArray(ArrayList<Integer> src){
    // mainArray = new MainArray(src);
    // }

    public void bubbleSort(AnimationQueue animationQueue, MainArray mainArray) {

        int swapped = 0;
        do {
            swapped = 0;
            for (int i = 0; i < mainArray.getSize() - 1; i++) {
                if (i == 0) {
                    animationQueue.add(new Animation("", mainArray.moveIndicatorTo(mainArray.primaryIndicator, 0)));
                    animationQueue.add(new Animation("", mainArray.moveIndicatorTo(mainArray.secondaryIndicator, 1)));
                    animationQueue.add(new Animation("Primary Indicator: 0", mainArray.primaryIndicator.makeAppear()));
                    animationQueue
                            .add(new Animation("Secondary Indicator: 1", mainArray.secondaryIndicator.makeAppear()));
                } else {
                    animationQueue.add(new Animation("Primary Indicator: " + i,
                            mainArray.moveIndicatorTo(mainArray.primaryIndicator, i)));
                    animationQueue.add(new Animation("Secondary Indicator: " + (i + 1),
                            mainArray.moveIndicatorTo(mainArray.secondaryIndicator, i + 1)));
                }
                if (mainArray.get(i).getKey() > mainArray.get(i + 1).getKey()) {

                    animationQueue.add(new Animation("Swap A[i] and A[i+1] ", mainArray.swap(i, i + 1)));
                    swapped = 1;
                }
            }
            animationQueue.add(new Animation("", mainArray.primaryIndicator.makeDisappear()));
            animationQueue.add(new Animation("", mainArray.secondaryIndicator.makeDisappear()));
        } while (swapped == 1);
        animationQueue.setCompleted(true);
        // return animationQueue;
    }

    public void quickSort(AnimationQueue animationQueue, MainArray mainArray, int L, int R) {
        if (L < R) {
            int index = (L + R) / 2;
            index = partition(animationQueue, mainArray, L, R, index);
            if (L < index)
                quickSort(animationQueue, mainArray, L, index - 1);
            if (index < R)
                quickSort(animationQueue, mainArray, index + 1, R);
        }
    }

    public int partition(AnimationQueue animationQueue, MainArray mainArray, int L, int R, int indexPivot) {
        // int pivot = A[indexPivot];
        int pivot = mainArray.get(indexPivot).getKey();
        animationQueue.add(new Animation("", mainArray.moveIndicatorTo(mainArray.pivotIndicator, indexPivot)));
        animationQueue.add(new Animation("", mainArray.moveIndicatorTo(mainArray.secondaryIndicator, R)));
        // animationQueue.add(new Animation("",
        // mainArray.moveIndicatorTo(mainArray.pivotIndicator, R-1)));
        // animationQueue.add(new Animation("",
        // mainArray.primaryIndicator.makeAppear()));
        animationQueue.add(new Animation("", mainArray.pivotIndicator.makeAppear()));
        animationQueue.add(new Animation("", mainArray.secondaryIndicator.makeAppear()));
        animationQueue.add(new Animation("", mainArray.moveIndicatorTo(mainArray.primaryIndicator, L)));
        animationQueue.add(new Animation("", mainArray.swap(indexPivot, R)));

        // swap(A[indexPivot], A[R]);
        int storeIndex = L;
        for (int i = L; i <= R - 1; i++) {
            if (i == L) {
                animationQueue.add(new Animation("", mainArray.primaryIndicator.makeAppear()));
                // animationQueue.add(new Animation("", mainArray.secondaryIndicator.makeAppear()));
            }
            animationQueue.add(new Animation("", mainArray.moveIndicatorTo(mainArray.pivotIndicator, R)));
            if (mainArray.get(i).getKey() < pivot) {
                // swap(A[storeIndex], A[i]);
                // tmp = A[storeIndex];
                // A[storeIndex] = A[i];
                // A[i] = tmp;
                animationQueue.add(new Animation("Primary Indicator = " + storeIndex,
                        mainArray.moveIndicatorTo(mainArray.primaryIndicator, storeIndex)));
                animationQueue.add(new Animation("Secondary Indicator = " + i,
                        mainArray.moveIndicatorTo(mainArray.secondaryIndicator, i)));
                animationQueue.add(new Animation("", mainArray.swap(storeIndex, i)));
                storeIndex++;
            }
        }
        // swap(A[storeIndex], A[R]);
        // tmp = A[storeIndex];
        // A[storeIndex] = A[R];
        // A[R] = tmp;
        animationQueue.add(new Animation("Primary Indicator = " + storeIndex, mainArray.moveIndicatorTo(mainArray.primaryIndicator, storeIndex)));
        animationQueue.add(new Animation("Secondary Indicator = " + R,mainArray.moveIndicatorTo(mainArray.secondaryIndicator, R)));
        animationQueue.add(new Animation("", mainArray.swap(storeIndex, R)));
        animationQueue.add(new Animation("End", mainArray.primaryIndicator.makeDisappear()));
        animationQueue.add(new Animation("", mainArray.secondaryIndicator.makeDisappear()));
        animationQueue.add(new Animation("", mainArray.pivotIndicator.makeDisappear()));
        return storeIndex;
    }

}