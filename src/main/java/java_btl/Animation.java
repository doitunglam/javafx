package java_btl;

import javafx.animation.ParallelTransition;

public class Animation {
    private String message;
    private ParallelTransition animation;

    public Animation(String message, ParallelTransition animation) {
        this.message = message;
        this.animation = animation;
    }
    public Animation(){
        
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ParallelTransition getAnimation() {
        return animation;
    }

    public void setAnimation(ParallelTransition animation) {
        this.animation = animation;
    }

}
