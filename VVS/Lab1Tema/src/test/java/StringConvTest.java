
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringConvTest {

    StringConv sc = new StringConv();

    @Test
    public void testStrToInt() {
        assertEquals(0, sc.strToInt("0"));
        assertEquals(1, sc.strToInt("1"));
        assertEquals(0, sc.strToInt(""));
        assertEquals(8, sc.strToInt("8"));
    }

}
