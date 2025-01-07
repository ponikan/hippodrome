import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    @Test
    public void constructorThrowsException_IfNameNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 20.0, 100.0)
        );
    }

    @Test
    public void constructorThrowsMessage_NameCannotBeNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, 20.0, 100.0)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r"})
    public void constructorThrowsException_IfNameBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 20.0, 100.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t", "\r"})
    public void constructorThrowsMessage_NameCannotBeBlank(String name) {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 20.1, 120.2));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void constructorThrowsException_IfSpeedNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -1.1, 120.1));
    }

    @Test
    public void constructorThrowsMessage_SpeedCannotBeNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("horse", -2.0, 120.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void constructorThrowsException_IfDistanceNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("horse", 15.0, -4.2));
    }

    @Test
    public void constructorThrowsMessage_DistanceCannotBeNegative() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> new Horse("horse", 6.0, -123.2));
    assertEquals("Distance cannot be negative.",exception.getMessage());
}
@Test
    public void getNameTest(){
        Horse horse = new Horse("horse", 5.1,100.0);
        String actualName = horse.getName();
        assertEquals("horse", actualName);
}
@Test
    public void getSpeedTest(){
    Horse horse = new Horse("horse", 5.1,100.0);
    double actualSpeed = horse.getSpeed();
    assertEquals(5.1,actualSpeed);
}
@Test
    public void getDistanceTest_ReturnThirdParameter(){
        Horse horse = new Horse("horse",5.1, 100.1);
        double actualDistance = horse.getDistance();
        assertEquals(100.1, actualDistance);
}
@Test
public void getDistance_returnsZeroIfConstructorWithTwoParam(){
    Horse horse = new Horse("horse",5.1);
    double actualDistance = horse.getDistance();
    assertEquals(0,actualDistance);
}

@Test
   public void moveInvokesGetRandomDouble(){
    try(MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
        mockedStatic.when(()->Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.4);
        Horse horse = new Horse("horse",5.1,100.1);

        horse.move();
        mockedStatic.verify(()->Horse.getRandomDouble(0.2,0.9),times(1));
    }
}
@Test
    public void distanceByFormula(){
        try(MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)){
            mockedStatic.when(()-> Horse.getRandomDouble(0.2,0.9)).thenReturn(0.4);
            Horse horse = new Horse("horse",10,100);
            horse.move();
            assertEquals(104,horse.getDistance());
        }
}
}