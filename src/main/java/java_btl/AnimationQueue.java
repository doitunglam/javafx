package java_btl;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

public class AnimationQueue extends  LinkedList<ParallelTransition> {
    private boolean isCompleted;
    private int waitCount;
    private Semaphore isInterruped;

    public AnimationQueue() {
        // super();
        isCompleted = false;
        waitCount = 0;
        isInterruped = new Semaphore(0);
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public void playQueue() {
        if (this.isInterruped.tryAcquire() == false)
            return;
        this.isInterruped.release();
        if (this.isEmpty() == true) {
            if (this.isCompleted == true) {
                System.out.println("Animation played successfully.");
                return;
            }
            if (this.isCompleted == false) {
                this.waitCount = this.waitCount + 1;
                if (waitCount < 5) {
                    PauseTransition wait = new PauseTransition(Duration.millis(500));
                    wait.play();
                    playQueue();
                } else {
                    System.out.println("Animation played unsuccessfully. Wait timeout.");
                    return;
                }

            }
        }
        if (this.isEmpty() == false) {
            ParallelTransition currentAnimationNode = this.pop();
            currentAnimationNode.setOnFinished(Event -> {
                playQueue();
            });
            currentAnimationNode.play();
        }
    }

    public void setInterupted() {
        try {
            this.isInterruped.acquire();
        } catch (InterruptedException e) {
            setInterupted();
        }
    }

    public void resetInteruputed(){
        this.isInterruped.release();
        playQueue();
    }

    // public void add(Animation animation) {
    //     this.add(animation.getAnimation());
    // }
}
