import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    @Test
    void constructorHippodrome_Null_Throws_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void constructorHippodrome_Null_Throws_ThrowsMessage_Horses_cannot_be_null() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }


    static Stream<List<Horse>> createEmptyList() {
        return Stream.of(Collections.emptyList());
    }

    @ParameterizedTest
    @MethodSource("createEmptyList")
    void constructorHippodrome_EmptyList_Throws_IllegalArgumentException(List<Horse> horses) {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }


    @ParameterizedTest
    @MethodSource("createEmptyList")
    void constructorHippodrome_EmptyList_hrowsMessage_Horses_cannot_be_empty(List<Horse> horses) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesTest_the_same_List_as_passedTo_constructor() {
        List<Horse> horseList = IntStream.range(0, 30)
                .mapToObj(i -> new Horse("horse " + i, 5.0 + i, 100.0 + i))
                .collect(Collectors.toList());
        Hippodrome hippodrome = new Hippodrome(horseList);
        List<Horse> expectedList = horseList;
        List<Horse> actualList = hippodrome.getHorses();
        assertEquals(expectedList, actualList);
    }

    @Test
    void moveTest_invokes_at_all_horses() {

        List<Horse> listMockedHorses = IntStream.range(0, 50)
                .mapToObj(i -> Mockito.mock(Horse.class))
                .collect(Collectors.toList());

        Hippodrome hippodrome = new Hippodrome(listMockedHorses);
        hippodrome.move();
        listMockedHorses.forEach(horseMock -> Mockito.verify(horseMock).move());

    }
    @Test
    void getWinnerTest_return_horse_with_theMost_distance(){
        List<Horse> horseList = new ArrayList<>();
        horseList.add(new Horse("horse1",10,10));
        horseList.add(new Horse("horse2",20,20));
        horseList.add(new Horse("horse2",30,30));
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(30,hippodrome.getWinner().getDistance());
    }

}