import com.teacup.tech.sys.enums.DirectionState;
import com.teacup.tech.sys.enums.SpeedState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CeilingFanTest {

    private CeilingFan ceilingFan;
    private Supplier<Integer> mockUserInputSupplier;

    /*
     * Default to simulate pulling speed cord
     */
    @BeforeEach
    public void setUp() {
        mockUserInputSupplier = () -> 1;
        ceilingFan = new CeilingFan(SpeedState.OFF, DirectionState.FORWARD, mockUserInputSupplier);
    }

    /*
     * Simulate pulling speed cord
     */
    @Test
    public void testPullSpeedCordForward() {
        mockUserInputSupplier = () -> 1;
        ceilingFan = new CeilingFan(SpeedState.OFF, DirectionState.FORWARD, mockUserInputSupplier);

        ceilingFan.pullSpeedCord();

        assertEquals(SpeedState.LOW, ceilingFan.getLevel(), "Speed should be LOW after one pull in FORWARD direction.");
    }

    /*
     * Simulate pulling speed cord
     */
    @Test
    public void testPullSpeedCordReversed() {
        ceilingFan = new CeilingFan(SpeedState.MEDIUM, DirectionState.REVERSED, () -> 1);

        ceilingFan.pullSpeedCord();

        assertEquals(SpeedState.LOW, ceilingFan.getLevel(), "Speed should be LOW after pulling cord in REVERSED direction.");
    }

    /*
     * Simulate pulling direction cord
     */
    @Test
    public void testPullDirectionCardForwardToReversed() {
        mockUserInputSupplier = () -> 2;
        ceilingFan = new CeilingFan(SpeedState.OFF, DirectionState.FORWARD, mockUserInputSupplier);

        ceilingFan.pullDirectionCard();

        assertEquals(DirectionState.REVERSED, ceilingFan.getState(), "Direction should be REVERSED after one pull.");
    }

    /*
     * Simulate pulling direction cord
     */
    @Test
    public void testPullDirectionCardReversedToForward() {
        ceilingFan = new CeilingFan(SpeedState.OFF, DirectionState.REVERSED, () -> 2);

        ceilingFan.pullDirectionCard();

        assertEquals(DirectionState.FORWARD, ceilingFan.getState(), "Direction should be FORWARD after one pull.");
    }

    /*
     * Simulate valid user input
     */
    @Test
    public void testGetUserOperationValidInput() {
        mockUserInputSupplier = () -> 1;
        ceilingFan = new CeilingFan(SpeedState.OFF, DirectionState.FORWARD, mockUserInputSupplier);

        int result = ceilingFan.getUserOperation("Pull cord 1 for Speed or 2 for Direction: ");

        assertEquals(1, result, "User operation should return 1 for valid input.");
    }

    /*
     * Validation for two inputs are invalid, then valid
     */
    @Test
    public void testGetUserOperationInvalidInput() {
        mockUserInputSupplier = new Supplier<>() {
            private int count = 0;

            @Override
            public Integer get() {
                return count++ < 2 ? 3 : 1;
            }
        };
        ceilingFan = new CeilingFan(SpeedState.OFF, DirectionState.FORWARD, mockUserInputSupplier);

        int result = ceilingFan.getUserOperation("Pull cord 1 for Speed or 2 for Direction: ");

        assertEquals(1, result, "User operation should handle invalid input and return 1.");
    }
}
