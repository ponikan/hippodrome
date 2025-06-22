import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HorseTest {
  @Test
    void constructor_NullName_Throws_IllegalArgumentException(){

    assertThrows(IllegalArgumentException.class,
              ()-> new Horse(null,10.0,10.0));
  }
  @Test
  void constructor_NullName_ThrowsMessage_Name_cannot_be_null(){

    Throwable exception = assertThrows(IllegalArgumentException.class,
            () -> new Horse(null, 10.0, 10.0));

    assertEquals("Name cannot be null.",exception.getMessage());
  }
  @ParameterizedTest
  @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\u2002", "\u2003", "\u2004",
          "\u2005", "\u2006", "\u2008","\u2009"})

  void constructor_FirstEmptyString_Throws_IllegalArgumentException(String name){
    assertThrows(IllegalArgumentException.class,
            ()-> new Horse(name,10.0,10.0));
  }
  @ParameterizedTest
  @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\u2002", "\u2003", "\u2004",
          "\u2005", "\u2006", "\u2008","\u2009"})

  void  constructor_FirstEmptyString_ThrowsMessage_Name_cannot_be_blank(String name){
    Throwable exception = assertThrows(IllegalArgumentException.class,
            () -> new Horse(name, 10.1, 10.2));
    assertEquals("Name cannot be blank.", exception.getMessage());
  }
  @Test
  void constructor_secondNegativeNumber_Throws_IllegalArgumentException(){
    assertThrows(IllegalArgumentException.class,
            ()->new Horse("Horse",-1.0,1.0));
  }
  @Test
  void constructor_secondNegativeNumber_ThrowsMessage_Speed_cannot_be_negative(){
    Throwable exception = assertThrows(IllegalArgumentException.class,
            () -> new Horse("Horse", -1.0, 1.0));
    assertEquals("Speed cannot be negative.", exception.getMessage());
  }
  @Test
  void constructor_thirdNegativeNumber_Throws_IllegalArgumentException(){
  assertThrows(IllegalArgumentException.class,
          ()->new Horse("Voron", 12.0,-400.0));
  }
  @Test
  void constructor_thirdNegativeNumber_ThrowsMessage_Distance_cannot_be_negative(){
    Throwable exception = assertThrows(IllegalArgumentException.class,
            () -> new Horse("Voron", 14.0, -300.2));
    String exceptionMessage = exception.getMessage();
    assertEquals("Distance cannot be negative.", exceptionMessage);
  }

  @Test
  void getNameTest() {
    Horse horse = new Horse("Horse", 2.0, 3.5);
    String actualName = horse.getName();
    assertEquals("Horse",actualName);
  }
  @Test
  void getSpeedTest(){
    Horse horse = new Horse("Horse", 2.0, 3.5);
    double actualSpeed = horse.getSpeed();
    assertEquals(2.0, actualSpeed);
  }
  @Test
  void getDistanceTest_with_third_parameters(){
    Horse horse = new Horse("Horse", 2.0, 3.5);
    double actualDistance = horse.getDistance();
    assertEquals(3.5,actualDistance);
  }
  @Test
  void getDistanceTest_with_two_parameters_retun_zero(){
   Horse horse = new Horse("Horse", 2.0);
   double actualDistance = horse.getDistance();
   assertEquals(0,actualDistance);
  }

  @Test
  void whenInvoke_move_thenInvoke_getRandomDouble() {
    try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
      horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.3);
      Horse horse = new Horse("Horse", 2.0, 3.5);
      horse.move();
      horseMockedStatic.verify(()->Horse.getRandomDouble(0.2,0.9));
    }
  }
  @ParameterizedTest
  @CsvSource({
          "2.9, 100.0, 0.3",
          "3.9, 200.0, 0.4",
          "4.9, 250.0, 0.6",
  })
  void whenInvoke_move_thenDistance_seecks_ByFormula(double speed, double distance, double random) {
    try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
      horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
      Horse horse = new Horse("Horse", speed, distance);
      horse.move();
      double expectedDistance = speed * random + distance;
      assertEquals(expectedDistance, horse.getDistance());
    }
  }
}
