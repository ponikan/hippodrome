import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    public void constructorThrowsException_IfListNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void constructorThrowsMessage_HorsesCannotBeNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorThrowsException_IfListEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void constructorThrowsMessage_HorsesCannotBeEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getHorsesTest_returnsTheSameListFromConstructor() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String name = i + "horse";
            double speed = 1.0 + i;
            double distance = 100.0 + i;
            horses.add(new Horse(name, speed, distance));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertIterableEquals(horses,hippodrome.getHorses());
    }
    @Test
   public void moveTest_foeAllHorsesInList(){
        Horse mockHorse = Mockito.mock(Horse.class);
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i<50;i++){
            horseList.add(mockHorse);
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        Mockito.verify(mockHorse,Mockito.times(50)).move();
    }
    @Test
    public void getWinnerTest_returnTheMostDistanceHorse(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            String name = i + "horse";
            double speed = 1.0 + i;
            double distance = 100.0 + i;
            horses.add(new Horse(name, speed, distance));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(129,hippodrome.getWinner().getDistance());
    }
}