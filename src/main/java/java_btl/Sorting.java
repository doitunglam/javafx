package java_btl;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Parent;
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
     Group group = new Group(textField1,textField2,textField3);
     mainScene = new MainScene(group, 1024 ,768 );
    }

    public Sorting(){
        
    }

    // public void setMainArray(ArrayList<Integer> src){
    // mainArray = new MainArray(src);
    // }

    public void bubleSort(AnimationQueue animationQueue, MainArray mainArray) {
        animationQueue.add(mainArray.moveIndicatorTo(mainArray.primaryIndicator, 0));
        animationQueue.add(mainArray.moveIndicatorTo(mainArray.secondaryIndicator, 1));
        int swapped = 0;
        do {
            swapped = 0;
            for (int i = 0; i < mainArray.getSize() - 1; i++) {
                if (i == 0) {
                    animationQueue.add(mainArray.primaryIndicator.makeAppear());
                    animationQueue.add(mainArray.secondaryIndicator.makeAppear());
                } else {
                    animationQueue.add(mainArray.moveIndicatorTo(mainArray.primaryIndicator, i));
                    animationQueue.add(mainArray.moveIndicatorTo(mainArray.secondaryIndicator, i + 1));
                }
                if (mainArray.get(i).getKey() > mainArray.get(i + 1).getKey()) {
                    animationQueue.add(mainArray.moveIndicatorTo(mainArray.primaryIndicator, i));
                    animationQueue.add(mainArray.moveIndicatorTo(mainArray.secondaryIndicator, i + 1));
                    animationQueue.add(mainArray.secondaryIndicator.makeAppear());
                    animationQueue.add(mainArray.swap(i, i + 1));
                    swapped = 1;
                }
            }
            animationQueue.add(mainArray.primaryIndicator.makeDisappear());
            animationQueue.add(mainArray.secondaryIndicator.makeDisappear());
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
        animationQueue.add(mainArray.moveIndicatorTo(mainArray.primaryIndicator, indexPivot));
        animationQueue.add(mainArray.moveIndicatorTo(mainArray.secondaryIndicator, R));
        animationQueue.add(mainArray.primaryIndicator.makeAppear());
        animationQueue.add(mainArray.secondaryIndicator.makeAppear());
        animationQueue.add(mainArray.swap(indexPivot, R));
        // swap(A[indexPivot], A[R]);
        int storeIndex = L;
        for (int i = L; i <= R - 1; i++) {
            if (mainArray.get(i).getKey() < pivot) {
                // swap(A[storeIndex], A[i]);
                // tmp = A[storeIndex];
                // A[storeIndex] = A[i];
                // A[i] = tmp;
                animationQueue.add(mainArray.moveIndicatorTo(mainArray.primaryIndicator, storeIndex));
                animationQueue.add(mainArray.moveIndicatorTo(mainArray.secondaryIndicator, i));
                animationQueue.add(mainArray.swap(storeIndex, i));
                storeIndex++;
            }
        }
        // swap(A[storeIndex], A[R]);
        // tmp = A[storeIndex];
        // A[storeIndex] = A[R];
        // A[R] = tmp;
        animationQueue.add(mainArray.swap(storeIndex, R));
        return storeIndex;
    }

 
}