import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
@Test
    void constructorHippodrome_Null_Throws_IllegalArgumentException(){
    assertThrows(IllegalArgumentException.class, ()-> new Hippodrome(null));
}
@Test
void constructorHippodrome_Null_Throws_ThrowsMessage_Horses_cannot_be_null(){
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    assertEquals("Horses cannot be null.", exception.getMessage());
}
@ParameterizedTest
@MethodSource("createEmptyList")
    void constructorHippodrome_EmptyList_Throws_IllegalArgumentException(List<Horse> horses){
    assertThrows(IllegalArgumentException.class, ()->new Hippodrome(horses));
}
static Stream<List<Horse>> createEmptyList(){
    return Stream.of(Collections.emptyList());
}
    @ParameterizedTest
    @MethodSource("createEmptyList")
    void constructorHippodrome_EmptyList_hrowsMessage_Horses_cannot_be_empty(List<Horse> horses){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

}