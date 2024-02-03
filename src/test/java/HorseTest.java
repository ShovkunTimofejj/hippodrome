import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class HorseTest {
    @Test
    public void constructor_NullNameParamPassed_ThrowsIllegalArgumentException(){
        String expectedMassage = "Name cannot be null.";
        double speed = 1;
        double distance = 2;
       Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Horse(null,speed,distance));
        assertEquals(expectedMassage, throwable.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {" ","  ","\n","\n\n","\t","\t\t","\t \t"})
    public void constructor_EmptyNameParamPassed_ThrowsIllegalArgumentException(String name){
        String expectedMassage = "Name cannot be blank.";
        double speed = 1;
        double distance = 2;
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Horse(name,speed,distance));
        assertEquals(expectedMassage, throwable.getMessage());

    }
    @Test
    public void constructor_NegativeSpeedParamPassed_ThrowsIllegalArgumentException(){
        String expectedMassage = "Speed cannot be negative.";
        String nameHorse = "horseName";
        double speed = -1;
        double distance = 2;
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Horse(nameHorse,speed,distance));
        assertEquals(expectedMassage, throwable.getMessage());
    }
    @Test
    public void constructor_NegativeDistanceParamPassed_ThrowsIllegalArgumentException(){
        String expectedMassage = "Distance cannot be negative.";
        String nameHorse = "horseName";
        double speed = 1;
        double distance = -2;
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Horse(nameHorse,speed,distance));
        assertEquals(expectedMassage, throwable.getMessage());
    }



    @Test
    void getName_ReturnsExpectedName() {
        String nameHorse = "name";
        double speed = 1;
        double distance = 2;
        Horse horse = new Horse(nameHorse, speed, distance);

        String actualName = horse.getName();

        assertEquals(nameHorse, actualName);
    }

    @Test
    void getSpeed_ReturnsInitialSpeedValue() {
        String nameHorse = "name";
        double speed = 1;
        double distance = 2;
        Horse horse = new Horse(nameHorse, speed, distance);

        double actualSpeed = horse.getSpeed();

        assertEquals(speed, actualSpeed);
    }

    @Test
    void getDistance_ReturnsTheSpecifiedDistanceValue() {
         String nameHorse = "name";
         double speed = 1;
         double distance = 2;
         Horse horse = new Horse(nameHorse, speed, distance);

        double actualDistance = horse.getDistance();

        assertEquals(distance, actualDistance);
    }

    @Test
    void move_IncorporatesRandomSpeedAdjustment() {
        String nameHorse = "name";
        double speed = 1;
        double distance = 2;
        try (final MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)){
            Horse horse = new Horse(nameHorse,speed,distance);

            horse.move();

            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2,0.9));
        }

    }
    @ParameterizedTest
    @ValueSource(doubles = {0.2,0.3,0.5,0.8,20,133,0})
    public void move_UsedFormulaIsCorrect(double fakeRandomValue){
        double max = 0.9;
        double min = 0.2;
        String nameHorse = "name";
        double speed = 2.5;
        double distance = 111;
        Horse horse = new Horse(nameHorse,speed,distance);
        double expectedDistance = distance + speed * fakeRandomValue;

        try (MockedStatic<Horse> horseMockedStatic = mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(min,max)).thenReturn(fakeRandomValue);
            horse.move();
        }

        assertEquals(expectedDistance,horse.getDistance());

    }
}