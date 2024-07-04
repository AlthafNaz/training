import org.example.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test {
    @org.junit.jupiter.api.Test
    public void testSquare() {
        //Main mathUtils = new Main();
        assertEquals(4, Main.square(2));
        assertEquals(9, Main.square(3));
        assertEquals(16, Main.square(4));
        assertEquals(25, Main.square(5));

    }
}


