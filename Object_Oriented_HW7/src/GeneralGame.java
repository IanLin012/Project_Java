import java.util.Random;

public class GeneralGame implements PlayGenerator, PlayGround {
    private Gesture[] sysGesture = new Gesture[3]; // private variable

    public Gesture[] getSysGesture() { // sysGesture accessor
        return sysGesture;
    }

    public Gesture generateGestureByRandom() { // implement PlayGenerator interface
        Gesture[] values = Gesture.values();
        int len = values.length;
        int randIdx = new Random().nextInt(len);
        return values[randIdx];
    }

    public String play(Gesture[] user) { // implement PlayGround interface
        int winTimes = 0;
        for(int i=0; i<3; i++) {
            sysGesture[i] = generateGestureByRandom();
            if((sysGesture[i]==Gesture.STONE && user[i]==Gesture.PAPER) || (sysGesture[i]==Gesture.PAPER && user[i]==Gesture.SCISSORS) || (sysGesture[i]==Gesture.SCISSORS && user[i]==Gesture.STONE)) {
                winTimes++;
            }
            else if((user[i]==Gesture.STONE && sysGesture[i]==Gesture.PAPER) || (user[i]==Gesture.PAPER && sysGesture[i]==Gesture.SCISSORS) || (user[i]==Gesture.SCISSORS && sysGesture[i]==Gesture.STONE)) {
                winTimes--;
            }
        }
        if(winTimes > 0) {
            return("You win!");
        }
        else if(winTimes == 0) {
            return("Game tie");
        }
        else {
            return("You lose!");
        }
    }
}