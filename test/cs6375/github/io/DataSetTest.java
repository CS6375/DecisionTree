package cs6375.github.io;

import org.junit.Test;

import java.io.IOException;
import static org.junit.Assert.*;

public class DataSetTest {

    @Test
    public void DataSet() throws IOException {
        DataSet ds = new DataSet("resources/data_sets1/training_set.csv");
        System.out.println(ds);
    }

}