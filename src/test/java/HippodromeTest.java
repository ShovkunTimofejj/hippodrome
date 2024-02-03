import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    public void constructor_NullListParamPassed_ThrowsIllegalArgumentException(){
        String expectedMassage = "Horses cannot be null.";;

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals(expectedMassage, throwable.getMessage());
    }
    @Test
    public void constructor_EmptyListParamPassed_ThrowsIllegalArgumentException(){
        String expectedMassage = "Horses cannot be empty.";
        List<Horse> horseList = new ArrayList<>();

        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horseList));
        assertEquals(expectedMassage, throwable.getMessage());
    }

    @Test
    void getHorses_ReturnsListAllHorsesInOrder() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i < 60; i++) {
            horses.add(new Horse("HorseName" + i,i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertNotNull(hippodrome.getHorses());
        assertEquals(60, hippodrome.getHorses().size());
        assertEquals("HorseName0", hippodrome.getHorses().get(0).getName());
        assertEquals("HorseName10", hippodrome.getHorses().get(10).getName());
        assertEquals("HorseName59", hippodrome.getHorses().get(59).getName());
    }

    @Test
    void move_CallsMoveMethodForAllHorses() {
        final List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(horses);

        hippodrome.move();

        for (Horse hors : horses) {
            Mockito.verify(hors,Mockito.times(1)).move();

        }
    }

    @Test
    void getWinner_ReturnsTheCorrectWinner() {
        Hippodrome hippodrome = new Hippodrome(List.of(
                new Horse("Hors10",1,10),
                new Horse("Horse20",1,20),
                new Horse("Horse30",1,30)

        ));
       assertEquals("Horse30", hippodrome.getWinner().getName());
    }
}