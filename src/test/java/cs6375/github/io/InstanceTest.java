package cs6375.github.io;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class InstanceTest {

    Instance i = new Instance("1,1,0,1,1,0,1,1,0,1,1,1,0,1,1,0,0,1,0,0,1");

    @Test
    public void getValues() throws Exception {
        boolean[] values = {true, true, false, true, true, false, true, true, false, true, true,
                true, false, true, true, false, false, true, false, false};
        assertArrayEquals(values, i.getValues());
    }

    @Test
    public void getLabel() throws Exception {
        assertTrue(i.getLabel());
    }
}