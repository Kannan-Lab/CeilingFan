import com.teacup.tech.sys.enums.DirectionState;
import com.teacup.tech.sys.enums.SpeedState;

import java.util.Scanner;
import java.util.function.Supplier;

public class CeilingFan {
    private SpeedState level;
    private DirectionState state;
    private final boolean isPowerOn;
    private final Supplier<Integer> userInputSupplier;

    public CeilingFan() {
        this(SpeedState.OFF, DirectionState.FORWARD, () -> {
            System.out.print("Pull cord 1 for Speed or 2 for Direction : ");
            return new Scanner(System.in).nextInt();
        });
    }

    // Constructor for dependency injection
    public CeilingFan(SpeedState level, DirectionState state, Supplier<Integer> userInputSupplier) {
        this.level = level;
        this.state = state;
        this.isPowerOn = true;
        this.userInputSupplier = userInputSupplier;
    }

    public void start() {
        System.out.println("Ceiling Fan Application");
        while (isPowerOn) {
            System.out.printf("%nSpeed:%s Direction:%s%n%n", level.ordinal() == 0 ? "Off" : level.name(), state.name());
            int cordChoice = getUserOperation("Pull cord 1 for Speed or 2 for Direction : ");
            if (cordChoice == 1) {
                pullSpeedCord();
            } else if (cordChoice == 2) {
                pullDirectionCard();
            }
        }
    }

    int getUserOperation(String prompt) {
        System.out.print(prompt);
        int cordChoice = userInputSupplier.get();
        while (cordChoice != 1 && cordChoice != 2) {
            System.out.printf("%nInvalid selection. Please choose either option 1 or 2.%n%n");
            cordChoice = userInputSupplier.get();
        }
        return cordChoice;
    }

    void pullSpeedCord() {
        System.out.println("-----------------------------------------------");
        System.out.println("Current Speed Mode is: " + level);
        if (state == DirectionState.FORWARD) {
            level = level.getNextMode();
        } else if (state == DirectionState.REVERSED) {
            level = level.getPreviousMode();
        }
        System.out.println("After Changing Mode is: " + level);
        System.out.println("-----------------------------------------------");
    }

    void pullDirectionCard() {
        System.out.println("-----------------------------------------------");
        System.out.println("Current Direction State is: " + state);
        this.state = changeDirection(state);
        System.out.println("After Changing Direction is: " + state);
        System.out.println("-----------------------------------------------");
    }

    DirectionState changeDirection(DirectionState presentState) {
        return presentState.ordinal() == 0 ? DirectionState.REVERSED : DirectionState.FORWARD;
    }

    public SpeedState getLevel() {
        return level;
    }

    public DirectionState getState() {
        return state;
    }

    public static void main(String[] args) {
        CeilingFan ceilingFan = new CeilingFan();
        ceilingFan.start();
    }
}
